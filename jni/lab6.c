#include <jni.h>
#include <string.h>


jstring Java_com_example_mso_1lab_Window6_toUpperCase(JNIEnv* env, jobject obj, jstring textToChange, int n){

    char *str = (*env)->GetStringUTFChars(env,textToChange,0);
    for (int i = 0; i < n; i++) {
        if (str[i] >= 97 && str[i] <= 122){
            str[i] -= 32;
        }
    }
    return (*env)->NewStringUTF(env, str);

}