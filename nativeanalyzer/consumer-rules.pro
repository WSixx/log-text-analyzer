# Consumer ProGuard rules for nativeanalyzer library

# Preserve JNI classes and native methods
-keepclasseswithmembers class * {
    native <methods>;
}

-keep class br.com.lucad.nativeanalyzer.NativeLib { *; }
