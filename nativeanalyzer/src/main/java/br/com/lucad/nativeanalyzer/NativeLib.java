package br.com.lucad.nativeanalyzer;

public class NativeLib {

    static {
        System.loadLibrary("nativeanalyzer");
    }

    public native String stringFromJNI();
}
