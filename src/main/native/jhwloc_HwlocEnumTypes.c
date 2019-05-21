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
#include "es_udc_gac_jhwloc_HwlocEnumTypes.h"
#include "jhwloc_util.h"

extern jhwloc_api_t api;

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    java2hwloc_cpubindflag
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_java2hwloc_1cpubindflag
  (JNIEnv *env, jclass this, jint jhwloc_flag)
{
	switch(jhwloc_flag)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_PROCESS_TYPE:              return HWLOC_CPUBIND_PROCESS;
		case es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_THREAD_TYPE:               return HWLOC_CPUBIND_THREAD;
        case es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_STRICT_TYPE:               return HWLOC_CPUBIND_STRICT;
        case es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_NOMEMBIND_TYPE:            return HWLOC_CPUBIND_NOMEMBIND;
        default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_CPUBIND_UNKNOWN_TYPE: "+jhwloc_flag);
    }
    return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_UNKNOWN_TYPE;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    hwloc2java_cpubindflag
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_hwloc2java_1cpubindflag
  (JNIEnv *env, jclass this, jint hwloc_flag)
{
	switch(hwloc_flag)
	{
		case HWLOC_CPUBIND_PROCESS:	return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_PROCESS_TYPE;
        case HWLOC_CPUBIND_THREAD:	return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_THREAD_TYPE;
        case HWLOC_CPUBIND_STRICT:	return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_STRICT_TYPE;
        case HWLOC_CPUBIND_NOMEMBIND:	return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_NOMEMBIND_TYPE;
        default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_CPUBIND_UNKNOWN: "+hwloc_flag);
     }
     return es_udc_gac_jhwloc_HwlocEnumTypes_CPUBIND_UNKNOWN_TYPE;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    java2hwloc_membindflag
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_java2hwloc_1membindflag
  (JNIEnv *env, jclass this, jint jhwloc_flag)
{
	switch(jhwloc_flag)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_PROCESS_TYPE:     return HWLOC_MEMBIND_PROCESS;
	    case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_THREAD_TYPE:      return HWLOC_MEMBIND_THREAD;
	    case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_STRICT_TYPE:      return HWLOC_MEMBIND_STRICT;
	    case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_MIGRATE_TYPE:		return HWLOC_MEMBIND_MIGRATE;
	    case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_NOCPUBIND_TYPE:   return HWLOC_MEMBIND_NOCPUBIND;
	    case es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_BYNODESET_TYPE:	return HWLOC_MEMBIND_BYNODESET;
	    default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_MEMBIND_UNKNOWN_TYPE: "+jhwloc_flag);
	}
	return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_UNKNOWN_TYPE;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    hwloc2java_membindflag
 * Signature: (I)I
 */
JNIEXPORT jint JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_hwloc2java_1membindflag
  (JNIEnv *env, jclass this, jint hwloc_flag)
{
	switch(hwloc_flag)
	{
		case HWLOC_MEMBIND_PROCESS:		return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_PROCESS_TYPE;
        case HWLOC_MEMBIND_THREAD:		return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_THREAD_TYPE;
        case HWLOC_MEMBIND_STRICT:		return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_STRICT_TYPE;
        case HWLOC_MEMBIND_MIGRATE:		return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_MIGRATE_TYPE;
        case HWLOC_MEMBIND_NOCPUBIND:	return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_NOCPUBIND_TYPE;
        case HWLOC_MEMBIND_BYNODESET:	return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_BYNODESET_TYPE;
        default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_MEMBIND_UNKNOWN: "+hwloc_flag);
     }
     return es_udc_gac_jhwloc_HwlocEnumTypes_MEMBIND_UNKNOWN_TYPE;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    java2hwloc_topologyflag
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_java2hwloc_1topologyflag
  (JNIEnv *env, jclass this, jlong jhwloc_flag)
{
	switch(jhwloc_flag)
	{
		case es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_WHOLE_SYSTEM_TYPE:			return HWLOC_TOPOLOGY_FLAG_WHOLE_SYSTEM;
		case es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_IS_THISSYSTEM_TYPE:			return HWLOC_TOPOLOGY_FLAG_IS_THISSYSTEM;
		case es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES_TYPE:	return HWLOC_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TOPOLOGY_FLAG_UNKNOWN_TYPE: "+jhwloc_flag);
	}
        return es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_UNKNOWN_TYPE;
}

/*
 * Class:     es_udc_gac_jhwloc_HwlocEnumTypes
 * Method:    hwloc2java_topologyflag
 * Signature: (J)J
 */
JNIEXPORT jlong JNICALL Java_es_udc_gac_jhwloc_HwlocEnumTypes_hwloc2java_1topologyflag
  (JNIEnv *env, jclass this, jlong hwloc_flag)
{
	switch(hwloc_flag)
	{
		case HWLOC_TOPOLOGY_FLAG_WHOLE_SYSTEM:			return es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_WHOLE_SYSTEM_TYPE;
		case HWLOC_TOPOLOGY_FLAG_IS_THISSYSTEM:			return es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_IS_THISSYSTEM_TYPE;
		case HWLOC_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES:	return es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES_TYPE;
		default: ThrowByName(env, "es/udc/gac/jhwloc/HwlocException", "HWLOC_TOPOLOGY_FLAG_UNKNOWN: "+hwloc_flag);
	}
        return es_udc_gac_jhwloc_HwlocEnumTypes_TOPOLOGY_FLAG_UNKNOWN_TYPE;
}
