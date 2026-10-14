#include "es_udc_gac_jhwloc_HWLOC.h"
#include "jhwloc_util.h"

extern jhwloc_api_t api;

/*
 * Class:     es_udc_gac_jhwloc_HWLOC
 * Method:    jhwloc_get_api_version
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HWLOC_jhwloc_1get_1api_1version
  (JNIEnv *env, jclass this)
{
	return api.jhwloc_get_api_version();
}

/*
 * Class:     es_udc_gac_jhwloc_HWLOC
 * Method:    jhwloc_api_version
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HWLOC_jhwloc_1api_1version
  (JNIEnv *env, jclass this)
{
	return HWLOC_API_VERSION;
}

/*
 * Class:     es_udc_gac_jhwloc_HWLOC
 * Method:    jhwloc_compare_types
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HWLOC_jhwloc_1compare_1types
 (JNIEnv *env, jclass this, jint jhwloc_obj_type1, jint jhwloc_obj_type2)
{
	hwloc_obj_type_t chwloc_obj_type1 = GetHwlocObjectTypeNative(env, jhwloc_obj_type1);
	RETURN_IF_EXCEPTION(env, -1);
	hwloc_obj_type_t chwloc_obj_type2 = GetHwlocObjectTypeNative(env, jhwloc_obj_type2);
	RETURN_IF_EXCEPTION(env, -1);

	int rc = api.jhwloc_compare_types(chwloc_obj_type1, chwloc_obj_type2);

	if (rc == HWLOC_TYPE_UNORDERED)
		return GetHwlocCompareTypesJava(env, HWLOC_TYPE_UNORDERED);

	return rc;
}
