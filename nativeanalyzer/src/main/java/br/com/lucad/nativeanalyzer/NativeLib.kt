package br.com.lucad.nativeanalyzer

data class LogAnalysisResult(
    val lineCount: Int,
    val charCount: Int,
    val keywordCount: Int,
)

class NativeLib {

    external fun stringFromJNI(): String

    external fun parseLog(text: String, keyword: String): IntArray

    companion object {
        init {
            System.loadLibrary("nativeanalyzer")
        }
    }
}
