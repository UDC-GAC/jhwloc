/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class es_udc_gac_jhwloc_HwlocTopology */

#ifndef _Included_es_udc_gac_jhwloc_HwlocTopology
#define _Included_es_udc_gac_jhwloc_HwlocTopology
#ifdef __cplusplus
extern "C" {
#endif
/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_init
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1init
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_load
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1load
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_destroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1destroy
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_dup
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1dup
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_abi_check
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1abi_1check
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_check
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1check
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_depth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1depth
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_depth_type
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1depth_1type
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_type_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1type_1depth
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_nbobjs_by_type
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1nbobjs_1by_1type
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_nbobjs_by_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1nbobjs_1by_1depth
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_is_thissystem
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1is_1thissystem
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_flags
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1flags
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_obj_by_type
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1obj_1by_1type
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_obj_by_depth
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1obj_1by_1depth
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_export_xml
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1export_1xml
  (JNIEnv *, jobject, jstring);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_xml
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1xml
  (JNIEnv *, jobject, jstring);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_synthetic
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1synthetic
  (JNIEnv *, jobject, jstring);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_pid
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1pid
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_flags
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1flags
  (JNIEnv *, jobject, jlong);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_root_obj
 * Signature: ()Les/udc/gac/jhwloc/HwlocObject;
 */
JNIEXPORT jobject JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1root_1obj
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_next_obj_by_depth
 * Signature: (ILes/udc/gac/jhwloc/HwlocObject;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1next_1obj_1by_1depth
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_next_obj_by_type
 * Signature: (ILes/udc/gac/jhwloc/HwlocObject;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1next_1obj_1by_1type
  (JNIEnv *, jobject, jint, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_cpubind
 * Signature: (Les/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1cpubind
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_proc_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1proc_1cpubind
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_thread_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1thread_1cpubind
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_cpubind
 * Signature: (Les/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1cpubind
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_proc_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1proc_1cpubind
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_thread_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1thread_1cpubind
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_last_cpu_location
 * Signature: (Les/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1last_1cpu_1location
  (JNIEnv *, jobject, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_proc_last_cpu_location
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1proc_1last_1cpu_1location
  (JNIEnv *, jobject, jint, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_complete_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1complete_1cpuset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_topology_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1topology_1cpuset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_allowed_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1allowed_1cpuset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_complete_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1complete_1nodeset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_topology_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1topology_1nodeset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_allowed_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1allowed_1nodeset
  (JNIEnv *, jobject);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_type_filter
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1type_1filter
  (JNIEnv *, jobject, jint, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_type_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1type_1filter
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_all_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1all_1types_1filter
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_cache_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1cache_1types_1filter
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_icache_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1icache_1types_1filter
  (JNIEnv *, jobject, jint);

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_io_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1io_1types_1filter
  (JNIEnv *, jobject, jint);

#ifdef __cplusplus
}
#endif
#endif