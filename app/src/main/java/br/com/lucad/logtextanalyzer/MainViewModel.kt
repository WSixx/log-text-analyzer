package br.com.lucad.logtextanalyzer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucad.nativeanalyzer.NativeLib
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val nativeLib: NativeLib = NativeLib(),
) : ViewModel() {

    private val _headerText = MutableStateFlow("Type Text")
    val headerText: StateFlow<String> = _headerText.asStateFlow()

    fun analyzeLog() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                nativeLib.stringFromJNI()
            }
            _headerText.value = result
        }
    }
}
