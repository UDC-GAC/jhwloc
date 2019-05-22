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

import java.util.EnumSet;

public final class HwlocEnumTypes {

	enum HwlocObjectType {
		/**
		 * Machine. The root object type. A set of processors and memory with cache coherency.
		 */
		HWLOC_OBJ_MACHINE,
		/**
		 * Physical package. The physical package that usually gets inserted into a socket on
		 * the motherboard. A processor package usually contains multiple cores.
		 */
		HWLOC_OBJ_PACKAGE,
		/**
		 * Core. A computation unit (may be shared by several logical processors).
		 */
		HWLOC_OBJ_CORE,
		/**
		 * Processing Unit, or (Logical) Processor. An execution unit (may share a core with some
		 * other logical processors, e.g. in the case of an SMT core). Objects of this kind are always 
		 * reported and can thus be used as fallback when others are not.
		 */
		HWLOC_OBJ_PU,
		/**
		 * Level 1 Data (or Unified) Cache.
		 */	
		HWLOC_OBJ_L1CACHE,
		/**
		 * Level 2 Data (or Unified) Cache.
		 */	
		HWLOC_OBJ_L2CACHE,
		/**
		 * Level 3 Data (or Unified) Cache.
		 */	
		HWLOC_OBJ_L3CACHE,
		/**
		 * Level 4 Data (or Unified) Cache.
		 */	
		HWLOC_OBJ_L4CACHE,
		/**
		 * Level 5 Data (or Unified) Cache.
		 */	
		HWLOC_OBJ_L5CACHE,
		/**
		 * Level 1 instruction Cache (filtered out by default).
		 */	
		HWLOC_OBJ_L1ICACHE,
		/**
		 * Level 2 instruction Cache (filtered out by default).
		 */	
		HWLOC_OBJ_L2ICACHE,
		/**
		 * Level 3 instruction Cache (filtered out by default).
		 */	
		HWLOC_OBJ_L3ICACHE,
		/**
		 * Group objects. Objects which do not fit in the above but are detected by hwloc and are
		 * useful to take into account for affinity. For instance, some operating systems expose
		 * their arbitrary processors aggregation this way. And hwloc may insert such objects to
		 * group NUMA nodes according to their distances.
		 * <p>
		 * These objects are removed when they do not bring any structure. 
		 */	
		HWLOC_OBJ_GROUP,
		/**
		 * NUMA node. An object that contains memory that is directly and byte-accessible to the host
		 * processors. It is usually close to some cores (the corresponding objects are descendants of
		 * the NUMA node object in the hwloc tree).
		 * <p>
		 * There is always at one such object in the topology even if the machine is not NUMA.
		 * <p>
		 * Memory objects are not listed in the main children list, but rather in the dedicated Memory
		 * children list.
		 * <p>
		 * NUMA nodes have a special depth HWLOC_TYPE_DEPTH_NUMANODE instead of a normal depth just
		 * like other objects in the main tree. 
		 */
		HWLOC_OBJ_NUMANODE,
		/**
		 * Bridge (filtered out by default). Any bridge that connects the host or an I/O bus, to
		 * another I/O bus. They are not added to the topology unless I/O discovery is enabled with
		 * hwloc_topology_set_flags(). I/O objects are not listed in the main children list, but
		 * rather in the dedicated I/O children list. I/O objects have NULL CPU and node sets. 
		 */
		HWLOC_OBJ_BRIDGE,
		/**
		 * PCI device (filtered out by default). They are not added to the topology unless I/O
		 * discovery is enabled with hwloc_topology_set_flags(). I/O objects are not listed in the
		 * main children list, but rather in the dedicated io children list. I/O objects have NULL
		 * CPU and node sets. 
		 */
		HWLOC_OBJ_PCI_DEVICE,
		/**
		 * Operating system device (filtered out by default). They are not added to the topology
		 * unless I/O discovery is enabled with hwloc_topology_set_flags(). I/O objects are not listed
		 * in the main children list, but rather in the dedicated io children list. I/O objects have
		 * NULL CPU and node sets. 
		 */
		HWLOC_OBJ_OS_DEVICE,
		/**
		 * Miscellaneous objects (filtered out by default). Objects without particular meaning, that
		 * can e.g. be added by the application for its own use, or by hwloc for miscellaneous objects
		 * such as MemoryModule (DIMMs). These objects are not listed in the main children list, but
		 * rather in the dedicated misc children list. Misc objects may only have Misc objects as
		 * children, and those are in the dedicated misc children list as well. Misc objects have NULL
		 * CPU and node sets. 
		 */
		HWLOC_OBJ_MISC,
		/**
		 * Unknown hwloc object.
		 */
		HWLOC_OBJ_UNKNOWN;

		private HwlocObjectType() {}

