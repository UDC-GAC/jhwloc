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
#include "jhwloc_util.h"

extern int errno;

JavaVM *cached_jvm;
jhwloc_api_t api;

/* HwlocTopology */
jclass CL_jhwloc_HwlocTopology;
jfieldID FID_jhwloc_HwlocTopology_handler;

/* HwlocObject */
jclass CL_jhwloc_HwlocObject;
jmethodID MID_HwlocObject_constructor;
jfieldID FID_jhwloc_HwlocObject_handler;
jfieldID FID_jhwloc_HwlocObject_os_index;
jfieldID FID_jhwloc_HwlocObject_name;
jfieldID FID_jhwloc_HwlocObject_depth;
jfieldID FID_jhwloc_HwlocObject_logical_index;
jfieldID FID_jhwloc_HwlocObject_gp_index;
jfieldID FID_jhwloc_HwlocObject_io_arity;
jfieldID FID_jhwloc_HwlocObject_total_memory;
jfieldID FID_jhwloc_HwlocObject_misc_arity;
jfieldID FID_jhwloc_HwlocObject_children;
jfieldID FID_jhwloc_HwlocObject_memory_children;
jfieldID FID_jhwloc_HwlocObject_attr;
jfieldID FID_jhwloc_HwlocObject_cpuset;
jfieldID FID_jhwloc_HwlocObject_nodeset;

/* HwlocObjectAttr */
jclass CL_jhwloc_HwlocObjectAttr;

/* HwlocObjectCacheAttr */
jclass CL_jhwloc_HwlocObjectCacheAttr;
jmethodID MID_HwlocObjectCacheAttr_constructor;

/* HwlocObjectNUMANodeAttr */
jclass CL_jhwloc_HwlocObjectNUMANodeAttr;
jmethodID MID_HwlocObjectNUMANodeAttr_constructor;

/* HwlocBitmap */
jclass CL_jhwloc_HwlocBitmap;
jfieldID FID_jhwloc_HwlocBitmap_handler;

/* HwlocCPUSet */
jclass CL_jhwloc_HwlocCPUSet;
jfieldID FID_jhwloc_HwlocCPUSet_handler;

/* HwlocNodeSet */
jclass CL_jhwloc_HwlocNodeSet;
jfieldID FID_jhwloc_HwlocNodeSet_handler;

/* String */
jmethodID MID_String_getBytes;

