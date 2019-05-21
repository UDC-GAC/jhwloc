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
package es.udc.gac.jhwloc;

import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocCPUBindFlags;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocCompareTypes;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocGetTypeDepth;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectBridgeType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectCacheType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectOSDevType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocTopologyFlags;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocTypeFilter;

public class HWLOC {

	static {
		System.loadLibrary("jhwloc");
		API_VERSION = jhwloc_api_version();
	}

	private HWLOC() {}

	public static final int API_VERSION;
	public static final HwlocObjectType OBJ_UNKNOWN = HwlocObjectType.HWLOC_OBJ_UNKNOWN;
	public static final HwlocObjectType OBJ_MIN = HwlocObjectType.HWLOC_OBJ_MACHINE;
	public static final HwlocObjectType OBJ_MACHINE = HwlocObjectType.HWLOC_OBJ_MACHINE;
	public static final HwlocObjectType OBJ_PACKAGE = HwlocObjectType.HWLOC_OBJ_PACKAGE;
	public static final HwlocObjectType OBJ_SOCKET = HwlocObjectType.HWLOC_OBJ_PACKAGE; // For backwards compatibility
	public static final HwlocObjectType OBJ_CORE = HwlocObjectType.HWLOC_OBJ_CORE;
	public static final HwlocObjectType OBJ_PU = HwlocObjectType.HWLOC_OBJ_PU;
	public static final HwlocObjectType OBJ_L1CACHE = HwlocObjectType.HWLOC_OBJ_L1CACHE;
	public static final HwlocObjectType OBJ_L2CACHE = HwlocObjectType.HWLOC_OBJ_L2CACHE;
	public static final HwlocObjectType OBJ_L3CACHE = HwlocObjectType.HWLOC_OBJ_L3CACHE;
	public static final HwlocObjectType OBJ_L4CACHE = HwlocObjectType.HWLOC_OBJ_L4CACHE;
	public static final HwlocObjectType OBJ_L5CACHE = HwlocObjectType.HWLOC_OBJ_L5CACHE;
	public static final HwlocObjectType OBJ_L1ICACHE = HwlocObjectType.HWLOC_OBJ_L1ICACHE;
	public static final HwlocObjectType OBJ_L2ICACHE = HwlocObjectType.HWLOC_OBJ_L2ICACHE;
	public static final HwlocObjectType OBJ_L3ICACHE = HwlocObjectType.HWLOC_OBJ_L3ICACHE;
	public static final HwlocObjectType OBJ_GROUP = HwlocObjectType.HWLOC_OBJ_GROUP;
	public static final HwlocObjectType OBJ_NUMANODE = HwlocObjectType.HWLOC_OBJ_NUMANODE;
	public static final HwlocObjectType OBJ_BRIDGE = HwlocObjectType.HWLOC_OBJ_BRIDGE;
	public static final HwlocObjectType OBJ_PCI_DEVICE = HwlocObjectType.HWLOC_OBJ_PCI_DEVICE;
	public static final HwlocObjectType OBJ_OS_DEVICE = HwlocObjectType.HWLOC_OBJ_OS_DEVICE;
	public static final HwlocObjectType OBJ_MISC = HwlocObjectType.HWLOC_OBJ_MISC;
	public static final HwlocObjectCacheType OBJ_CACHE_UNKNOWN = HwlocObjectCacheType.HWLOC_OBJ_CACHE_UNKNOWN;
	public static final HwlocObjectCacheType OBJ_CACHE_UNIFIED = HwlocObjectCacheType.HWLOC_OBJ_CACHE_UNIFIED;
	public static final HwlocObjectCacheType OBJ_CACHE_DATA = HwlocObjectCacheType.HWLOC_OBJ_CACHE_DATA;
	public static final HwlocObjectCacheType OBJ_CACHE_INSTRUCTION = HwlocObjectCacheType.HWLOC_OBJ_CACHE_INSTRUCTION;
	public static final HwlocObjectBridgeType OBJ_BRIDGE_UNKNOWN = HwlocObjectBridgeType.HWLOC_OBJ_BRIDGE_UNKNOWN;
	public static final HwlocObjectBridgeType OBJ_BRIDGE_HOST = HwlocObjectBridgeType.HWLOC_OBJ_BRIDGE_HOST;
	public static final HwlocObjectBridgeType OBJ_BRIDGE_PCI = HwlocObjectBridgeType.HWLOC_OBJ_BRIDGE_PCI;
	public static final HwlocObjectOSDevType OBJ_OSDEV_UNKNOWN = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_UNKNOWN;
	public static final HwlocObjectOSDevType OBJ_OSDEV_BLOCK = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_BLOCK;
	public static final HwlocObjectOSDevType OBJ_OSDEV_GPU = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_GPU;
	public static final HwlocObjectOSDevType OBJ_OSDEV_NETWORK = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_NETWORK;
	public static final HwlocObjectOSDevType OBJ_OSDEV_OPENFABFRICS = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_OPENFABRICS;
	public static final HwlocObjectOSDevType OBJ_OSDEV_DMA = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_DMA;
	public static final HwlocObjectOSDevType OBJ_OSDEV_COPROC = HwlocObjectOSDevType.HWLOC_OBJ_OSDEV_COPROC;
	public static final HwlocTopologyFlags TOPOLOGY_FLAG_UNKNOWN = HwlocTopologyFlags.HWLOC_TOPOLOGY_FLAG_UNKNOWN;
	public static final HwlocTopologyFlags TOPOLOGY_FLAG_WHOLE_SYSTEM = HwlocTopologyFlags.HWLOC_TOPOLOGY_FLAG_WHOLE_SYSTEM;
	public static final HwlocTopologyFlags TOPOLOGY_FLAG_IS_THISSYSTEM = HwlocTopologyFlags.HWLOC_TOPOLOGY_FLAG_IS_THISSYSTEM;
	public static final HwlocTopologyFlags TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES = HwlocTopologyFlags.HWLOC_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES;
	public static final HwlocCPUBindFlags CPUBIND_UNKNOWN = HwlocCPUBindFlags.HWLOC_CPUBIND_UNKNOWN;
	public static final HwlocCPUBindFlags CPUBIND_PROCESS = HwlocCPUBindFlags.HWLOC_CPUBIND_PROCESS;
	public static final HwlocCPUBindFlags CPUBIND_THREAD = HwlocCPUBindFlags.HWLOC_CPUBIND_THREAD;
	public static final HwlocCPUBindFlags CPUBIND_STRICT = HwlocCPUBindFlags.HWLOC_CPUBIND_STRICT;
	public static final HwlocCPUBindFlags CPUBIND_NOMEMBIND = HwlocCPUBindFlags.HWLOC_CPUBIND_NOMEMBIND;
	public static final HwlocCompareTypes TYPE_UNKNOWN = HwlocCompareTypes.HWLOC_TYPE_UNKNOWN;
	public static final HwlocCompareTypes TYPE_UNORDERED = HwlocCompareTypes.HWLOC_TYPE_UNORDERED;
	public static final HwlocTypeFilter TYPE_FILTER_UNKNOWN = HwlocTypeFilter.HWLOC_TYPE_FILTER_UNKNOWN;
	public static final HwlocTypeFilter TYPE_FILTER_KEEP_ALL = HwlocTypeFilter.HWLOC_TYPE_FILTER_KEEP_ALL;
	public static final HwlocTypeFilter TYPE_FILTER_KEEP_NONE = HwlocTypeFilter.HWLOC_TYPE_FILTER_KEEP_NONE;
	public static final HwlocTypeFilter TYPE_FILTER_KEEP_STRUCTURE = HwlocTypeFilter.HWLOC_TYPE_FILTER_KEEP_STRUCTURE;
	public static final HwlocTypeFilter TYPE_FILTER_KEEP_IMPORTANT = HwlocTypeFilter.HWLOC_TYPE_FILTER_KEEP_IMPORTANT;
	public static final HwlocGetTypeDepth TYPE_DEPTH_UNKNOWN = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_UNKNOWN;
	public static final HwlocGetTypeDepth TYPE_DEPTH_MULTIPLE = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_MULTIPLE;
	public static final HwlocGetTypeDepth TYPE_DEPTH_NUMANODE = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_NUMANODE;
	public static final HwlocGetTypeDepth TYPE_DEPTH_BRIDGE = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_BRIDGE;
	public static final HwlocGetTypeDepth TYPE_DEPTH_PCI_DEVICE = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_PCI_DEVICE;
	public static final HwlocGetTypeDepth TYPE_DEPTH_OS_DEVICE = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_OS_DEVICE;
	public static final HwlocGetTypeDepth TYPE_DEPTH_MISC = HwlocGetTypeDepth.HWLOC_TYPE_DEPTH_MISC;

