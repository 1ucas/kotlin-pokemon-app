# Buscador de Cartas de Pokemon

![Bitrise](https://app.bitrise.io/app/26d1c0031990a97a.svg?token=4cTxJZAOYqiuFy08kvVgJA&branch=master)
![Github Actions](https://github.com/1ucas/kotlin-pokemon-tdc/actions/workflows/Android%20CI.yml/badge.svg)
![Codemagic](https://api.codemagic.io/apps/61a23a2495aace8712502100/android-build/status_badge.svg)

Este é um aplicativo nativo Android estilo showcase para testes / aplicações de funcionalidades.

Deve ser utilizado como consulta de implementações e referência de evolução das plataformas, framework e bibliotecas.

Não deve ser usado como "base" para nenhum projeto, mas sim como consulta porque é certo que irá apresentar mais funciondalidades do que esperado ou até alguns pontos de overengineering,

## Core:

Um aplicativo que faz a busca de uma lista de cartas de Pokemon

## Funcionalidades:

- [x] Listar Cartas
- [X] Exibir em formato de um grid

## Tecnologia / Stack:

- [x] Clean Architecture
- [x] MVVM
- [x] View - Activities + Layouts
- [x] Presenter - ViewModel (com propriedades reativas)
- [x] Router - Manual (modularizado)
- [x] Testes Unitários
- [x] Testes Testes de Integração (API)
- [x] Gerenciador de Dependências - Gradle + Groove
- [ ] Modularização - Algumas partes

## Bibliotecas Utilizadas:

- [x] LiveData / Observables
- [x] Glide
- [x] DI - Koin
- [x] MockK
- [x] Retrofit

## Work in Progress (WIP):

- [ ] Pipeline - Em construção
- [ ] Pipeline Jenkins - Em construção v2

## Trabalho Futuro (Roadmap):

- [ ] Analytics
- [ ] Persistência - Room
- [ ] Gerenciador de Dependências - Gradle + Kotlin DSL
- [ ] View - Activitis + Fragments
- [ ] Router - Manual -> Navigation + NavHostFragment
- [ ] Certificate Pinning
- [ ] Padrão de Qualidade (QA) - DETEKT