static void setHwlocAPI()
{
	api.jhwloc_get_api_version = &hwloc_get_api_version;
	api.jhwloc_compare_types = &hwloc_compare_types;
	api.jhwloc_topology_init = &hwloc_topology_init;
	api.jhwloc_topology_load = &hwloc_topology_load;
	api.jhwloc_topology_destroy = &hwloc_topology_destroy;
	api.jhwloc_topology_abi_check = &hwloc_topology_abi_check;
	api.jhwloc_topology_check = &hwloc_topology_check;
	api.jhwloc_topology_dup = &hwloc_topology_dup;
	api.jhwloc_topology_set_xml = &hwloc_topology_set_xml;
	api.jhwloc_topology_set_synthetic = &hwloc_topology_set_synthetic;
	api.jhwloc_topology_set_pid = &hwloc_topology_set_pid;
	api.jhwloc_topology_set_flags = &hwloc_topology_set_flags;
	api.jhwloc_topology_export_xml = &hwloc_topology_export_xml;
	api.jhwloc_topology_get_depth = &hwloc_topology_get_depth;
	api.jhwloc_get_memory_parents_depth = &hwloc_get_memory_parents_depth;
	api.jhwloc_get_depth_type = &hwloc_get_depth_type;
	api.jhwloc_get_type_depth = &hwloc_get_type_depth;
	api.jhwloc_get_type_or_above_depth = &hwloc_get_type_or_above_depth;
	api.jhwloc_get_type_or_below_depth = &hwloc_get_type_or_below_depth;
	api.jhwloc_get_nbobjs_by_depth = &hwloc_get_nbobjs_by_depth;
	api.jhwloc_get_nbobjs_by_type = &hwloc_get_nbobjs_by_type;
	api.jhwloc_topology_is_thissystem = &hwloc_topology_is_thissystem;
	api.jhwloc_topology_get_flags = &hwloc_topology_get_flags;
	api.jhwloc_get_obj_by_depth = &hwloc_get_obj_by_depth;
	api.jhwloc_get_obj_by_type = &hwloc_get_obj_by_type;
	api.jhwloc_obj_type_string = &hwloc_obj_type_string;
	api.jhwloc_get_root_obj = &hwloc_get_root_obj;
	api.jhwloc_get_next_obj_by_depth = &hwloc_get_next_obj_by_depth;
	api.jhwloc_get_next_obj_by_type = &hwloc_get_next_obj_by_type;
	api.jhwloc_obj_type_is_normal = &hwloc_obj_type_is_normal;
	api.jhwloc_obj_type_is_io = &hwloc_obj_type_is_io;
	api.jhwloc_obj_type_is_memory = &hwloc_obj_type_is_memory;
	api.jhwloc_obj_type_is_cache = &hwloc_obj_type_is_cache;
	api.jhwloc_obj_type_is_dcache = &hwloc_obj_type_is_dcache;
	api.jhwloc_obj_type_is_icache = &hwloc_obj_type_is_icache;
	api.jhwloc_bitmap_alloc = &hwloc_bitmap_alloc;
	api.jhwloc_bitmap_alloc_full = &hwloc_bitmap_alloc_full;
	api.jhwloc_bitmap_copy = &hwloc_bitmap_copy;
	api.jhwloc_bitmap_set = &hwloc_bitmap_set;
	api.jhwloc_bitmap_set_range = &hwloc_bitmap_set_range;
	api.jhwloc_bitmap_clr = &hwloc_bitmap_clr;
	api.jhwloc_bitmap_clr_range = &hwloc_bitmap_clr_range;
	api.jhwloc_bitmap_only = &hwloc_bitmap_only;
	api.jhwloc_bitmap_allbut = &hwloc_bitmap_allbut;	
	api.jhwloc_bitmap_isincluded = &hwloc_bitmap_isincluded;
	api.jhwloc_bitmap_free = &hwloc_bitmap_free;
	api.jhwloc_bitmap_dup = &hwloc_bitmap_dup;
	api.jhwloc_bitmap_or = &hwloc_bitmap_or;
	api.jhwloc_bitmap_and = &hwloc_bitmap_and;
	api.jhwloc_bitmap_andnot = &hwloc_bitmap_andnot;
	api.jhwloc_bitmap_xor = &hwloc_bitmap_xor;
	api.jhwloc_bitmap_not = &hwloc_bitmap_not;
	api.jhwloc_bitmap_singlify = &hwloc_bitmap_singlify;
	api.jhwloc_bitmap_intersects = &hwloc_bitmap_intersects;
	api.jhwloc_bitmap_isequal = &hwloc_bitmap_isequal;
	api.jhwloc_bitmap_compare = &hwloc_bitmap_compare;
	api.jhwloc_bitmap_compare_first = &hwloc_bitmap_compare_first;
	api.jhwloc_bitmap_zero = &hwloc_bitmap_zero;
	api.jhwloc_bitmap_fill = &hwloc_bitmap_fill;
	api.jhwloc_bitmap_isset = &hwloc_bitmap_isset;
	api.jhwloc_bitmap_iszero = &hwloc_bitmap_iszero;
	api.jhwloc_bitmap_isfull = &hwloc_bitmap_isfull;
	api.jhwloc_bitmap_asprintf = &hwloc_bitmap_asprintf;
	api.jhwloc_bitmap_list_asprintf = &hwloc_bitmap_list_asprintf;
	api.jhwloc_bitmap_first = &hwloc_bitmap_first;
	api.jhwloc_bitmap_first_unset = &hwloc_bitmap_first_unset;
	api.jhwloc_bitmap_last = &hwloc_bitmap_last;
	api.jhwloc_bitmap_last_unset = &hwloc_bitmap_last_unset;
	api.jhwloc_bitmap_next = &hwloc_bitmap_next;
	api.jhwloc_bitmap_next_unset = &hwloc_bitmap_next_unset;
	api.jhwloc_bitmap_weight = &hwloc_bitmap_weight;
	api.jhwloc_set_cpubind = &hwloc_set_cpubind;
	api.jhwloc_set_proc_cpubind = &hwloc_set_proc_cpubind;
	api.jhwloc_set_thread_cpubind = &hwloc_set_thread_cpubind;
	api.jhwloc_get_cpubind = &hwloc_get_cpubind;
	api.jhwloc_get_proc_cpubind = &hwloc_get_proc_cpubind;
	api.jhwloc_get_thread_cpubind = &hwloc_get_thread_cpubind;
	api.jhwloc_get_last_cpu_location = &hwloc_get_last_cpu_location;
	api.jhwloc_get_proc_last_cpu_location = &hwloc_get_proc_last_cpu_location;
	api.jhwloc_topology_get_complete_cpuset = &hwloc_topology_get_complete_cpuset;
	api.jhwloc_topology_get_topology_cpuset = &hwloc_topology_get_topology_cpuset;
	api.jhwloc_topology_get_allowed_cpuset = &hwloc_topology_get_allowed_cpuset;
	api.jhwloc_topology_get_complete_nodeset = &hwloc_topology_get_complete_nodeset;
	api.jhwloc_topology_get_topology_nodeset = &hwloc_topology_get_topology_nodeset;
	api.jhwloc_topology_get_allowed_nodeset = &hwloc_topology_get_allowed_nodeset;
	api.jhwloc_topology_set_type_filter = &hwloc_topology_set_type_filter;
	api.jhwloc_topology_get_type_filter = &hwloc_topology_get_type_filter;
	api.jhwloc_topology_set_all_types_filter = &hwloc_topology_set_all_types_filter;
	api.jhwloc_topology_set_cache_types_filter = &hwloc_topology_set_cache_types_filter;
	api.jhwloc_topology_set_icache_types_filter = &hwloc_topology_set_icache_types_filter;
	api.jhwloc_topology_set_io_types_filter = &hwloc_topology_set_io_types_filter;
}

