#include "es_udc_gac_jhwloc_HWLOC.h"
#include "jhwloc_util.h"

/*
 * HWLOC.TYPE_UNORDERED and HWLOC.TYPE_DEPTH_* are the values of hwloc and the native
 * functions return what hwloc returns, so they must be the same.
 */
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_UNORDERED == HWLOC_TYPE_UNORDERED, "HWLOC.TYPE_UNORDERED differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_UNKNOWN == HWLOC_TYPE_DEPTH_UNKNOWN, "HWLOC.TYPE_DEPTH_UNKNOWN differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_MULTIPLE == HWLOC_TYPE_DEPTH_MULTIPLE, "HWLOC.TYPE_DEPTH_MULTIPLE differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_NUMANODE == HWLOC_TYPE_DEPTH_NUMANODE, "HWLOC.TYPE_DEPTH_NUMANODE differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_BRIDGE == HWLOC_TYPE_DEPTH_BRIDGE, "HWLOC.TYPE_DEPTH_BRIDGE differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_PCI_DEVICE == HWLOC_TYPE_DEPTH_PCI_DEVICE, "HWLOC.TYPE_DEPTH_PCI_DEVICE differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_OS_DEVICE == HWLOC_TYPE_DEPTH_OS_DEVICE, "HWLOC.TYPE_DEPTH_OS_DEVICE differs from hwloc");
_Static_assert(es_udc_gac_jhwloc_HWLOC_TYPE_DEPTH_MISC == HWLOC_TYPE_DEPTH_MISC, "HWLOC.TYPE_DEPTH_MISC differs from hwloc");

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

	return api.jhwloc_compare_types(chwloc_obj_type1, chwloc_obj_type2);
}
