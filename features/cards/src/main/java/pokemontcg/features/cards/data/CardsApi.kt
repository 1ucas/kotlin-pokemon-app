import pokemontcg.features.cards.data.CardListResponse
import retrofit2.Response
import retrofit2.http.GET

internal interface CardsApi {
    @GET("cards?name=charmander")
    suspend fun listCards(): Response<CardListResponse>
}