JNIEXPORT jint JNICALL JNI_OnLoad(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;
	jclass cls;

	cached_jvm = jvm; /* cache the JavaVM pointer */

	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2))
		return JNI_ERR; /* JNI version not supported */

	unsigned version = hwloc_get_api_version();

	if ((version >> 16) != (HWLOC_API_VERSION >> 16)) {
		fprintf(stderr, "jhwloc was compiled for hwloc API 0x%x but running on library API 0x%x.\n"
		"You may need to point LD_LIBRARY_PATH to the right hwloc library.\n"
		"Aborting since the new ABI is not backward compatible.\n", HWLOC_API_VERSION, version);
		return JNI_ERR;
	}

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocTopology");

	if (cls == NULL)
		return JNI_ERR;

  	CL_jhwloc_HwlocTopology = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocTopology == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	FID_jhwloc_HwlocTopology_handler = (*env)->GetFieldID(env,CL_jhwloc_HwlocTopology,"handler","J");

	if (FID_jhwloc_HwlocTopology_handler == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocObject");

	if (cls == NULL)
		return JNI_ERR;

  	CL_jhwloc_HwlocObject = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocObject == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	MID_HwlocObject_constructor = (*env)->GetMethodID(env, CL_jhwloc_HwlocObject, "<init>", "(Les/udc/gac/jhwloc/HwlocTopology;JIIIJ)V");

	if (MID_HwlocObject_constructor == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_handler = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"handler","J");

	if (FID_jhwloc_HwlocObject_handler == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_os_index = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"os_index","I");

	if (FID_jhwloc_HwlocObject_os_index == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_gp_index = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"gp_index","J");

		if (FID_jhwloc_HwlocObject_gp_index == NULL)
			return JNI_ERR;

	FID_jhwloc_HwlocObject_name = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"name","Ljava/lang/String;");

	if (FID_jhwloc_HwlocObject_name == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_depth = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"depth","I");

	if (FID_jhwloc_HwlocObject_depth == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_logical_index = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"logical_index","I");

	if (FID_jhwloc_HwlocObject_logical_index == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_io_arity = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"io_arity","I");

	if (FID_jhwloc_HwlocObject_io_arity == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_misc_arity = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"misc_arity","I");

	if (FID_jhwloc_HwlocObject_misc_arity == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_total_memory = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"total_memory","J");

	if (FID_jhwloc_HwlocObject_total_memory == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_children = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"children","[Les/udc/gac/jhwloc/HwlocObject;");

	if (FID_jhwloc_HwlocObject_children == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_memory_children = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"memory_children","[Les/udc/gac/jhwloc/HwlocObject;");

		if (FID_jhwloc_HwlocObject_memory_children == NULL)
			return JNI_ERR;

	FID_jhwloc_HwlocObject_attr = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"attr","Les/udc/gac/jhwloc/HwlocObjectAttr;");

		if (FID_jhwloc_HwlocObject_attr == NULL)
			return JNI_ERR;

	FID_jhwloc_HwlocObject_cpuset = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"cpuset","Les/udc/gac/jhwloc/HwlocCPUSet;");

	if (FID_jhwloc_HwlocObject_cpuset == NULL)
		return JNI_ERR;

	FID_jhwloc_HwlocObject_nodeset = (*env)->GetFieldID(env,CL_jhwloc_HwlocObject,"nodeset","Les/udc/gac/jhwloc/HwlocNodeSet;");

	if (FID_jhwloc_HwlocObject_nodeset == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocBitmap");

	if (cls == NULL)
		return JNI_ERR;

  	CL_jhwloc_HwlocBitmap = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocBitmap == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	FID_jhwloc_HwlocBitmap_handler = (*env)->GetFieldID(env,CL_jhwloc_HwlocBitmap,"handler","J");

	if (FID_jhwloc_HwlocBitmap_handler == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocCPUSet");

	if (cls == NULL)
		return JNI_ERR;

  	CL_jhwloc_HwlocCPUSet = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocCPUSet == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	FID_jhwloc_HwlocCPUSet_handler = (*env)->GetFieldID(env,CL_jhwloc_HwlocCPUSet,"handler","J");

	if (FID_jhwloc_HwlocCPUSet_handler == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocNodeSet");

	if (cls == NULL)
		return JNI_ERR;

  	CL_jhwloc_HwlocNodeSet = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocNodeSet == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	FID_jhwloc_HwlocNodeSet_handler = (*env)->GetFieldID(env,CL_jhwloc_HwlocNodeSet,"handler","J");

	if (FID_jhwloc_HwlocNodeSet_handler == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocObjectAttr");

	if (cls == NULL)
		return JNI_ERR;

	CL_jhwloc_HwlocObjectAttr = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocObjectAttr == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocObjectCacheAttr");

	if (cls == NULL)
		return JNI_ERR;

	CL_jhwloc_HwlocObjectCacheAttr = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocObjectCacheAttr == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	MID_HwlocObjectCacheAttr_constructor = (*env)->GetMethodID(env, CL_jhwloc_HwlocObjectCacheAttr, "<init>", "(IIJIII)V");

	if (MID_HwlocObjectCacheAttr_constructor == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"es/udc/gac/jhwloc/HwlocObjectNUMANodeAttr");

	if (cls == NULL)
		return JNI_ERR;

	CL_jhwloc_HwlocObjectNUMANodeAttr = (*env)->NewWeakGlobalRef(env, cls);

	if (CL_jhwloc_HwlocObjectNUMANodeAttr == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	MID_HwlocObjectNUMANodeAttr_constructor = (*env)->GetMethodID(env, CL_jhwloc_HwlocObjectNUMANodeAttr, "<init>", "(IJ)V");

	if (MID_HwlocObjectNUMANodeAttr_constructor == NULL)
		return JNI_ERR;

	cls = (*env)->FindClass(env,"java/lang/String");

	if (cls == NULL)
		return JNI_ERR;

	MID_String_getBytes = (*env)->GetMethodID(env, cls, "getBytes", "()[B");

	if (MID_String_getBytes == NULL)
		return JNI_ERR;

	(*env)->DeleteLocalRef(env, cls);

	setHwlocAPI();

	return JNI_VERSION_1_2;
}


JNIEXPORT void JNICALL JNI_OnUnload(JavaVM *jvm, void *reserved)
{
	JNIEnv *env;

	if ((*jvm)->GetEnv(jvm, (void **)&env, JNI_VERSION_1_2))
		return;

	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocTopology);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocObject);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocObjectAttr);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocObjectCacheAttr);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocObjectNUMANodeAttr);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocBitmap);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocCPUSet);
	(*env)->DeleteWeakGlobalRef(env, CL_jhwloc_HwlocNodeSet);
}

