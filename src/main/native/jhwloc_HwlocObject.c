#include "es_udc_gac_jhwloc_HwlocObject.h"
#include "jhwloc_util.h"

extern jhwloc_api_t api;

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_string
 * Signature: (I)Ljava/lang/String;
 */
JNIEXPORT jstring JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1string
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, NULL);
	const char *cad = NULL;

	cad = api.jhwloc_obj_type_string(chwloc_obj_type);

	return (cad == NULL)? NULL : GetStringJava(env, cad);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_normal
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1normal
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_normal(chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_io
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1io
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_io(chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_memory
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1memory
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_memory(chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_cache
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1cache
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_cache(chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_dcache
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1dcache
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_dcache(chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocObject
 * Method:    jhwloc_obj_type_is_icache
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocObject_jhwloc_1obj_1type_1is_1icache
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	RETURN_IF_EXCEPTION(env, -1);

	return api.jhwloc_obj_type_is_icache(chwloc_obj_type);
}
