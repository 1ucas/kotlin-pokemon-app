package pokemontcg.features.pokemon.ui.generations

import androidx.lifecycle.MutableLiveData
import pokemontcg.features.pokemon.domain.model.Generation
import pokemontcg.features.pokemon.domain.model.Pokemon
import pokemontcg.features.pokemon.domain.usecase.GetCharmanderUseCase
import pokemontcg.features.pokemon.domain.usecase.ListGenerationsUseCase
import pokemontcg.libraries.ui_components.BaseViewModel
import pokemontcg.libraries.ui_components.SingleLiveEvent

internal class PokemonGenerationsViewModel(
    private val useCase: ListGenerationsUseCase,
    private val charmanderUseCase: GetCharmanderUseCase) : BaseViewModel() {

    val generations = SingleLiveEvent<List<Generation>>()
    val pokemon = SingleLiveEvent<Pokemon>()

    fun fetchGenerations() {
        doAsyncWork {
            val response = useCase.execute(Unit)
            generations.value = response
        }
    }

    fun fetchCharmander() {
        doAsyncWork {
            val response = charmanderUseCase.execute(Unit)
            pokemon.value = response
        }
    }
}
