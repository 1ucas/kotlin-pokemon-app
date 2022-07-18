@file:Suppress("PackageNaming")
package pokemontcg.libraries.ui_components

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    val showError = SingleLiveEvent<String>()
    val state = SingleLiveEvent<State>()
    val isLoading = SingleLiveEvent<Boolean>()

    enum class State {
        Default,
        Loading
    }

    @Suppress("TooGenericExceptionCaught")
    fun doAsyncWork(work: suspend () -> Unit) {
        viewModelScope.launch {
            try {
                state.call(State.Loading)
                isLoading.call(true)
                work()
            } catch (e: Exception) {
                showError.call(e.message)
            }
            state.call(State.Default)
            isLoading.call(false)
        }
    }

}
