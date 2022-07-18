pipeline {
    agent { label "android" }

    environment {
        DEACTIVATE_TESTS = false
        DEACTIVATE_SECURITY = false
        DEACTIVATE_PUBLISH = false
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', credentialsId: 'github-lucas', url: 'https://github.com/1ucas/kotlin-pokemon-tdc.git'
            }
        }
        stage('Prepare Environment') {
            steps {
                echo 'Instala Gems Necessárias.'
                sh 'bundle install'
            }
        }
        stage('Clean Build') {
            steps {
                sh './gradlew clean'
                sh './gradlew :app:assembleDebug'
            }
        }
        stage('Lint') {
            when {
                expression { DEACTIVATE_TESTS != 'true' }
            }
            steps {
                sh './gradlew lint'
            }
        }
        stage('Detekt') {
            when {
                expression { DEACTIVATE_TESTS != 'true' }
            }
            steps {
                sh './gradlew detekt'
            }
        }
        stage('Test') {
            when {
                expression { DEACTIVATE_TESTS != 'true' }
            }
            steps {
                sh './gradlew testDebug'
            }
        }
        stage('Create Release APK') {
            steps {
                sh './gradlew :app:assembleRelease'
            }
        }
        stage('Sign and Align APK') {
            steps {
                // A partir da versão do gradle 4.1 a apk já nasce alinhada só precisa conferir
                echo 'Alinhamento da APK'
                sh "zipalign -c -v 4 ${WORKSPACE}/app/build/outputs/apk/release/app-release-unsigned.apk"
                
                withCredentials([
                    file(credentialsId: 'pokemon-tcg-keystore-release', variable: 'tcg_keystore'),
                    string(credentialsId: "pokemon-tcg-keystore-password", variable: "tcg_keystore_password"),
                    string(credentialsId: "pokemon-tcg-key-alias", variable: "tcg_key_alias"),
                    string(credentialsId: "pokemon-tcg-key-password", variable: "tcg_key_password"),

                ]) {
                    echo 'Copiando Store'
                    sh "mv $tcg_keystore ./pokemon-tcg-keystore-release.jks"
                    sh "apksigner sign --ks ./pokemon-tcg-keystore-release.jks --ks-key-alias ${tcg_key_alias} --ks-pass pass:${tcg_keystore_password} --key-pass pass:${tcg_key_password} --out ./app/build/outputs/apk/release/app-signed-jenkins.apk ./app/build/outputs/apk/release/app-release-unsigned.apk"
                }

                echo 'Verificando alinhamento da APK'
                sh 'zipalign -c -v 4 ./app/build/outputs/apk/release/app-signed-jenkins.apk'
            }
        }
        stage('Validação de Segurança') {
            when {
                expression { DEACTIVATE_SECURITY != 'true' }
            }
            steps {
                script {
                    withCredentials([
                        string(credentialsId: 'MOBSF_TOKEN', variable: 'MOBSF_TOKEN')
                    ]) {
                        sh(returnStdout: true, script: "curl --show-error --fail -o mobsf_upload_result.json -F 'file=@${WORKSPACE}/app/build/outputs/apk/release/app-signed-jenkins.apk' http://mobsf:8000/api/v1/upload -H \"Authorization:${MOBSF_TOKEN}\"")
                        echo 'Realizando o Upload'
                        def mobsf_upload_hash = sh(
                            script: 'jq \'.hash\' mobsf_upload_result.json',
                            returnStdout: true
                            ).trim()
                        echo 'Realizando o Scan'
                        sh(
                            script: "curl --show-error --fail -o mobsf_scan_result.json -X POST --url http://mobsf:8000/api/v1/scan --data \"scan_type=apk&file_name=app-release.apk&hash=${mobsf_upload_hash}\" -H \"Authorization:${MOBSF_TOKEN}\"",
                            returnStdout: true,
                            returnStatus: true
                        )
                        echo 'Gerando o Report'
                        sh(
                            script: "curl --show-error --fail -o mobsf_json_report.json -X POST --url http://mobsf:8000/api/v1/report_json --data \"hash=${mobsf_upload_hash}\" -H \"Authorization:${MOBSF_TOKEN}\"",
                            returnStdout: true,
                            returnStatus: true
                        )
                        echo 'Deleta o upload e report'
                        sh(
                            script: "curl --show-error --fail -X POST --url http://mobsf:8000/api/v1/delete_scan --data \"hash=${mobsf_upload_hash}\" -H \"Authorization:${MOBSF_TOKEN}\"",
                            returnStdout: true,
                            returnStatus: true
                        )
                        echo 'Validando o Report'
                        def mobsf_security_score = sh(
                            script: 'jq \'.security_score\' mobsf_json_report.json',
                            returnStdout: true
                            ).trim() as Integer
                        if (mobsf_security_score < 95) {
                            error("Validação de segurança falhou com nota menor do que 95. Nota Obtida: ${mobsf_security_score}")
                        } else {
                            echo "Passou na validação de segurança com nota: ${mobsf_security_score}."
                        }
                    }
                }
            }
        }
        stage('Deploy to Firebase') {
            when {
                expression { DEACTIVATE_PUBLISH != 'true' }
            }
            steps {
                withCredentials([
                    string(credentialsId: 'firebase_refresh_token', variable: 'firebase_refresh_token')
                ]) {
                    sh """ 
                        LC_ALL=en_US.UTF-8 \
                        LANG=en_US.UTF-8 \
                        bundle exec fastlane firebase
                    """
                }
            }
        }
    }
    post { 
        always { 
            cleanWs()
        }
    }
}