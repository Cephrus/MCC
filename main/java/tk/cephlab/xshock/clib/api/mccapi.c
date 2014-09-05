/*
 * mccapi.c
 *
 *  Created on: Sep 5, 2014
 *      Author: Cephrus
 */

#include "mccapi.h"
#include <stdlib.h>

void registerRecipe(String firstRow, String secondRow, String thirdRow, char[] c, Item[] it, Item output, int quantity, JNIEnv *env, jobject obj)
{
	jclass cls = env->GetJavaClass(env, obj);
	int[] ids;
	for(int i = 0; i < sizeof(i); i++)
	{
		ids[i] = it[i].id;
	}
	int outId = output.id;
	jmethodID mth = env->GetMethodID(env, cls, "registerRecipe", "SSS[CI]II");
	env->CallVoidMethod(env, obj, mth, firstRow, secondRow, thirdRow, c, ids, outId, quantity);
}
