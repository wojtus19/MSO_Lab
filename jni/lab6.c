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

void swap(int *x, int *y){
    int temp = *x;
    *x = *y;
    *y = temp;
}

void Sort(int arr[], int n){
    for (int i = 0; i < n-1; i++){
        for (int j = 0; j < n-1; j++) {
            if (arr[j] > arr[j+1]){
                swap(&arr[j],&arr[j+1]);
            }
        }
    }
}

jstring Java_com_example_mso_1lab_Window6_Sort(JNIEnv* env, jobject obj, jintArray arr){

   int *tab = (*env) -> GetIntArrayElements(env, arr, NULL);
   int n = (*env) -> GetArrayLength(env, arr);

   Sort(tab, n);

   jintArray ret;
   ret = (*env)->NewIntArray(env,n);
   (*env) -> SetIntArrayRegion(env,ret, NULL, n, tab);

   return ret;



}
