package br.com.lucad.logtextanalyzer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.lucad.nativeanalyzer.LogAnalysisResult
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

    companion object {
        private const val MOCK_LOG =
            """2026-03-30 10:15:01.123 INFO  [MainThread]: Starting application initialization...
2026-03-30 10:15:02.456 DEBUG [NetworkModule]: Connecting to backend endpoint https://api.example.com/v1/data
2026-03-30 10:15:03.789 ERROR [NetworkModule]: Connection timeout after 5000ms. Retrying (1/3)
2026-03-30 10:15:05.101 WARN  [CacheManager]: Cache miss for key 'user_settings'. Falling back to local DB.
2026-03-30 10:15:06.222 ERROR [DatabaseModule]: Failed to open SQLite database: Database disk image is malformed.
2026-03-30 10:15:07.333 INFO  [AuthManager]: User session restored for user_id=1042
2026-03-30 10:15:08.888 ERROR [SyncWorker]: Background sync failed with StatusCode 500 Internal Server Error."""

        private const val MOCK_LOG_2 = "";
        private const val MOCK_LOG_3 = "ERROR";
        private const val MOCK_LOG_4 = "A";
    }

    fun analyzeLog() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                val stats = nativeLib.parseLog(MOCK_LOG, "")
                LogAnalysisResult(
                    lineCount = stats[0],
                    charCount = stats[1],
                    keywordCount = stats[2]
                )

            }
            _headerText.value = "Line Count: ${result.lineCount} \n" +
                    "Char Count: ${result.charCount} \n" +
                    "Keyword Count: ${result.keywordCount} \n"
        }
    }
}
