package br.com.lucad.nativeanalyzer

class NativeLib {

    external fun stringFromJNI(): String

    companion object {
        init {
            System.loadLibrary("nativeanalyzer")
        }
    }
}
