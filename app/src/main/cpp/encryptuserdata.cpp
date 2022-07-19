// Write C++ code here.
//
// Do not forget to dynamically load the C++ library into your application.
//
// For instance,
//
// In MainActivity.java:
//    static {
//       System.loadLibrary("encryptuserdata");
//    }
//
// Or, in MainActivity.kt:
//    companion object {
//      init {
//         System.loadLibrary("encryptuserdata")
//      }
//    }

#include <jni.h>
#include <string>

extern "C"
JNIEXPORT jstring JNICALL
Java_com_slailati_android_encryptuserdata_ui_activity_MainActivity_firstAPIKey(JNIEnv *env, jobject object) {
    std::string hello = "TmF0aXZlNWVjcmV0UEBzc3cwcmQx";
    return env->NewStringUTF(hello.c_str());
}
