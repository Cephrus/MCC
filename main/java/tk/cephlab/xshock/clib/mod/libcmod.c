/*
 * libcmod.c
 *
 *  Created on: Aug 10, 2014
 *      Author: Cephrus
 */

#include "jni.h"
#include "mccmod.h"

#ifdef _cplusplus
extern "C"
{
#endif

	JNIEXPORT void JNICALL Java_tk_cephlab_xshock_clib_mod_CModMetadata_preInit(JNIEnv * env, jobject obj)
	{
		jclass cls = env->GetObjectClass(env, obj);
		preInit(env, obj);
		jmethodID ret = env->GetMethodID(env, cls, "");
	}

	JNIEXPORT void JNICALL Java_tk_cephlab_xshock_clib_mod_CModMetadata_init(JNIEnv * env, jobject obj)
	{
		jclass cls = env->GetObjectClass(env, obj);
		init(env, obj);
		jmethodID ret = env->GetMethodID(env, cls, "");
	}

	JNIEXPORT void JNICALL Java_tk_cephlab_xshock_clib_mod_CModMetadata_postInit(JNIEnv * env, jobject obj)
	{
		jclass cls = env->GetObjectClass(env, obj);
		postInit(env, obj);
		jmethodID ret = env->GetMethodID(env, cls, "");
	}

#ifdef _cplusplus
}
#endif