void ThrowByName(JNIEnv *env, const char *name, const char *msg)
{
	jclass cls = (*env)->FindClass(env, name);

	/* if cls is NULL, an exception has already been thrown */
	if (cls != NULL)
		(*env)->ThrowNew(env, cls, msg);

	/* free the local ref */
	(*env)->DeleteLocalRef(env, cls);
}

hwloc_obj_type_t GetHwlocObjectTypeNative(JNIEnv *env, int jhwloc_obj_type)
{
	switch(jhwloc_obj_type)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_MACHINE_TYPE:	return HWLOC_OBJ_MACHINE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PACKAGE_TYPE:	return HWLOC_OBJ_PACKAGE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CORE_TYPE:	return HWLOC_OBJ_CORE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PU_TYPE:	return HWLOC_OBJ_PU;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L1CACHE_TYPE:	return HWLOC_OBJ_L1CACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L2CACHE_TYPE:	return HWLOC_OBJ_L2CACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L3CACHE_TYPE:	return HWLOC_OBJ_L3CACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L4CACHE_TYPE:	return HWLOC_OBJ_L4CACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L5CACHE_TYPE:	return HWLOC_OBJ_L5CACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L1ICACHE_TYPE:	return HWLOC_OBJ_L1ICACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L2ICACHE_TYPE:	return HWLOC_OBJ_L2ICACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L3ICACHE_TYPE:	return HWLOC_OBJ_L3ICACHE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_GROUP_TYPE: 	return HWLOC_OBJ_GROUP;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_NUMANODE_TYPE:	return HWLOC_OBJ_NUMANODE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_BRIDGE_TYPE: 	return HWLOC_OBJ_BRIDGE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PCI_DEVICE_TYPE:return HWLOC_OBJ_PCI_DEVICE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_OS_DEVICE_TYPE:return HWLOC_OBJ_OS_DEVICE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_MISC_TYPE:	return HWLOC_OBJ_MISC;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_OBJ_UNKNOWN_TYPE: "+jhwloc_obj_type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_UNKNOWN_TYPE;
}