		static int GetType(HwlocObjectType type) {
			switch(type)
			{
			case HWLOC_OBJ_MACHINE:		return OBJ_MACHINE_TYPE;
			case HWLOC_OBJ_PACKAGE:		return OBJ_PACKAGE_TYPE;
			case HWLOC_OBJ_CORE:		return OBJ_CORE_TYPE;
			case HWLOC_OBJ_PU:			return OBJ_PU_TYPE;
			case HWLOC_OBJ_L1CACHE:		return OBJ_L1CACHE_TYPE;
			case HWLOC_OBJ_L2CACHE:		return OBJ_L2CACHE_TYPE;
			case HWLOC_OBJ_L3CACHE:		return OBJ_L3CACHE_TYPE;
			case HWLOC_OBJ_L4CACHE:		return OBJ_L4CACHE_TYPE;
			case HWLOC_OBJ_L5CACHE:		return OBJ_L5CACHE_TYPE;
			case HWLOC_OBJ_L1ICACHE:	return OBJ_L1ICACHE_TYPE;
			case HWLOC_OBJ_L2ICACHE:	return OBJ_L2ICACHE_TYPE;
			case HWLOC_OBJ_L3ICACHE:	return OBJ_L3ICACHE_TYPE;
			case HWLOC_OBJ_GROUP:		return OBJ_GROUP_TYPE;
			case HWLOC_OBJ_NUMANODE:	return OBJ_NUMANODE_TYPE;
			case HWLOC_OBJ_BRIDGE:		return OBJ_BRIDGE_TYPE;
			case HWLOC_OBJ_PCI_DEVICE:	return OBJ_PCI_DEVICE_TYPE;
			case HWLOC_OBJ_OS_DEVICE:	return OBJ_OS_DEVICE_TYPE;
			case HWLOC_OBJ_MISC:		return OBJ_MISC_TYPE;
			default:					return OBJ_UNKNOWN_TYPE;
			}
		}

		static HwlocObjectType GetType(int type) {
			switch(type)
			{
			case OBJ_MACHINE_TYPE:		return HWLOC_OBJ_MACHINE;
			case OBJ_PACKAGE_TYPE:		return HWLOC_OBJ_PACKAGE;
			case OBJ_CORE_TYPE:			return HWLOC_OBJ_CORE;
			case OBJ_PU_TYPE:			return HWLOC_OBJ_PU;
			case OBJ_L1CACHE_TYPE:		return HWLOC_OBJ_L1CACHE;
			case OBJ_L2CACHE_TYPE:		return HWLOC_OBJ_L2CACHE;
			case OBJ_L3CACHE_TYPE:		return HWLOC_OBJ_L3CACHE;
			case OBJ_L4CACHE_TYPE:		return HWLOC_OBJ_L4CACHE;
			case OBJ_L5CACHE_TYPE:		return HWLOC_OBJ_L5CACHE;
			case OBJ_L1ICACHE_TYPE:		return HWLOC_OBJ_L1ICACHE;
			case OBJ_L2ICACHE_TYPE:		return HWLOC_OBJ_L2ICACHE;
			case OBJ_L3ICACHE_TYPE:		return HWLOC_OBJ_L3ICACHE;
			case OBJ_NUMANODE_TYPE:		return HWLOC_OBJ_NUMANODE;
			case OBJ_BRIDGE_TYPE:		return HWLOC_OBJ_BRIDGE;
			case OBJ_PCI_DEVICE_TYPE:	return HWLOC_OBJ_PCI_DEVICE;
			case OBJ_OS_DEVICE_TYPE:	return HWLOC_OBJ_OS_DEVICE;
			case OBJ_MISC_TYPE:			return HWLOC_OBJ_MISC;
			default: 					return HWLOC_OBJ_UNKNOWN;
			}
		}
	}

	private static final int OBJ_UNKNOWN_TYPE = -1;
	private static final int OBJ_MACHINE_TYPE = 0;
	private static final int OBJ_PACKAGE_TYPE = 1;
	private static final int OBJ_CORE_TYPE = 2;
	private static final int OBJ_PU_TYPE = 3;
	private static final int OBJ_L1CACHE_TYPE = 4;
	private static final int OBJ_L2CACHE_TYPE = 5;
	private static final int OBJ_L3CACHE_TYPE = 6;
	private static final int OBJ_L4CACHE_TYPE = 7;
	private static final int OBJ_L5CACHE_TYPE = 8;
	private static final int OBJ_L1ICACHE_TYPE = 9;
	private static final int OBJ_L2ICACHE_TYPE = 10;
	private static final int OBJ_L3ICACHE_TYPE = 11;
	private static final int OBJ_GROUP_TYPE = 12;
	private static final int OBJ_NUMANODE_TYPE = 13;
	private static final int OBJ_BRIDGE_TYPE = 14;
	private static final int OBJ_PCI_DEVICE_TYPE = 15;
	private static final int OBJ_OS_DEVICE_TYPE = 16;
	private static final int OBJ_MISC_TYPE = 17;

	enum HwlocObjectCacheType {
		/**
		 * Unified cache.
		 */
		HWLOC_OBJ_CACHE_UNIFIED,
		/**
		 * Data cache.
		 */
		HWLOC_OBJ_CACHE_DATA,
		/**
		 * Instruction cache (filtered out by default).
		 */
		HWLOC_OBJ_CACHE_INSTRUCTION,
		/**
		 * Unknown hwloc cache object.
		 */
		HWLOC_OBJ_CACHE_UNKNOWN;

		private HwlocObjectCacheType() {}

		static int GetType(HwlocObjectCacheType type){

			switch(type)
			{
			case HWLOC_OBJ_CACHE_UNIFIED:		return OBJ_CACHE_UNIFIED_TYPE;
			case HWLOC_OBJ_CACHE_DATA:			return OBJ_CACHE_DATA_TYPE;
			case HWLOC_OBJ_CACHE_INSTRUCTION:	return OBJ_CACHE_INSTRUCTION_TYPE;
			default: 							return OBJ_CACHE_UNKNOWN_TYPE;
			}
		}

