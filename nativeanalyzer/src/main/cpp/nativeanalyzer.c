#include <jni.h>
#include <stdio.h>
#include <string.h>
#include <android/log.h>

#define LOG_TAG "NativeAnalyzer"
#define LOGI(...) __android_log_print(ANDROID_LOG_INFO, LOG_TAG, __VA_ARGS__)

typedef struct {
    int line_count;
    int char_count;
    int keyword_matchers;
} LogStats;

LogStats analyze_buffer(const char *buffer, const char *keyword);

JNIEXPORT jstring JNICALL
Java_br_com_lucad_nativeanalyzer_NativeLib_stringFromJNI(
        JNIEnv *env,
        jobject thiz) {
    (void) thiz;
    const char *hello = "Hello from C";
    return (*env)->NewStringUTF(env, hello);
}

JNIEXPORT jintArray JNICALL
Java_br_com_lucad_nativeanalyzer_NativeLib_parseLog(JNIEnv *env, jobject thiz, jstring text,
                                                    jstring keyword) {

    if (text == nullptr || keyword == nullptr) {
        return nullptr;
    }

    const char *textLocal = (*env)->GetStringUTFChars(env, text, nullptr);
    if (textLocal == nullptr) return nullptr;

    const char *keyWordLocal = (*env)->GetStringUTFChars(env, keyword, nullptr);
    if (keyWordLocal == nullptr) {
        (*env)->ReleaseStringUTFChars(env, text, textLocal);
        return nullptr;
    }

    LogStats logStats = analyze_buffer(textLocal, keyWordLocal);

    //releasing
    (*env)->ReleaseStringUTFChars(env, text, textLocal);
    (*env)->ReleaseStringUTFChars(env, keyword, keyWordLocal);
    return
}

LogStats analyze_buffer(const char *buffer, const char *keyword) {
    const char *ptr = buffer;
    const char *ptrKeyWord = keyword;
    LogStats logStats = {};

    while (*ptr != '\0') {

        if (*ptr == *ptrKeyWord) {
            const char *ptrLocal = ptr;
            while (*ptrLocal == *ptrKeyWord && *ptrLocal != '\0') {
                ptrKeyWord++;
                ptrLocal++;
            }
            if (*ptrKeyWord == '\0') {
                logStats.keyword_matchers++;
            }
            ptrKeyWord = keyword;
        }

        if (*ptr == '\n') {
            LOGI("New Line %d", *ptr);
            logStats.line_count++;
        }
        ptr++;
    }
    ptrdiff_t charCount = ptr - buffer;
    LOGI("Char Count %d", charCount);
    logStats.char_count = charCount;
    return logStats;
}