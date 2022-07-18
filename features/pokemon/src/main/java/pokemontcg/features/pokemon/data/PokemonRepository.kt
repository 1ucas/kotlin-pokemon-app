package pokemontcg.features.pokemon.data

import pokemontcg.features.pokemon.data.entities.GeneralEntry
import pokemontcg.features.pokemon.data.entities.GenerationDTO
import pokemontcg.features.pokemon.data.entities.PokemonDTO
import retrofit2.Response
import retrofit2.http.Path

internal interface PokemonRepository {
    suspend fun listGenerations() : List<GeneralEntry>
    suspend fun getGenerationDetails(generationName: String) : GenerationDTO
    suspend fun getPokemon(number:Int) : PokemonDTO
}
