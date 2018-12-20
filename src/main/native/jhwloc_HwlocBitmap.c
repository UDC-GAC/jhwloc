/*
 * Copyright (C) 2019 Universidade da Coruña
 * 
 * This file is part of jhwloc.
 * 
 * jhwloc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jhwloc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jhwloc. If not, see <http://www.gnu.org/licenses/>.
 */
#include "es_udc_gac_jhwloc_HwlocBitmap.h"
#include "jhwloc_util.h"

extern jhwloc_api_t api;
extern jfieldID FID_jhwloc_HwlocBitmap_handler;

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_alloc
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1alloc
  (JNIEnv *env, jclass this)
{
	hwloc_bitmap_t bitmap = api.jhwloc_bitmap_alloc();

	return (bitmap == NULL)? -1 : (jlong) bitmap;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_alloc_full
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1alloc_1full
  (JNIEnv *env, jclass this)
{
	hwloc_bitmap_t bitmap = api.jhwloc_bitmap_alloc_full();

	return (bitmap == NULL)? -1 : (jlong) bitmap;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_copy
 * Signature: (Les/udc/gac/jhwloc/HwlocBitmap;Les/udc/gac/jhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1copy
  (JNIEnv *env, jclass this, jobject dst, jobject src)
{
	hwloc_bitmap_t bitmap_dst = (hwloc_bitmap_t) (*env)->GetLongField(env, dst, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap_src = (hwloc_const_bitmap_t) (*env)->GetLongField(env, src, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_copy(bitmap_dst, bitmap_src);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_set
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1set
  (JNIEnv *env, jobject this, jint id)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_set(bitmap, id);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_set_range
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1set_1range
  (JNIEnv *env, jobject this, jint begin, jint end)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_set_range(bitmap, begin, end);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_clr
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1clr
  (JNIEnv *env, jobject this, jint id)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_clr(bitmap, id);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_clr_range
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1clr_1range
  (JNIEnv *env, jobject this, jint begin, jint end)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_clr_range(bitmap, begin, end);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_only
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1only
  (JNIEnv *env, jobject this, jint id)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_only(bitmap, id);
}


/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_allbut
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1allbut
  (JNIEnv *env, jobject this, jint id)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_allbut(bitmap, id);
}


/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_isincluded
 * Signature: (Ljhwloc/HwlocBitmap;Ljhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1isincluded
  (JNIEnv *env, jclass this, jobject obj_sub, jobject obj_super)
{
	hwloc_const_bitmap_t sub = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_sub, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t super = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_super, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_isincluded(sub, super);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_free
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1free
  (JNIEnv *env, jobject this)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	api.jhwloc_bitmap_free(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_dup
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1dup
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	hwloc_bitmap_t copy = api.jhwloc_bitmap_dup(bitmap);
	
	return (copy == NULL)? -1 : (jlong) copy;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_or
 * Signature: (Ljhwloc/HwlocBitmap;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1or
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);
	hwloc_bitmap_t res = api.jhwloc_bitmap_alloc();

	api.jhwloc_bitmap_or(res, bitmap1, bitmap2);

	return (jlong) res;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_and
 * Signature: (Ljhwloc/HwlocBitmap;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1and
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);
	hwloc_bitmap_t res = api.jhwloc_bitmap_alloc();

	api.jhwloc_bitmap_and(res, bitmap1, bitmap2);

	return (jlong) res;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_andnot
 * Signature: (Ljhwloc/HwlocBitmap;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1andnot
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);
	hwloc_bitmap_t res = api.jhwloc_bitmap_alloc();

	api.jhwloc_bitmap_andnot(res, bitmap1, bitmap2);

	return (jlong) res;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_xor
 * Signature: (Ljhwloc/HwlocBitmap;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1xor
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);
	hwloc_bitmap_t res = api.jhwloc_bitmap_alloc();

	api.jhwloc_bitmap_xor(res, bitmap1, bitmap2);

	return (jlong) res;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_not
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1not
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);
	hwloc_bitmap_t res = api.jhwloc_bitmap_alloc();

	api.jhwloc_bitmap_not(res, bitmap);

	return (jlong) res;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_singlify
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1singlify
  (JNIEnv *env, jobject this)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);
	return api.jhwloc_bitmap_singlify(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_intersects
 * Signature: (Ljhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1intersects
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_intersects(bitmap1, bitmap2);
}


/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_isequal
 * Signature: (Ljhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1isequal
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_isequal(bitmap1, bitmap2);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_compare
 * Signature: (Ljhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1compare
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_compare(bitmap1, bitmap2);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_compare_first
 * Signature: (Ljhwloc/HwlocBitmap;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1compare_1first
  (JNIEnv *env, jobject obj_bitmap1, jobject obj_bitmap2)
{
	hwloc_const_bitmap_t bitmap1 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap1, FID_jhwloc_HwlocBitmap_handler);
	hwloc_const_bitmap_t bitmap2 = (hwloc_const_bitmap_t) (*env)->GetLongField(env, obj_bitmap2, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_compare_first(bitmap1, bitmap2);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_zero
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1zero
  (JNIEnv *env, jobject this)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	api.jhwloc_bitmap_zero(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_fill
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1fill
  (JNIEnv *env, jobject this)
{
	hwloc_bitmap_t bitmap = (hwloc_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	api.jhwloc_bitmap_fill(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_isset
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1isset
  (JNIEnv *env, jobject this, jint id)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_isset(bitmap, id);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_iszero
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1iszero
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_iszero(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_isfull
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1isfull
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return api.jhwloc_bitmap_isfull(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_asprintf
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1asprintf
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);
	char *string;

	api.jhwloc_bitmap_asprintf(&string, bitmap);

	jstring str = GetStringJava(env, string);
	free(string);

	return str;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_list_asprintf
 * Signature: ()Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1list_1asprintf
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);
	char *string;

	api.jhwloc_bitmap_list_asprintf(&string, bitmap);

	jstring str = GetStringJava(env, string);
	free(string);

	return str;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_first
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1first
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_first(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_first_unset
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1first_1unset
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_first_unset(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_last
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1last
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_last(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_last_unset
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1last_1unset
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_last_unset(bitmap);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_next
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1next
  (JNIEnv *env, jobject this, jint prev)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_next(bitmap, prev);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_next_unset
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1next_1unset
  (JNIEnv *env, jobject this, jint prev)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_next_unset(bitmap, prev);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocBitmap
 * Method:    jhwloc_bitmap_weight
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocBitmap_jhwloc_1bitmap_1weight
  (JNIEnv *env, jobject this)
{
	hwloc_const_bitmap_t bitmap = (hwloc_const_bitmap_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocBitmap_handler);

	return 	api.jhwloc_bitmap_weight(bitmap);
}

