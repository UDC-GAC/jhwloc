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
#ifndef _Included_jhwloc_util_h
#define _Included_jhwloc_util_h

#include "es_udc_gac_jhwloc_HWLOC.h"
#include "es_udc_gac_jhwloc_HwlocEnumTypes.h"
#include <jni.h>
#include <stdlib.h>
#include <hwloc.h>
#include <errno.h>

/* Java hwloc library API */
typedef struct jhwloc_api {
	unsigned (*jhwloc_get_api_version)(void);
	int (*jhwloc_compare_types)(hwloc_obj_type_t type1, hwloc_obj_type_t type2);
	int (*jhwloc_topology_init)(hwloc_topology_t *topology);
	int (*jhwloc_topology_load)(hwloc_topology_t topology);
	void (*jhwloc_topology_destroy)(hwloc_topology_t topology);
	int (*jhwloc_topology_dup)(hwloc_topology_t *newtopology, hwloc_topology_t oldtopology);
	int (*jhwloc_topology_abi_check)(hwloc_topology_t topology);
	void (*jhwloc_topology_check)(hwloc_topology_t topology);
	int (*jhwloc_topology_set_xml)(hwloc_topology_t topology, const char *xmlpath);
	int (*jhwloc_topology_set_synthetic)(hwloc_topology_t topology, const char *description);
	int (*jhwloc_topology_set_pid)(hwloc_topology_t topology, hwloc_pid_t pid);
	int (*jhwloc_topology_set_flags)(hwloc_topology_t topology, unsigned long flags);
	int (*jhwloc_topology_export_xml)(hwloc_topology_t topology, const char *xmlpath, unsigned long flags);
	int (*jhwloc_topology_get_depth)(hwloc_topology_t topology);
	hwloc_obj_type_t (*jhwloc_get_depth_type)(hwloc_topology_t topology, int depth);
	int (*jhwloc_get_memory_parents_depth)(hwloc_topology_t topology);
	int (*jhwloc_get_type_depth)(hwloc_topology_t topology, hwloc_obj_type_t type);
	int (*jhwloc_get_type_or_above_depth)(hwloc_topology_t topology, hwloc_obj_type_t type);
	int (*jhwloc_get_type_or_below_depth)(hwloc_topology_t topology, hwloc_obj_type_t type);
	unsigned (*jhwloc_get_nbobjs_by_depth)(hwloc_topology_t topology, int depth);
	int (*jhwloc_get_nbobjs_by_type)(hwloc_topology_t topology, hwloc_obj_type_t type);
	int (*jhwloc_topology_is_thissystem)(hwloc_topology_t topology);
	unsigned long (*jhwloc_topology_get_flags)(hwloc_topology_t topology);
	hwloc_obj_t (*jhwloc_get_obj_by_depth)(hwloc_topology_t topology, int depth, unsigned idx);
	hwloc_obj_t (*jhwloc_get_numanode_obj_by_os_index)(hwloc_topology_t topology, unsigned os_index);
	hwloc_obj_t (*jhwloc_get_pu_obj_by_os_index)(hwloc_topology_t topology, unsigned os_index);
	hwloc_obj_t (*jhwloc_get_obj_by_type)(hwloc_topology_t topology, hwloc_obj_type_t type, unsigned idx);
	const char* (*jhwloc_obj_type_string)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_normal)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_io)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_memory)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_cache)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_dcache)(hwloc_obj_type_t type);
	int (*jhwloc_obj_type_is_icache)(hwloc_obj_type_t type);
	hwloc_obj_t (*jhwloc_get_root_obj)(hwloc_topology_t topology);
	hwloc_obj_t (*jhwloc_get_next_obj_by_depth)(hwloc_topology_t topology, int depth, hwloc_obj_t prev);
	hwloc_obj_t (*jhwloc_get_next_obj_by_type)(hwloc_topology_t topology, hwloc_obj_type_t type, hwloc_obj_t prev);
	hwloc_bitmap_t (*jhwloc_bitmap_alloc)(void);
	hwloc_bitmap_t (*jhwloc_bitmap_alloc_full)(void);
	int (*jhwloc_bitmap_copy)(hwloc_bitmap_t dst, hwloc_const_bitmap_t src);
	int (*jhwloc_bitmap_set)(hwloc_bitmap_t bitmap, unsigned id);
	int (*jhwloc_bitmap_set_range)(hwloc_bitmap_t bitmap, unsigned being, int end);
	int (*jhwloc_bitmap_clr)(hwloc_bitmap_t bitmap, unsigned id);
	int (*jhwloc_bitmap_clr_range)(hwloc_bitmap_t bitmap, unsigned being, int end);
	int (*jhwloc_bitmap_only)(hwloc_bitmap_t bitmap, unsigned id);
	int (*jhwloc_bitmap_allbut)(hwloc_bitmap_t bitmap, unsigned id);
	int (*jhwloc_bitmap_isincluded)(hwloc_const_bitmap_t sub_bitmap, hwloc_const_bitmap_t super_bitmap);
	void (*jhwloc_bitmap_free)(hwloc_bitmap_t bitmap);
	hwloc_bitmap_t (*jhwloc_bitmap_dup)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_or)(hwloc_bitmap_t res, hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_and)(hwloc_bitmap_t res, hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_andnot)(hwloc_bitmap_t res, hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_xor)(hwloc_bitmap_t res, hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_not)(hwloc_bitmap_t res, hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_singlify)(hwloc_bitmap_t bitmap);
	int (*jhwloc_bitmap_intersects)(hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_isequal)(hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_compare)(hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	int (*jhwloc_bitmap_compare_first)(hwloc_const_bitmap_t bitmap1, hwloc_const_bitmap_t bitmap2);
	void (*jhwloc_bitmap_zero)(hwloc_bitmap_t bitmap);
	void (*jhwloc_bitmap_fill)(hwloc_bitmap_t bitmap);
	int (*jhwloc_bitmap_isset)(hwloc_const_bitmap_t bitmap, unsigned id);
	int (*jhwloc_bitmap_iszero)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_isfull)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_asprintf)(char **strp, hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_list_asprintf)(char **strp, hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_taskset_asprintf)(char **strp, hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_first)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_first_unset)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_last)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_last_unset)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_bitmap_next)(hwloc_const_bitmap_t bitmap, int prev);
	int (*jhwloc_bitmap_next_unset)(hwloc_const_bitmap_t bitmap, int prev);
	int (*jhwloc_bitmap_weight)(hwloc_const_bitmap_t bitmap);
	int (*jhwloc_set_cpubind)(hwloc_topology_t topology, hwloc_const_cpuset_t set, int flags);
	int (*jhwloc_set_proc_cpubind)(hwloc_topology_t topology, hwloc_pid_t pid, hwloc_const_cpuset_t set, int flags);
	int (*jhwloc_set_thread_cpubind)(hwloc_topology_t topology, hwloc_thread_t tid, hwloc_const_cpuset_t set, int flags);
	int (*jhwloc_get_cpubind)(hwloc_topology_t topology, hwloc_cpuset_t set, int flags);
	int (*jhwloc_get_proc_cpubind)(hwloc_topology_t topology, hwloc_pid_t pid, hwloc_cpuset_t set, int flags);
	int (*jhwloc_get_thread_cpubind)(hwloc_topology_t topology, hwloc_thread_t tid, hwloc_cpuset_t set, int flags);
	int (*jhwloc_get_last_cpu_location)(hwloc_topology_t topology, hwloc_cpuset_t set, int flags);
	int (*jhwloc_get_proc_last_cpu_location)(hwloc_topology_t topology, hwloc_pid_t pid, hwloc_cpuset_t set, int flags);
	hwloc_const_cpuset_t (*jhwloc_topology_get_complete_cpuset)(hwloc_topology_t topology);
	hwloc_const_cpuset_t (*jhwloc_topology_get_topology_cpuset)(hwloc_topology_t topology);
	hwloc_const_cpuset_t (*jhwloc_topology_get_allowed_cpuset)(hwloc_topology_t topology);
	hwloc_const_nodeset_t (*jhwloc_topology_get_complete_nodeset)(hwloc_topology_t topology);
	hwloc_const_nodeset_t (*jhwloc_topology_get_topology_nodeset)(hwloc_topology_t topology);
	hwloc_const_nodeset_t (*jhwloc_topology_get_allowed_nodeset)(hwloc_topology_t topology);
	int (*jhwloc_topology_set_type_filter)(hwloc_topology_t topology, hwloc_obj_type_t type, enum hwloc_type_filter_e filter);
	int (*jhwloc_topology_get_type_filter)(hwloc_topology_t topology, hwloc_obj_type_t type, enum hwloc_type_filter_e *filter);
	int (*jhwloc_topology_set_all_types_filter)(hwloc_topology_t topology, enum hwloc_type_filter_e filter);
	int (*jhwloc_topology_set_cache_types_filter)(hwloc_topology_t topology, enum hwloc_type_filter_e filter);
	int (*jhwloc_topology_set_icache_types_filter)(hwloc_topology_t topology, enum hwloc_type_filter_e filter);
	int (*jhwloc_topology_set_io_types_filter)(hwloc_topology_t topology, enum hwloc_type_filter_e filter);
	int (*jhwloc_get_membind)(hwloc_topology_t topology, hwloc_bitmap_t bitmap, hwloc_membind_policy_t *policy, int flags);
	int (*jhwloc_get_proc_membind)(hwloc_topology_t topology, hwloc_pid_t pid, hwloc_bitmap_t bitmap, hwloc_membind_policy_t *policy, int flags);
	int (*jhwloc_set_membind)(hwloc_topology_t topology, hwloc_const_bitmap_t bitmap, hwloc_membind_policy_t policy, int flags);
	int (*jhwloc_set_proc_membind)(hwloc_topology_t topology, hwloc_pid_t pid, hwloc_const_bitmap_t bitmap, hwloc_membind_policy_t policy, int flags);
	int (*jhwloc_cpuset_from_nodeset)(hwloc_topology_t topology, hwloc_cpuset_t cpuset, hwloc_const_nodeset_t nodeset);
	int (*jhwloc_cpuset_to_nodeset)(hwloc_topology_t topology, hwloc_const_cpuset_t cpuset, hwloc_nodeset_t nodeset);
} jhwloc_api_t;