		static HwlocObjectCacheType GetType(int type){

			switch(type)
			{
			case OBJ_CACHE_UNIFIED_TYPE:		return HWLOC_OBJ_CACHE_UNIFIED;
			case OBJ_CACHE_DATA_TYPE:			return HWLOC_OBJ_CACHE_DATA;
			case OBJ_CACHE_INSTRUCTION_TYPE:	return HWLOC_OBJ_CACHE_INSTRUCTION;
			default: 							return HWLOC_OBJ_CACHE_UNKNOWN;
			}
		}
	}

	private static final int OBJ_CACHE_UNKNOWN_TYPE = -2;
	private static final int OBJ_CACHE_UNIFIED_TYPE = 100;
	private static final int OBJ_CACHE_DATA_TYPE = 101;
	private static final int OBJ_CACHE_INSTRUCTION_TYPE = 102;

	enum HwlocObjectBridgeType {
		/**
		 * Host-side of a bridge, only possible upstream.
		 */
		HWLOC_OBJ_BRIDGE_HOST,
		/**
		 * PCI-side of a bridge.
		 */
		HWLOC_OBJ_BRIDGE_PCI,
		/**
		 * Unknown hwloc bridge object.
		 */
		HWLOC_OBJ_BRIDGE_UNKNOWN;

		private HwlocObjectBridgeType() {}

		static int GetType(HwlocObjectBridgeType type){

			switch(type)
			{
			case HWLOC_OBJ_BRIDGE_HOST:	return OBJ_BRIDGE_HOST_TYPE;
			case HWLOC_OBJ_BRIDGE_PCI:	return OBJ_BRIDGE_PCI_TYPE;
			default: 					return OBJ_BRIDGE_UNKNOWN_TYPE;
			}
		}

		static HwlocObjectBridgeType GetType(int type){

			switch(type)
			{
			case OBJ_BRIDGE_HOST_TYPE:	return HWLOC_OBJ_BRIDGE_HOST;
			case OBJ_BRIDGE_PCI_TYPE:	return HWLOC_OBJ_BRIDGE_PCI;
			default: 					return HWLOC_OBJ_BRIDGE_UNKNOWN;
			}
		}
	}

	private static final int OBJ_BRIDGE_UNKNOWN_TYPE = -5;
	private static final int OBJ_BRIDGE_HOST_TYPE = 400;
	private static final int OBJ_BRIDGE_PCI_TYPE = 401;

	enum HwlocObjectOSDevType {
		/**
		 * Operating system block device. For instance "sda" on Linux.
		 */
		HWLOC_OBJ_OSDEV_BLOCK,
		/**
		 * Operating system GPU device. For instance ":0.0" for a GL display,
		 * "card0" for a Linux DRM device. 
		 */
		HWLOC_OBJ_OSDEV_GPU,
		/**
		 * Operating system network device. For instance the "eth0" interface
		 * on Linux. 
		 */
		HWLOC_OBJ_OSDEV_NETWORK,
		/**
		 * Operating system openfabrics device. For instance the "mlx4_0"
		 * InfiniBand HCA, or "hfi1_0" Omni-Path interface on Linux.  
		 */
		HWLOC_OBJ_OSDEV_OPENFABRICS,
		/**
		 * Operating system DMA engine device. For instance the "dma0chan0" DMA
		 * channel on Linux.
		 */
		HWLOC_OBJ_OSDEV_DMA,
		/**
		 * Operating system co-processor device. For instance "mic0" for a Xeon Phi
		 * (MIC) on Linux, "opencl0d0" for a OpenCL device, "cuda0" for a CUDA device. 
		 */
		HWLOC_OBJ_OSDEV_COPROC,
		/**
		 * Unknown hwloc OS dev object.
		 */
		HWLOC_OBJ_OSDEV_UNKNOWN;

		private HwlocObjectOSDevType() {}

		static int GetType(HwlocObjectOSDevType type){

			switch(type)
			{
			case HWLOC_OBJ_OSDEV_BLOCK:			return OBJ_OSDEV_BLOCK_TYPE;
			case HWLOC_OBJ_OSDEV_GPU:			return OBJ_OSDEV_GPU_TYPE;
			case HWLOC_OBJ_OSDEV_NETWORK:		return OBJ_OSDEV_NETWORK_TYPE;
			case HWLOC_OBJ_OSDEV_OPENFABRICS:	return OBJ_OSDEV_OPENFABRICS_TYPE;
			case HWLOC_OBJ_OSDEV_DMA:			return OBJ_OSDEV_DMA_TYPE;
			case HWLOC_OBJ_OSDEV_COPROC:		return OBJ_OSDEV_COPROC_TYPE;
			default: 							return OBJ_OSDEV_UNKNOWN_TYPE;
			}
		}

