#include <jni.h>

JNIEXPORT jstring JNICALL
Java_br_com_lucad_nativeanalyzer_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject thiz) {
    (void)thiz;
    const char *hello = "Hello from C";
    return (*env)->NewStringUTF(env, hello);
}