void ThrowByName(JNIEnv *env, const char *name, const char *msg);
hwloc_obj_type_t GetHwlocObjectTypeNative(JNIEnv *env, int jhwloc_obj_type);
int GetHwlocObjectTypeJava(JNIEnv *env, hwloc_obj_type_t type);
hwloc_obj_cache_type_t GetHwlocObjectCacheTypeNative(JNIEnv *env, int jhwloc_obj_cache_type);
int GetHwlocObjectCacheTypeJava(JNIEnv *env, hwloc_obj_cache_type_t type);
int GetHwlocMEMBindPolicyTypeNative(JNIEnv *env, int jhwloc_policy);
int GetHwlocMEMBindPolicyTypeJava(JNIEnv *env, hwloc_membind_policy_t hwloc_policy);
int GetHwlocCompareTypesJava(JNIEnv *env, enum hwloc_compare_types_e type);
int GetHwlocGetTypeDepthJava(JNIEnv *env, enum hwloc_get_type_depth_e type);
int GetHwlocTypeFilterJava(JNIEnv *env, enum hwloc_type_filter_e filter);
enum hwloc_type_filter_e GetHwlocTypeFilterNative(JNIEnv *env, int filter);
char* GetStringNative(JNIEnv *env, jstring jstr);
jstring GetStringJava(JNIEnv *env, const char *str);
jobject CreateHwlocObject(JNIEnv *env, jobject topo, hwloc_obj_t obj);

#endif