int GetHwlocObjectTypeJava(JNIEnv *env, hwloc_obj_type_t type)
{
	switch(type)
	{
		case HWLOC_OBJ_MACHINE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_MACHINE_TYPE;
		case HWLOC_OBJ_PACKAGE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PACKAGE_TYPE;
		case HWLOC_OBJ_CORE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CORE_TYPE;
		case HWLOC_OBJ_PU:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PU_TYPE;
		case HWLOC_OBJ_L1CACHE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L1CACHE_TYPE;
		case HWLOC_OBJ_L2CACHE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L2CACHE_TYPE;
		case HWLOC_OBJ_L3CACHE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L3CACHE_TYPE;
		case HWLOC_OBJ_L4CACHE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L4CACHE_TYPE;
		case HWLOC_OBJ_L5CACHE:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L5CACHE_TYPE;
		case HWLOC_OBJ_L1ICACHE:	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L1ICACHE_TYPE;
		case HWLOC_OBJ_L2ICACHE:	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L2ICACHE_TYPE;
		case HWLOC_OBJ_L3ICACHE:	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_L3ICACHE_TYPE;
		case HWLOC_OBJ_GROUP: 		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_GROUP_TYPE;
		case HWLOC_OBJ_NUMANODE: 	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_NUMANODE_TYPE;
		case HWLOC_OBJ_BRIDGE: 		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_BRIDGE_TYPE;
		case HWLOC_OBJ_PCI_DEVICE: 	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_PCI_DEVICE_TYPE;
		case HWLOC_OBJ_OS_DEVICE: 	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_OS_DEVICE_TYPE;
		case HWLOC_OBJ_MISC: 		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_MISC_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_OBJ_UNKNOWN: "+type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_UNKNOWN_TYPE;
}

hwloc_obj_cache_type_t GetHwlocObjectCacheTypeNative(JNIEnv *env, int jhwloc_obj_cache_type)
{
	switch(jhwloc_obj_cache_type)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_UNIFIED_TYPE:	return HWLOC_OBJ_CACHE_UNIFIED;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_DATA_TYPE:	return HWLOC_OBJ_CACHE_DATA;
		case es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_INSTRUCTION_TYPE:return HWLOC_OBJ_CACHE_INSTRUCTION;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_OBJ_CACHE_UNKNOWN_TYPE: "+jhwloc_obj_cache_type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_UNKNOWN_TYPE;
}

int GetHwlocObjectCacheTypeJava(JNIEnv *env, hwloc_obj_cache_type_t type)
{
	switch(type)
	{
		case HWLOC_OBJ_CACHE_UNIFIED:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_UNIFIED_TYPE;
		case HWLOC_OBJ_CACHE_DATA:		return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_DATA_TYPE;
		case HWLOC_OBJ_CACHE_INSTRUCTION:	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_INSTRUCTION_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_OBJ_CACHE_UNKNOWN: "+type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_OBJ_CACHE_UNKNOWN_TYPE;
}

int GetHwlocCompareTypesJava(JNIEnv *env, enum hwloc_compare_types_e type)
{
	switch(type)
	{
		case HWLOC_TYPE_UNORDERED :	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_UNORDERED_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TYPE_UNKNOWN: "+type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_UNKNOWN_TYPE;
}

int GetHwlocGetTypeDepthJava(JNIEnv *env, enum hwloc_get_type_depth_e type)
{
	switch(type)
	{
		case HWLOC_TYPE_DEPTH_MULTIPLE:		return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_MULTIPLE_TYPE;
		case HWLOC_TYPE_DEPTH_NUMANODE:		return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_NUMANODE_TYPE;
		case HWLOC_TYPE_DEPTH_BRIDGE:		return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_BRIDGE_TYPE;
		case HWLOC_TYPE_DEPTH_PCI_DEVICE:	return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_PCI_DEVICE_TYPE;
		case HWLOC_TYPE_DEPTH_OS_DEVICE:	return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_OS_DEVICE_TYPE;
		case HWLOC_TYPE_DEPTH_MISC:			return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_MISC_TYPE;
		case HWLOC_TYPE_DEPTH_UNKNOWN:		return es_udc_gac_jhwloc_HwlocEnumTypes_HWLOC_TYPE_DEPTH_UNKNOWN_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TYPE_DEPTH_UNKNOWN: "+type);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_UNKNOWN_TYPE;
}

int GetHwlocTypeFilterJava(JNIEnv *env, enum hwloc_type_filter_e filter)
{
	switch(filter)
	{
		case HWLOC_TYPE_FILTER_KEEP_ALL:		return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_ALL_TYPE;
		case HWLOC_TYPE_FILTER_KEEP_NONE:		return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_NONE_TYPE;
		case HWLOC_TYPE_FILTER_KEEP_STRUCTURE:	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_STRUCTURE_TYPE;
		case HWLOC_TYPE_FILTER_KEEP_IMPORTANT:	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_IMPORTANT_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TYPE_FILTER_UNKNOWN: "+filter);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_UNKNOWN_TYPE;
}

enum hwloc_type_filter_e GetHwlocTypeFilterNative(JNIEnv *env, int filter)
{
	switch(filter)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_ALL_TYPE:	return HWLOC_TYPE_FILTER_KEEP_ALL;
		case es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_NONE_TYPE:	return HWLOC_TYPE_FILTER_KEEP_NONE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_STRUCTURE_TYPE:	return HWLOC_TYPE_FILTER_KEEP_STRUCTURE;
		case es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_KEEP_IMPORTANT_TYPE:	return HWLOC_TYPE_FILTER_KEEP_IMPORTANT;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TYPE_FILTER_UNKNOWN: "+filter);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_TYPE_FILTER_UNKNOWN_TYPE;
}

char* GetStringNative(JNIEnv *env, jstring jstr)
{
	jbyteArray bytes = 0;
	char *result = NULL;

	if ((*env)->EnsureLocalCapacity(env, 2) < 0)
		return NULL; /* out of memory error */

	bytes = (*env)->CallObjectMethod(env, jstr, MID_String_getBytes);
	
	if ((*env)->ExceptionOccurred(env))
	{
		(*env)->ExceptionDescribe(env);
		return NULL;
	}

	jint len = (*env)->GetArrayLength(env, bytes);
	result = (char *)malloc(len + 1);

	if (result == NULL)
	{
		ThrowByName(env, "java/lang/OutOfMemoryError", "");
		(*env)->DeleteLocalRef(env, bytes);
		return NULL;
	}

	(*env)->GetByteArrayRegion(env, bytes, 0, len, (jbyte *)result);
	result[len] = '\0'; /* NULL-terminate */

	(*env)->DeleteLocalRef(env, bytes);

	return result;
}

jstring GetStringJava(JNIEnv *env, const char *str)
{
	jstring new_string = (*env)->NewStringUTF(env, str);
	return new_string;
}

jobject CreateHwlocObject(JNIEnv *env, jobject topo, hwloc_obj_t obj)
{
	int i;
	jobject child;
	jobjectArray children;
	jobjectArray memory_children;
	jobject attr;
	jobject cpuset;
	jobject nodeset;
	jlong parent;
	int jhwloc_obj_type = GetHwlocObjectTypeJava(env, obj->type);
	jstring name = GetStringJava(env, obj->name);

	if (obj->parent != NULL)
		parent = (jlong) obj->parent;
	else
		parent = -1;

	jobject new_hwloc_obj = (*env)->NewObject(env, CL_jhwloc_HwlocObject, MID_HwlocObject_constructor,
			topo, (jlong) obj, jhwloc_obj_type, obj->arity, obj->memory_arity, parent);

	(*env)->SetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_name, name);
	(*env)->SetIntField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_depth, obj->depth);
	(*env)->SetIntField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_os_index, obj->os_index);
	(*env)->SetIntField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_logical_index, obj->logical_index);
	(*env)->SetLongField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_gp_index, (jlong)obj->gp_index);
	(*env)->SetIntField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_io_arity, obj->io_arity);
	(*env)->SetLongField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_total_memory, (jlong)obj->total_memory);
	(*env)->SetIntField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_misc_arity, obj->misc_arity);

	cpuset = (*env)->GetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_cpuset);
	nodeset = (*env)->GetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_nodeset);
	(*env)->SetLongField(env, cpuset, FID_jhwloc_HwlocCPUSet_handler, (jlong)obj->cpuset);
	(*env)->SetLongField(env, nodeset, FID_jhwloc_HwlocNodeSet_handler, (jlong)obj->nodeset);

	if (obj->attr != NULL) {
		if (hwloc_obj_type_is_cache(obj->type)) {
			attr = (*env)->NewObject(env, CL_jhwloc_HwlocObjectCacheAttr, MID_HwlocObjectCacheAttr_constructor,
					jhwloc_obj_type,
					GetHwlocObjectCacheTypeJava(env, obj->attr->cache.type),
					obj->attr->cache.size,
					obj->attr->cache.depth,
					obj->attr->cache.linesize,
					obj->attr->cache.associativity);

			(*env)->SetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_attr, attr);
		} else if (hwloc_obj_type_is_memory(obj->type)) {
			attr = (*env)->NewObject(env, CL_jhwloc_HwlocObjectNUMANodeAttr, MID_HwlocObjectNUMANodeAttr_constructor,
					jhwloc_obj_type,
					obj->attr->numanode.local_memory);

			(*env)->SetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_attr, attr);
		}
	}

	if (obj->arity > 0) {
		children = (*env)->GetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_children);

		for (i=0; i<obj->arity; i++) {
			child = CreateHwlocObject(env, topo, obj->children[i]);
			(*env)->SetObjectArrayElement(env, children, i, child);
		}
	}

	if (obj->memory_arity > 0) {
			memory_children = (*env)->GetObjectField(env, new_hwloc_obj, FID_jhwloc_HwlocObject_memory_children);
			hwloc_obj_t memory_child = obj->memory_first_child;

			for (i=0; i<obj->memory_arity; i++) {
				child = CreateHwlocObject(env, topo, memory_child);
				(*env)->SetObjectArrayElement(env, memory_children, i, child);
				memory_child = memory_child->next_sibling;
			}
	}

	return new_hwloc_obj;
}