		static HwlocObjectOSDevType GetType(int type){

			switch(type)
			{
			case OBJ_OSDEV_BLOCK_TYPE:			return HWLOC_OBJ_OSDEV_BLOCK;
			case OBJ_OSDEV_GPU_TYPE:			return HWLOC_OBJ_OSDEV_GPU;
			case OBJ_OSDEV_NETWORK_TYPE:		return HWLOC_OBJ_OSDEV_NETWORK;
			case OBJ_OSDEV_OPENFABRICS_TYPE:	return HWLOC_OBJ_OSDEV_OPENFABRICS;
			case OBJ_OSDEV_DMA_TYPE:			return HWLOC_OBJ_OSDEV_DMA;
			case OBJ_OSDEV_COPROC_TYPE:			return HWLOC_OBJ_OSDEV_COPROC;
			default: 							return HWLOC_OBJ_OSDEV_UNKNOWN;
			}
		}
	}

	private static final int OBJ_OSDEV_UNKNOWN_TYPE = -6;
	private static final int OBJ_OSDEV_BLOCK_TYPE = 500;
	private static final int OBJ_OSDEV_GPU_TYPE = 501;
	private static final int OBJ_OSDEV_NETWORK_TYPE = 502;
	private static final int OBJ_OSDEV_OPENFABRICS_TYPE = 503;
	private static final int OBJ_OSDEV_DMA_TYPE = 504;
	private static final int OBJ_OSDEV_COPROC_TYPE = 505;

	enum HwlocTopologyFlags {
		/**
		 * Detect the whole system, ignore reservations.
		 * <p>
		 * Gather all resources, even if some were disabled by the administrator. For
		 * instance, ignore Linux Cgroup/Cpusets and gather all processors and memory
		 * nodes.
		 */
		HWLOC_TOPOLOGY_FLAG_WHOLE_SYSTEM,
		/**
		 * Assume that the selected backend provides the topology for the system on
		 * which we are running.
		 * <p>
		 * This forces <tt>is_thissystem()</tt> to return 1, i.e. makes hwloc assume
		 * that the selected backend provides the topology for the system on which we
		 * are running, even if it is not the OS-specific backend but the XML backend
		 * for instance. This means making the binding functions actually call the
		 * OS-specific system calls and really do binding, while the XML backend would
		 * otherwise provide empty hooks just returning success.
		 * <p>
		 * Setting the environment variable <tt>HWLOC_THISSYSTEM</tt> may also result in
		 * the same behavior.
		 * <p>
		 * This can be used for efficiency reasons to first detect the topology once, save
		 * it to an XML file, and quickly reload it later through the XML backend, but
		 * still having binding functions actually do bind.
		 */
		HWLOC_TOPOLOGY_FLAG_IS_THISSYSTEM,
		/**
		 * Get the set of allowed resources from the local operating system even if the
		 * topology was loaded from XML or synthetic description.
		 * <p>
		 * If the topology was loaded from XML or from a synthetic string, restrict it by
		 * applying the current process restrictions such as Linux Cgroup/Cpuset.
		 * <p>
		 * This is useful when the topology is not loaded directly from the local machine
		 * (e.g. for performance reason) and it comes with all resources, while the running
		 * process is restricted to only parts of the machine.
		 * <p>
		 * This flag is ignored unless HWLOC.TOPOLOGY_FLAG_IS_THISSYSTEM is also set since
		 * the loaded topology must match the underlying machine where restrictions will be
		 * gathered from.
		 * <p>
		 * Setting the environment variable HWLOC_THISSYSTEM_ALLOWED_RESOURCES would result
		 * in the same behavior.  
		 */
		HWLOC_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES,
		/**
		 * Unknown hwloc topology flag.
		 */
		HWLOC_TOPOLOGY_FLAG_UNKNOWN;

		private HwlocTopologyFlags() {}

		private static final EnumSet<HwlocTopologyFlags> TOPOLOGY_FLAGS = EnumSet.allOf(HwlocTopologyFlags.class);

		static long Java2HwlocFlags(EnumSet<HwlocTopologyFlags> flags)
		{
			long hwloc_flags = 0;
			int i = 0;

			for (HwlocTopologyFlags flag: flags)
			{
				if(i==0)
					hwloc_flags = java2hwloc_topologyflag(HwlocTopologyFlags.GetFlag(flag));
				else
					hwloc_flags = hwloc_flags | java2hwloc_topologyflag(HwlocTopologyFlags.GetFlag(flag));

				i++;
			}
			return hwloc_flags;
		}

		static EnumSet<HwlocTopologyFlags> Hwloc2JavaFlags(long hwloc_flags)
		{
			EnumSet<HwlocTopologyFlags> set = EnumSet.noneOf(HwlocTopologyFlags.class);

			for (HwlocTopologyFlags flag: HwlocTopologyFlags.TOPOLOGY_FLAGS)
			{
				if((hwloc_flags & hwloc2java_topologyflag(HwlocTopologyFlags.GetFlag(flag))) != 0)
					set.add(flag);
			}

			return set;
		}

		private static int GetFlag(HwlocTopologyFlags flag) {
			switch(flag)
			{
			case HWLOC_TOPOLOGY_FLAG_WHOLE_SYSTEM:					return TOPOLOGY_FLAG_WHOLE_SYSTEM_TYPE;
			case HWLOC_TOPOLOGY_FLAG_IS_THISSYSTEM:					return TOPOLOGY_FLAG_IS_THISSYSTEM_TYPE;
			case HWLOC_TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES:	return TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES_TYPE;
			default: 												return TOPOLOGY_FLAG_UNKNOWN_TYPE;
			}
		}
	}

