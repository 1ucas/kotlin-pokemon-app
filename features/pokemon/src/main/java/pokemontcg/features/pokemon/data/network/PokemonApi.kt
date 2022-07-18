package pokemontcg.features.pokemon.data.network

import pokemontcg.features.pokemon.data.entities.GenerationDTO
import pokemontcg.features.pokemon.data.entities.GenerationListingDTO
import pokemontcg.features.pokemon.data.entities.PokemonDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

internal interface PokemonApi {
    @GET("generation")
    suspend fun listGenerations() : Response<GenerationListingDTO>

    @GET("generation/{name}")
    suspend fun getGenerationDetails(@Path("name") name:String) : Response<GenerationDTO>

    @GET("pokemon/{number}")
    suspend fun getPokemon(@Path("number") number:Int) : Response<PokemonDTO>
}
