# ProGuard/R8 rules for app module

# Keep JNI methods
-keepclasseswithmembers class * {
    native <methods>;
}

# Preserve Line Numbers for Debugging/Crash Reporting
-renamesourcefileattribute SourceFile
-keepattributes SourceFile,LineNumberTable