	private static final int TOPOLOGY_FLAG_UNKNOWN_TYPE = -3;
	private static final int TOPOLOGY_FLAG_WHOLE_SYSTEM_TYPE = 200;
	private static final int TOPOLOGY_FLAG_IS_THISSYSTEM_TYPE = 201;
	private static final int TOPOLOGY_FLAG_THISSYSTEM_ALLOWED_RESOURCES_TYPE = 202;

	enum HwlocCPUBindFlags {
		/**
		 * Bind all threads of the current (possibly) multithreaded process.
		 */
		HWLOC_CPUBIND_PROCESS,
		/**
		 * Bind current thread of current process.
		 */
		HWLOC_CPUBIND_THREAD,
		/**
		 * Request for strict binding from the OS.
		 * <p>
		 * By default, when the designated CPUs are all busy while other CPUs
		 * are idle, operating systems may execute the thread/process on those
		 * other CPUs instead of the designated CPUs, to let them progress anyway.
		 * Strict binding means that the thread/process will _never_ execute on
		 * other cpus than the designated CPUs, even when those are busy with
		 * other tasks and other CPUs are idle.
		 * <p>
		 * Note
		 * <ul>
		 * <li>Depending on the operating system, strict binding may not be possible
		 * (e.g., the OS does not implement it) or not allowed (e.g., for an
		 * administrative reasons), and the function will fail in that case.
		 * </ul>
		 * <p>
		 * When retrieving the binding of a process, this flag checks whether all
		 * its threads actually have the same binding. If the flag is not given, the
		 * binding of each thread will be accumulated.
		 * <p>
		 * Note
		 * <ul>
		 * <li>This flag is meaningless when retrieving the binding of a thread.
		 * </ul>
		 */
		HWLOC_CPUBIND_STRICT,
		/**
		 * Avoid any effect on memory binding.
		 * <p>
		 * On some operating systems, some CPU binding function would also bind
		 * the memory on the corresponding NUMA node. It is often not a problem
		 * for the application, but if it is, setting this flag will make hwloc
		 * avoid using OS functions that would also bind memory. This will however
		 * reduce the support of CPU bindings, i.e. potentially return -1 and
		 * throw HwlocException.
		 * <p>
		 * This flag is only meaningful when used with functions that set the CPU
		 * binding. It is ignored when used with functions that get CPU binding
		 * information.
		 */
		HWLOC_CPUBIND_NOMEMBIND,
		/**
		 * Unknown hwloc CPU binding flag.
		 */
		HWLOC_CPUBIND_UNKNOWN;

		private HwlocCPUBindFlags() {}

		static final EnumSet<HwlocCPUBindFlags> CPUBINDING_FLAGS = EnumSet.allOf(HwlocCPUBindFlags.class);

		static int Java2HwlocFlags(EnumSet<HwlocCPUBindFlags> flags)
		{
			int hwloc_flags = 0;
			int i = 0;

			for (HwlocCPUBindFlags flag: flags)
			{
				if(i==0)
					hwloc_flags = java2hwloc_cpubindflag(HwlocCPUBindFlags.GetFlag(flag));
				else
					hwloc_flags = hwloc_flags | java2hwloc_cpubindflag(HwlocCPUBindFlags.GetFlag(flag));

				i++;
			}
			return hwloc_flags;
		}

		static int GetFlag(HwlocCPUBindFlags flag) {

			switch(flag)
			{
			case HWLOC_CPUBIND_PROCESS:		return CPUBIND_PROCESS_TYPE;
			case HWLOC_CPUBIND_THREAD:		return CPUBIND_THREAD_TYPE;
			case HWLOC_CPUBIND_STRICT:		return CPUBIND_STRICT_TYPE;
			case HWLOC_CPUBIND_NOMEMBIND:	return CPUBIND_NOMEMBIND_TYPE;
			default: 						return CPUBIND_UNKNOWN_TYPE;
			}
		}
	}

	private static final int CPUBIND_UNKNOWN_TYPE = -4;
	private static final int CPUBIND_PROCESS_TYPE = 300;
	private static final int CPUBIND_THREAD_TYPE = 301;
	private static final int CPUBIND_STRICT_TYPE = 302;
	private static final int CPUBIND_NOMEMBIND_TYPE = 303;

	enum HwlocMEMBindFlags {
		/**
		 * Set policy for all threads of the specified (possibly multithreaded) process.
		 * <p>
		 * This flag is mutually exclusive with HWLOC_MEMBIND_THREAD.
		 */
		HWLOC_MEMBIND_PROCESS,
		/**
		 * Set policy for a specific thread of the current process.
		 * <p>
		 * This flag is mutually exclusive with HWLOC_MEMBIND_PROCESS.
		 */		
		HWLOC_MEMBIND_THREAD,
		/**
		 * Request strict binding from the OS. The function will fail if the binding can not 
		 * be guaranteed / completely enforced. This flag has slightly different meanings
		 * depending on which function it is used with.
		 */				
		HWLOC_MEMBIND_STRICT,
		/**
		 * Migrate existing allocated memory. If the memory cannot be migrated and the 
		 * HWLOC_MEMBIND_STRICT flag is passed, an error will be returned.
		 */		
		HWLOC_MEMBIND_MIGRATE,
		/**
		 * Avoid any effect on CPU binding.
		 * <p>
		 * On some operating systems, some underlying memory binding functions also bind
		 * the application to the corresponding CPU(s). Using this flag will cause hwloc
		 * to avoid using OS functions that could potentially affect CPU bindings. Note,
		 * however, that using NOCPUBIND may reduce hwloc's overall memory binding
		 * support, i.e. potentially return -1 and throw HwlocException.
		 */		
		HWLOC_MEMBIND_NOCPUBIND,
		/**
		 * Consider the bitmap argument as a nodeset.
		 * <p>
		 * The bitmap argument is considered a nodeset if this flag is given, or a cpuset 
		 * otherwise by default.
		 * <p>
		 * Memory binding by cpuset cannot work for CPU-less NUMA memory nodes. Binding by 
		 * nodeset should therefore be preferred whenever possible.
		 */		
		HWLOC_MEMBIND_BYNODESET,
		/**
		 * Unknown hwloc MEM binding flag.
		 */
		HWLOC_MEMBIND_UNKNOWN;

