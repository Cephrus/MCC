/*
 * mccapi.h
 *
 *  Created on: Sep 1, 2014
 *      Author: Cephrus
 */

#include <stdbool.h>

#ifndef MCCAPI_H_
#define MCCAPI_H_

typedef char[] String;

typedef struct MATERL
{
	int matID;
}
Material;

typedef struct BLKSTC
{
	String unlocalized;
	int hardness;
	Material mat;
	bool isUnbreakable;
}
Block;

typedef struct ITMSTD
{
	int id;
	String unlocalized;
}
Item;

typedef Item[64] ItemStack;

void registerRecipe(String firstRow, String secondRow, String thirdRow, char[] c, Item[] i, Item output, JNIEnv *env, jobject obj);

#endif /* MCCAPI_H_ */
