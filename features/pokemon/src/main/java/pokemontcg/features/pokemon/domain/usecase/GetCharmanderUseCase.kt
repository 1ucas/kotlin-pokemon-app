package pokemontcg.features.pokemon.domain.usecase

import pokemontcg.features.pokemon.data.PokemonRepository
import pokemontcg.features.pokemon.domain.model.Generation
import pokemontcg.features.pokemon.domain.model.Pokemon
import pokemontcg.libraries.common.UseCase

internal class GetCharmanderUseCase(private val repo: PokemonRepository) : UseCase<Unit, Pokemon> {

    override suspend fun execute(param: Unit): Pokemon {
        val pokemonEntity = repo.getPokemon(number = 4)
        return pokemonEntity.mapTo()
    }
}