		private HwlocMEMBindFlags() {}

		static final EnumSet<HwlocMEMBindFlags> MEMBINDING_FLAGS = EnumSet.allOf(HwlocMEMBindFlags.class);

		static int Java2HwlocFlags(EnumSet<HwlocMEMBindFlags> flags)
		{
			int hwloc_flags = 0;
			int i = 0;

			for (HwlocMEMBindFlags flag: flags)
			{
				if(i==0)
					hwloc_flags = java2hwloc_membindflag(HwlocMEMBindFlags.GetFlag(flag));
				else
					hwloc_flags = hwloc_flags | java2hwloc_membindflag(HwlocMEMBindFlags.GetFlag(flag));

				i++;
			}
			return hwloc_flags;
		}

		static int GetFlag(HwlocMEMBindFlags flag) {

			switch(flag)
			{
			case HWLOC_MEMBIND_PROCESS:		return MEMBIND_PROCESS_TYPE;
			case HWLOC_MEMBIND_THREAD:		return MEMBIND_THREAD_TYPE;
			case HWLOC_MEMBIND_STRICT:		return MEMBIND_STRICT_TYPE;
			case HWLOC_MEMBIND_MIGRATE:		return MEMBIND_MIGRATE_TYPE;
			case HWLOC_MEMBIND_NOCPUBIND:	return MEMBIND_NOCPUBIND_TYPE;
			case HWLOC_MEMBIND_BYNODESET:	return MEMBIND_BYNODESET_TYPE;
			default: 						return MEMBIND_UNKNOWN_TYPE;
			}
		}
	}

	private static final int MEMBIND_UNKNOWN_TYPE = -4;
	private static final int MEMBIND_PROCESS_TYPE = 400;
	private static final int MEMBIND_THREAD_TYPE = 401;
	private static final int MEMBIND_STRICT_TYPE = 402;
	private static final int MEMBIND_MIGRATE_TYPE = 403;
	private static final int MEMBIND_NOCPUBIND_TYPE = 404;
	private static final int MEMBIND_BYNODESET_TYPE = 405;

	public enum HwlocMEMBindPolicy {
		/**
		 * Reset the memory allocation policy to the system default.
		 * <p>
		 * Depending on the operating system, this may correspond to HWLOC_MEMBIND_FIRSTTOUCH (Linux),
		 * or HWLOC_MEMBIND_BIND (AIX, HP-UX, Solaris, Windows). This policy is never returned by get 
		 * membind functions. The nodeset argument is ignored
		 */
		HWLOC_MEMBIND_DEFAULT,
		/**
		 * Allocate each memory page individually on the local NUMA node of the thread that touches it.
		 * <p>
		 * The given nodeset should usually be <tt>get_topology_nodeset()</tt> so that 
		 * the touching thread may run and allocate on any node in the system. On AIX, if the nodeset is
		 * smaller, pages are allocated locally (if the local node is in the nodeset) or from a random
		 * non-local node (otherwise).
		 */		
		HWLOC_MEMBIND_FIRSTTOUCH,
		/**
		 * Allocate memory on the specified nodes.
		 */				
		HWLOC_MEMBIND_BIND,
		/**
		 * Allocate memory on the given nodes in an interleaved / round-robin manner.
		 * <p>
		 * The precise layout of the memory across multiple NUMA nodes is OS/system specific. Interleaving
		 * can be useful when threads distributed across the specified NUMA nodes will all be accessing the
		 * whole memory range concurrently, since the interleave will then balance the memory references.
		 */		
		HWLOC_MEMBIND_INTERLEAVE,
		/**
		 * For each page bound with this policy, by next time it is touched (and next time only), it is moved
		 * from its current location to the local NUMA node of the thread where the memory reference occurred
		 * (if it needs to be moved at all).
		 */		
		HWLOC_MEMBIND_NEXTTOUCH,
		/**
		 * Returned by <tt>get_membind()</tt> functions when multiple threads or parts of a memory area have 
		 * differing memory binding policies. Also returned when binding is unknown because binding hooks are
		 * empty when the topology is loaded from XML without HWLOC_THISSYSTEM=1, etc.
		 */		
		HWLOC_MEMBIND_MIXED,
		/**
		 * Unknown hwloc MEM binding policy.
		 */
		HWLOC_MEMBIND_UNKNOWN;

		private HwlocMEMBindPolicy() {}

