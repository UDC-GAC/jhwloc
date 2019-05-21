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
#include "es_udc_gac_jhwloc_HwlocTopology.h"
#include "jhwloc_util.h"

extern jhwloc_api_t api;
extern jfieldID FID_jhwloc_HwlocTopology_handler;
extern jfieldID FID_jhwloc_HwlocObject_handler;
extern jfieldID FID_jhwloc_HwlocCPUSet_handler;
extern jfieldID FID_jhwloc_HwlocBitmap_handler;

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_init
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1init
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo;
	int err;

	err = api.jhwloc_topology_init(&topo);

	(*env)->SetLongField(env, this, FID_jhwloc_HwlocTopology_handler,(jlong)topo);

	return err;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_load
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1load
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_load(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_destroy
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1destroy
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	api.jhwloc_topology_destroy(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_abi_check
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1abi_1check
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_abi_check(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_check
 * Signature: ()V
 */
JNIEXPORT void JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1check
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	api.jhwloc_topology_check(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_dup
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1dup
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t newtopology;
	hwloc_topology_t oldtopology = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	int rc = api.jhwloc_topology_dup(&newtopology, oldtopology);

	return (rc < 0)? -1 : (jlong) newtopology;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_depth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1depth
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_get_depth(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_depth_type
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1depth_1type
  (JNIEnv *env, jobject this, jint depth)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_obj_type_t chwloc_obj_type = api.jhwloc_get_depth_type(topo, depth);

	return (chwloc_obj_type == -1)? -1 : GetHwlocObjectTypeJava(env, chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_memory_parents_depth
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1memory_1parents_1depth
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_get_memory_parents_depth(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_type_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1type_1depth
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	
	int rc = api.jhwloc_get_type_depth(topo, chwloc_obj_type);

	if (rc == HWLOC_TYPE_DEPTH_UNKNOWN)
		return GetHwlocGetTypeDepthJava(env, HWLOC_TYPE_DEPTH_UNKNOWN);

	if (rc == HWLOC_TYPE_DEPTH_MULTIPLE)
		return GetHwlocGetTypeDepthJava(env, HWLOC_TYPE_DEPTH_MULTIPLE);

	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_type_or_above_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1type_1or_1above_1depth
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);

	int rc = api.jhwloc_get_type_or_above_depth(topo, chwloc_obj_type);

	if (rc == HWLOC_TYPE_DEPTH_MULTIPLE)
		return GetHwlocGetTypeDepthJava(env, HWLOC_TYPE_DEPTH_MULTIPLE);

	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_type_or_below_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1type_1or_1below_1depth
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);

	int rc = api.jhwloc_get_type_or_below_depth(topo, chwloc_obj_type);

	if (rc == HWLOC_TYPE_DEPTH_MULTIPLE)
		return GetHwlocGetTypeDepthJava(env, HWLOC_TYPE_DEPTH_MULTIPLE);

	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_nbobjs_by_type
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1nbobjs_1by_1type
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	
	return api.jhwloc_get_nbobjs_by_type(topo, chwloc_obj_type);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_nbobjs_by_depth
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1nbobjs_1by_1depth
  (JNIEnv *env, jobject this, jint depth)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_get_nbobjs_by_depth(topo, depth);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_is_thissystem
 * Signature: ()I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1is_1thissystem
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_is_thissystem(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_flags
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1flags
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_get_flags(topo);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_obj_by_type
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1obj_1by_1type
  (JNIEnv *env, jobject this, jint jhwloc_obj_type, jint idx)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	hwloc_obj_t obj = NULL;
	
	obj = api.jhwloc_get_obj_by_type(topo, chwloc_obj_type, idx);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_obj_by_depth
 * Signature: (II)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1obj_1by_1depth
  (JNIEnv *env, jobject this, jint depth, jint idx)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_t obj = NULL;

	obj = api.jhwloc_get_obj_by_depth(topo, depth, idx);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_numanode_obj_by_os_index
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1numanode_1obj_1by_1os_1index
  (JNIEnv *env, jobject this, jint os_index)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_t obj = NULL;

	obj = api.jhwloc_get_numanode_obj_by_os_index(topo, os_index);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_pu_obj_by_os_index
 * Signature: (I)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1pu_1obj_1by_1os_1index
  (JNIEnv *env, jobject this, jint os_index)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_t obj = NULL;

	obj = api.jhwloc_get_pu_obj_by_os_index(topo, os_index);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_export_xml
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1export_1xml
  (JNIEnv *env, jobject this, jstring xmlpath)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	char *path = GetStringNative(env, xmlpath);

	if(path == NULL)
		return -1;

	return api.jhwloc_topology_export_xml(topo, path, 0);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_xml
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1xml
  (JNIEnv *env, jobject this, jstring xmlpath)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	char *path = GetStringNative(env, xmlpath);

	if(path == NULL)
		return -1;

	return api.jhwloc_topology_set_xml(topo, path);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_synthetic
 * Signature: (Ljava/lang/String;)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1synthetic
  (JNIEnv *env, jobject this, jstring description)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	char *desc = GetStringNative(env, description);

	if(desc == NULL)
		return -1;

	return api.jhwloc_topology_set_synthetic(topo, desc);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_pid
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1pid
  (JNIEnv *env, jobject this, jint pid)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	return api.jhwloc_topology_set_pid(topo, pid);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_flags
 * Signature: (J)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1flags
  (JNIEnv *env, jobject this, jlong flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	return api.jhwloc_topology_set_flags(topo, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_root_obj
 * Signature: ()Ljhwloc/HwlocObject;
 */
JNIEXPORT jobject JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1root_1obj
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_t obj = NULL;
	
	obj = api.jhwloc_get_root_obj(topo);
	
	if(obj == NULL)
		return NULL;

	return CreateHwlocObject(env, this, obj);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_next_obj_by_depth
 * Signature: (ILjhwloc/HwlocObject;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1next_1obj_1by_1depth
  (JNIEnv *env, jobject this, jint depth, jobject prev)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_t obj = NULL;
	hwloc_obj_t prev_obj = NULL;

	if(prev != NULL)
		prev_obj = (hwloc_obj_t) (*env)->GetLongField(env, prev, FID_jhwloc_HwlocObject_handler);
	
	obj = api.jhwloc_get_next_obj_by_depth(topo, depth, prev_obj);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_next_obj_by_type
 * Signature: (ILjhwloc/HwlocObject;)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1next_1obj_1by_1type
  (JNIEnv *env, jobject this, jint jhwloc_obj_type, jobject prev)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	hwloc_obj_t obj = NULL;
	hwloc_obj_t prev_obj = NULL;

	if(prev != NULL)
		prev_obj = (hwloc_obj_t) (*env)->GetLongField(env, prev, FID_jhwloc_HwlocObject_handler);
	
	obj = api.jhwloc_get_next_obj_by_type(topo, chwloc_obj_type, prev_obj);

	return (obj == NULL)? -1 : (jlong) obj;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_cpubind
 * Signature: (Ljhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1cpubind
  (JNIEnv *env, jobject this, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_const_cpuset_t set = (hwloc_const_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	int rc = api.jhwloc_set_cpubind(topo, set, flags);

	if(rc == -1)
	{
		if(errno == ENOSYS)
			return -2;
		if(errno == EXDEV)
			return -3;
	}
	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_proc_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1proc_1cpubind
  (JNIEnv *env, jobject this, jint pid, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_const_cpuset_t set = (hwloc_const_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	int rc = api.jhwloc_set_proc_cpubind(topo, pid, set, flags);

	if(rc == -1)
	{
		if(errno == ENOSYS)
			return -2;
		if(errno == EXDEV)
			return -3;
	}
	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_set_thread_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1set_1thread_1cpubind
  (JNIEnv *env, jobject this, jint tid, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_const_cpuset_t set = (hwloc_const_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	int rc = api.jhwloc_set_thread_cpubind(topo, tid, set, flags);

	if(rc == -1)
	{
		if(errno == ENOSYS)
			return -2;
		if(errno == EXDEV)
			return -3;
	}
	return rc;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_cpubind
 * Signature: (Ljhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1cpubind
  (JNIEnv *env, jobject this, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_cpuset_t set = (hwloc_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	return api.jhwloc_get_cpubind(topo, set, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_proc_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1proc_1cpubind
  (JNIEnv *env, jobject this, jint pid, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_cpuset_t set = (hwloc_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	return api.jhwloc_get_proc_cpubind(topo, pid, set, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_thread_cpubind
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1thread_1cpubind
  (JNIEnv *env, jobject this, jint tid, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_cpuset_t set = (hwloc_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	return api.jhwloc_get_thread_cpubind(topo, tid, set, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_last_cpu_location
 * Signature: (Ljhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1last_1cpu_1location
  (JNIEnv *env, jobject this, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_cpuset_t set = (hwloc_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	return api.jhwloc_get_last_cpu_location(topo, set, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_proc_last_cpu_location
 * Signature: (ILes/udc/gac/jhwloc/HwlocCPUSet;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1proc_1last_1cpu_1location
  (JNIEnv *env, jobject this, jint pid, jobject cpuset, jint flags)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_cpuset_t set = (hwloc_cpuset_t) (*env)->GetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler);

	return api.jhwloc_get_proc_last_cpu_location(topo, pid, set, flags);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_complete_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1complete_1cpuset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_cpuset_t set = api.jhwloc_topology_get_complete_cpuset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_topology_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1topology_1cpuset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_cpuset_t set = api.jhwloc_topology_get_topology_cpuset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_allowed_cpuset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1allowed_1cpuset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_cpuset_t set = api.jhwloc_topology_get_allowed_cpuset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_complete_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1complete_1nodeset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_nodeset_t set = api.jhwloc_topology_get_complete_nodeset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_topology_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1topology_1nodeset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_nodeset_t set = api.jhwloc_topology_get_topology_nodeset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_allowed_nodeset
 * Signature: ()J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1allowed_1nodeset
  (JNIEnv *env, jobject this)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);

	hwloc_const_nodeset_t set = api.jhwloc_topology_get_allowed_nodeset(topo);

	return (set == NULL)? -1 : (jlong) set;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_type_filter
 * Signature: (II)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1type_1filter
  (JNIEnv *env, jobject this, jint jhwloc_obj_type, jint jhwloc_type_filter)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	enum hwloc_type_filter_e chwloc_type_filter = GetHwlocTypeFilterNative(env, jhwloc_type_filter);

	return api.jhwloc_topology_set_type_filter(topo, chwloc_obj_type, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_get_type_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1get_1type_1filter
  (JNIEnv *env, jobject this, jint jhwloc_obj_type)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	hwloc_obj_type_t chwloc_obj_type = GetHwlocObjectTypeNative(env, jhwloc_obj_type);
	enum hwloc_type_filter_e chwloc_type_filter;

	int rc = api.jhwloc_topology_get_type_filter(topo, chwloc_obj_type, &chwloc_type_filter);

	if (rc == -1)
		return -1;

	return GetHwlocTypeFilterJava(env, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_all_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1all_1types_1filter
  (JNIEnv *env, jobject this, jint jhwloc_type_filter)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	enum hwloc_type_filter_e chwloc_type_filter = GetHwlocTypeFilterNative(env, jhwloc_type_filter);

	return api.jhwloc_topology_set_all_types_filter(topo, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_cache_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1cache_1types_1filter
  (JNIEnv *env, jobject this, jint jhwloc_type_filter)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	enum hwloc_type_filter_e chwloc_type_filter = GetHwlocTypeFilterNative(env, jhwloc_type_filter);

	return api.jhwloc_topology_set_cache_types_filter(topo, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_icache_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1icache_1types_1filter
  (JNIEnv *env, jobject this, jint jhwloc_type_filter)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	enum hwloc_type_filter_e chwloc_type_filter = GetHwlocTypeFilterNative(env, jhwloc_type_filter);

	return api.jhwloc_topology_set_icache_types_filter(topo, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_topology_set_io_types_filter
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1topology_1set_1io_1types_1filter
  (JNIEnv *env, jobject this, jint jhwloc_type_filter)
{
	hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
	enum hwloc_type_filter_e chwloc_type_filter = GetHwlocTypeFilterNative(env, jhwloc_type_filter);

	return api.jhwloc_topology_set_io_types_filter(topo, chwloc_type_filter);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_membind
 * Signature: (Les/udc/gac/jhwloc/HwlocBitmap;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1membind
  (JNIEnv *env, jobject this, jobject jhwloc_bitmap, jint flags)
{
    hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
    hwloc_bitmap_t set = (hwloc_bitmap_t) (*env)->GetLongField(env, jhwloc_bitmap, FID_jhwloc_HwlocBitmap_handler);
    hwloc_membind_policy_t policy;

    int rc = api.jhwloc_get_membind(topo, set, &policy, flags);

    if (rc < 0)
    	return -1;

    return GetHwlocMEMBindPolicyTypeJava(env, policy);
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocTopology
 * Method:    jhwloc_get_proc_membind
 * Signature: (ILes/udc/gac/jhwloc/HwlocBitmap;I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocTopology_jhwloc_1get_1proc_1membind
  (JNIEnv *env, jobject this, jint pid, jobject jhwloc_bitmap, jint flags)
{
    hwloc_topology_t topo = (hwloc_topology_t) (*env)->GetLongField(env, this, FID_jhwloc_HwlocTopology_handler);
    hwloc_bitmap_t set = (hwloc_bitmap_t) (*env)->GetLongField(env, jhwloc_bitmap, FID_jhwloc_HwlocBitmap_handler);
    hwloc_membind_policy_t policy;

    int rc = api.jhwloc_get_proc_membind(topo, pid, set, &policy, flags);

    if (rc < 0)
    	return -1;

    return GetHwlocMEMBindPolicyTypeJava(env, policy);
}
