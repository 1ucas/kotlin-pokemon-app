package pokemontcg.libraries.network

import pokemontcg.libraries.network.exceptions.GeneralNetworkException
import pokemontcg.libraries.network.exceptions.ServerErrorException
import retrofit2.Response

@Suppress("TooGenericExceptionCaught")
object RequestManager {

    private const val serverErrorCode = 500

    suspend fun <T> requestFromApi(
        request:(suspend () -> Response<T>)
    ): T? {
        try {
            val response = request()
            if(response.isSuccessful) {
                return response.body() // -> Já realiza o Parse da resposta
            }
            else {
                val message = response.message()
                if(message.isBlank()) { message = "Couldn't fetch from server." }
                throw when(response.code()) {
                    serverErrorCode -> ServerErrorException(message)
                    else -> GeneralNetworkException(message)
                }
            }

        } catch (e: Exception) {
            // Log
            throw e
        }
    }
}