		static HwlocMEMBindPolicy GetType(int type)
		{
			switch(type)
			{
			case MEMBIND_DEFAULT_TYPE:		return HWLOC_MEMBIND_DEFAULT;
			case MEMBIND_FIRSTTOUCH_TYPE:	return HWLOC_MEMBIND_FIRSTTOUCH;
			case MEMBIND_BIND_TYPE:			return HWLOC_MEMBIND_BIND;
			case MEMBIND_INTERLEAVE_TYPE:	return HWLOC_MEMBIND_INTERLEAVE;
			case MEMBIND_NEXTTOUCH_TYPE:	return HWLOC_MEMBIND_NEXTTOUCH;
			case MEMBIND_MIXED_TYPE:		return HWLOC_MEMBIND_MIXED;
			default: 						return HWLOC_MEMBIND_UNKNOWN;
			}
		}

		static int GetType(HwlocMEMBindPolicy type) {

			switch(type)
			{
			case HWLOC_MEMBIND_DEFAULT:		return MEMBIND_DEFAULT_TYPE;
			case HWLOC_MEMBIND_FIRSTTOUCH:	return MEMBIND_FIRSTTOUCH_TYPE;
			case HWLOC_MEMBIND_BIND:		return MEMBIND_BIND_TYPE;
			case HWLOC_MEMBIND_INTERLEAVE:	return MEMBIND_INTERLEAVE_TYPE;
			case HWLOC_MEMBIND_NEXTTOUCH:	return MEMBIND_NEXTTOUCH_TYPE;
			case HWLOC_MEMBIND_MIXED:		return MEMBIND_MIXED_TYPE;
			default: 						return MEMBIND_UNKNOWN_POLICY_TYPE;
			}
		}
	}

	private static final int MEMBIND_UNKNOWN_POLICY_TYPE = -5;
	private static final int MEMBIND_DEFAULT_TYPE = 500;
	private static final int MEMBIND_FIRSTTOUCH_TYPE = 501;
	private static final int MEMBIND_BIND_TYPE = 502;
	private static final int MEMBIND_INTERLEAVE_TYPE = 503;
	private static final int MEMBIND_NEXTTOUCH_TYPE = 504;
	private static final int MEMBIND_MIXED_TYPE = 505;

	enum HwlocCompareTypes {
		/**
		 * Value returned by <tt>hwloc_compare_types()</tt> when types can not
		 * be compared. 
		 */
		HWLOC_TYPE_UNORDERED,
		/**
		 * Unknown hwloc compare type.
		 */
		HWLOC_TYPE_UNKNOWN;

		private HwlocCompareTypes() {}

		static int GetType(HwlocCompareTypes type) {
			switch(type)
			{
			case HWLOC_TYPE_UNORDERED:	return TYPE_UNORDERED_TYPE;
			default:					return TYPE_UNKNOWN_TYPE;
			}
		}

		static HwlocCompareTypes GetType(int type) {
			switch(type)
			{
			case TYPE_UNORDERED_TYPE:	return HWLOC_TYPE_UNORDERED;
			default:					return HWLOC_TYPE_UNKNOWN;
			}
		}
	}

	private static final int TYPE_UNKNOWN_TYPE = -7;
	private static final int TYPE_UNORDERED_TYPE = 600;

	enum HwlocTypeFilter {
		/**
		 * Keep all objects of this type.
		 * <p>
		 * Cannot be set for HWLOC.OBJ_GROUP (groups are designed only to
		 * add more structure to the topology). 
		 */
		HWLOC_TYPE_FILTER_KEEP_ALL,
		/**
		 * Ignore all objects of this type.
		 * <p>
		 * The bottom-level type HWLOC.OBJ_PU, the HWLOC.OBJ_NUMANODE type, and
		 * the top-level type HWLOC.OBJ_MACHINE may not be ignored.  
		 */		
		HWLOC_TYPE_FILTER_KEEP_NONE,
		/**
		 * Only ignore objects if their entire level does not bring any structure.
		 * <p>
		 * Keep the entire level of objects if at least one of these objects adds
		 * structure to the topology. An object brings structure when it has multiple
		 * children and it is not the only child of its parent.
		 * <p>
		 * If all objects in the level are the only child of their parent, and if none
		 * of them has multiple children, the entire level is removed.
		 * <p>
		 * Cannot be set for I/O and Misc objects since the topology structure does
		 * not matter there.  
		 */	
		HWLOC_TYPE_FILTER_KEEP_STRUCTURE,
		/**
		 * Only keep likely-important objects of the given type.
		 * <p>
		 * It is only useful for I/O object types. For HWLOC.OBJ_PCI_DEVICE and
		 * HWLOC.OBJ_OS_DEVICE, it means that only objects of major/common kinds
		 * are kept (storage, network, OpenFabrics, Intel MICs, CUDA, OpenCL,
		 * NVML, and displays). Also, only OS devices directly attached on PCI
		 * (e.g. no USB) are reported. For HWLOC_OBJ_BRIDGE, it means that bridges
		 * are kept only if they have children.
		 * <p>
		 * This flag equivalent to HWLOC.TYPE_FILTER_KEEP_ALL for Normal, Memory
		 * and Misc types since they are likely important.
		 */ 
		HWLOC_TYPE_FILTER_KEEP_IMPORTANT,
		/**
		 * Unknown hwloc type filter.
		 */
		HWLOC_TYPE_FILTER_UNKNOWN;

