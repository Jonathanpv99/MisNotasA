package net.ivanvega.misnotasa.repository

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import net.ivanvega.misnotasa.data.model.Nota

class NotaViewModel (private val repository: NotasRepository)
    : ViewModel() {
    var cont = 0
    val allNotas : LiveData<List<Nota>> = repository.allNotas.asLiveData()

    fun insertarAsync(nota: Nota) = viewModelScope.launch {
        repository.insertarAsync(nota)
    }

}

class NotaViewModelFactory(private val repository: NotasRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotaViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotaViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
