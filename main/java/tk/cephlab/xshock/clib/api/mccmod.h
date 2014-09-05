/*
 * mccmod.h
 *
 *	Should be #included in all main mod files.
 *	This
 *
 *  Created on: Sep 1, 2014
 *      Author: Cephrus
 */

#ifndef MCCMOD_H_
#define MCCMOD_H_
#include "jni.h"

void preInit(JNIEnv *env, jobject obj);
void init(JNIEnv *env, jobject obj);
void postInit(JNIEnv *env, jobject obj);

#endif /* MCCMOD_H_ */