		private HwlocTypeFilter() {}

		static int GetType(HwlocTypeFilter type) {
			switch(type)
			{
			case HWLOC_TYPE_FILTER_KEEP_ALL:		return TYPE_FILTER_KEEP_ALL_TYPE;
			case HWLOC_TYPE_FILTER_KEEP_NONE:		return TYPE_FILTER_KEEP_NONE_TYPE;
			case HWLOC_TYPE_FILTER_KEEP_STRUCTURE:	return TYPE_FILTER_KEEP_STRUCTURE_TYPE;
			case HWLOC_TYPE_FILTER_KEEP_IMPORTANT:	return TYPE_FILTER_KEEP_IMPORTANT_TYPE;
			default:								return TYPE_FILTER_UNKNOWN_TYPE;
			}
		}

		static HwlocTypeFilter GetType(int type) {
			switch(type)
			{
			case TYPE_FILTER_KEEP_ALL_TYPE:			return HWLOC_TYPE_FILTER_KEEP_ALL;
			case TYPE_FILTER_KEEP_NONE_TYPE:		return HWLOC_TYPE_FILTER_KEEP_NONE;
			case TYPE_FILTER_KEEP_STRUCTURE_TYPE:	return HWLOC_TYPE_FILTER_KEEP_STRUCTURE;
			case TYPE_FILTER_KEEP_IMPORTANT_TYPE:	return HWLOC_TYPE_FILTER_KEEP_IMPORTANT;
			default:								return HWLOC_TYPE_FILTER_UNKNOWN;
			}
		}
	}

	private static final int TYPE_FILTER_UNKNOWN_TYPE = -8;
	private static final int TYPE_FILTER_KEEP_ALL_TYPE = 701;
	private static final int TYPE_FILTER_KEEP_NONE_TYPE = 702;
	private static final int TYPE_FILTER_KEEP_STRUCTURE_TYPE = 703;
	private static final int TYPE_FILTER_KEEP_IMPORTANT_TYPE = 704;

	enum HwlocGetTypeDepth {
		/**
		 * Objects of given type exist at different depth in the topology (only for Groups).
		 */
		HWLOC_TYPE_DEPTH_MULTIPLE,
		/**
		 * Virtual depth for NUMA nodes.
		 */
		HWLOC_TYPE_DEPTH_NUMANODE,
		/**
		 * Virtual depth for bridge object level.
		 */
		HWLOC_TYPE_DEPTH_BRIDGE,
		/**
		 * Virtual depth for PCI device object level.
		 */
		HWLOC_TYPE_DEPTH_PCI_DEVICE,
		/**
		 * Virtual depth for software device object level. 
		 */
		HWLOC_TYPE_DEPTH_OS_DEVICE,
		/**
		 * Virtual depth for Misc object.
		 */
		HWLOC_TYPE_DEPTH_MISC,
		/**
		 * No object of given type exists in the topology.
		 */
		HWLOC_TYPE_DEPTH_UNKNOWN;

		private HwlocGetTypeDepth() {}

		static int GetType(HwlocGetTypeDepth type) {
			switch(type)
			{
			case HWLOC_TYPE_DEPTH_MULTIPLE:		return HWLOC_TYPE_DEPTH_MULTIPLE_TYPE;
			case HWLOC_TYPE_DEPTH_NUMANODE:		return HWLOC_TYPE_DEPTH_NUMANODE_TYPE;
			case HWLOC_TYPE_DEPTH_BRIDGE:		return HWLOC_TYPE_DEPTH_BRIDGE_TYPE;
			case HWLOC_TYPE_DEPTH_PCI_DEVICE:	return HWLOC_TYPE_DEPTH_PCI_DEVICE_TYPE;
			case HWLOC_TYPE_DEPTH_OS_DEVICE: 	return HWLOC_TYPE_DEPTH_OS_DEVICE_TYPE;
			case HWLOC_TYPE_DEPTH_MISC: 		return HWLOC_TYPE_DEPTH_MISC_TYPE;
			default:							return HWLOC_TYPE_DEPTH_UNKNOWN_TYPE;
			}
		}
	}

	private static final int HWLOC_TYPE_DEPTH_UNKNOWN_TYPE = -9;
	private static final int HWLOC_TYPE_DEPTH_MULTIPLE_TYPE = 801;
	private static final int HWLOC_TYPE_DEPTH_NUMANODE_TYPE = 802;
	private static final int HWLOC_TYPE_DEPTH_BRIDGE_TYPE = 803;
	private static final int HWLOC_TYPE_DEPTH_PCI_DEVICE_TYPE = 804;
	private static final int HWLOC_TYPE_DEPTH_OS_DEVICE_TYPE = 805;
	private static final int HWLOC_TYPE_DEPTH_MISC_TYPE = 806;

	/********************** PRIVATE NATIVE METHODS 	**********************/
	private static native int java2hwloc_cpubindflag(int jhwloc_flag);
	private static native int hwloc2java_cpubindflag(int hwloc_flag);
	private static native int java2hwloc_membindflag(int jhwloc_flag);
	private static native int hwloc2java_membindflag(int hwloc_flag);
	private static native long java2hwloc_topologyflag(long jhwloc_flag);
	private static native long hwloc2java_topologyflag(long hwloc_flag);
}