	/**
	 * Indicate at runtime which hwloc API version was used at build time.
	 * <p>
	 * Should be HWLOC.API_VERSION if running on the same version.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_api_version()</tt>.
	 *
	 * @return hwloc API version at build time.
	 */
	public static int get_api_version() {
		return jhwloc_get_api_version();
	}

	/**
	 * Compare the depth of two object types.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_compare_types()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>HWLOC.OBJ_PU will always be the deepest, while HWLOC.OBJ_MACHINE is
	 * always the highest.
	 * <li>This does not mean that the actual topology will respect that order:
	 * e.g. as of today cores may also contain caches, and packages may also contain
	 * nodes. This is thus just to be seen as a fallback comparison method. 
	 * </ul>
	 * 
	 * @param type1 First object type to be compared.
	 * @param type2 Second object type to be compared.
	 * @return This function returns less than, equal to, or greater than zero
	 * respectively if <tt>type1</tt> objects usually include <tt>type2</tt>
	 * objects, are the same as <tt>type2</tt> objects, or are included in
	 * <tt>type2</tt> objects. If the types can not be compared (because neither
	 * is usually contained in the other), HWLOC.TYPE_UNORDERED is returned.
	 * Object types containing CPUs can always be compared (usually, a system
	 * contains machines which contain nodes which contain packages which contain
	 * caches, which contain cores, which contain processors).
	 */
	public static int compare_types(HwlocObjectType type1, HwlocObjectType type2) {
		return jhwloc_compare_types(HwlocObjectType.GetType(type1),HwlocObjectType.GetType(type2));
	}

	/********************** PRIVATE NATIVE METHODS 	**********************/
	private static native int jhwloc_get_api_version();
	private static native int jhwloc_api_version();
	private static native int jhwloc_compare_types(int jhwloc_obj_type1, int jhwloc_obj_type2);
}
