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

import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectType;

public class HwlocObject {

	private long handler;
	private HwlocTopology topology;
	private HwlocObjectType type;
	private int os_index;
	private String name;
	private int depth;
	private int logical_index;
	private int arity;
	private long parent_handler;
	private HwlocObject[] children;
	private HwlocCPUSet cpuset;
	private HwlocNodeSet nodeset;

	private HwlocObject(HwlocTopology topology, long handler, int type, int arity, long parent_handler) {
		this.topology = topology;
		this.handler = handler;
		this.type = HwlocObjectType.GetType(type);
		this.arity = arity;
		this.children = new HwlocObject[this.arity];
		this.parent_handler = parent_handler;
		this.cpuset = new HwlocCPUSet();
		this.nodeset = new HwlocNodeSet();
	}

	public HwlocObjectType getType() {
		return this.type;
	}

	public int getOS_index() {
		return this.os_index;
	}

	public String getName() {
		return this.name;
	}

	public int getDepth() {
		return this.depth;
	}

	public int getLogical_index() {
		return this.logical_index;
	}

	public HwlocObject getParent() {
		if(this.parent_handler != -1)
			return HwlocTopology.GetHwlocObject(this.topology.get_root_obj(), this.parent_handler);
		else
			return null;	
	}

	public int getArity() {
		return this.arity;
	}


	public HwlocObject[] getChildren() {
		return this.children;
	}

	public HwlocObject getFirst_child() {
		if(this.arity > 0)
			return this.children[0];
		else
			return null;
	}

	public HwlocObject getLast_child() {
		if(this.arity > 0)
			return this.children[this.arity-1];
		else
			return null;
	}

	public HwlocCPUSet getCPUSet() {
		return this.cpuset;
	}

	public HwlocNodeSet getNodeSet() {
		return this.nodeset;
	}

	long getHandler() {
		return this.handler;
	}

	/**
	 * Return a constant stringified topology object type.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_string()</tt>.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		return jhwloc_obj_type_string(HwlocObjectType.GetType(this.type));
	}

	/**
	 * Check whether this object type is Normal.
	 * <p>
	 * Normal objects are objects of the main CPU hierarchy (Machine, Package, Core, PU,
	 * CPU caches, etc.), but they are not NUMA nodes, I/O devices or Misc objects.
	 * <p>
	 * They are attached to parent as Normal children, not as Memory, I/O or Misc children.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_normal()</tt>.
	 *
	 * @return <code>true</code> if this object is a Normal object, <code>false</code> otherwise. 
	 */
	public boolean is_normal() {
		int rc = jhwloc_obj_type_is_normal(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check whether this object type is I/O.
	 * <p>
	 * I/O objects are objects attached to their parents in the I/O children
	 * list. This current includes Bridges, PCI and OS devices.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_io()</tt>.
	 *
	 * @return <code>true</code> if this object is a I/O object, <code>false</code> otherwise. 
	 */
	public boolean is_io() {
		int rc = jhwloc_obj_type_is_io(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check whether this object type is Memory. 
	 * <p>
	 * Memory objects are objects attached to their parents in the Memory children
	 * list. This current only includes NUMA nodes.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_memory()</tt>.
	 *
	 * @return <code>true</code> if this object is a Memory object, <code>false</code> otherwise. 
	 */
	public boolean is_memory() {
		int rc = jhwloc_obj_type_is_memory(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check whether this object type is a Cache (Data, Unified or Instruction).  
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_cache()</tt>.
	 *
	 * @return <code>true</code> if this object is a Cache, <code>false</code> otherwise. 
	 */
	public boolean is_cache() {
		int rc = jhwloc_obj_type_is_cache(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check whether this object type is a Data or Unified Cache.  
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_dcache()</tt>.
	 *
	 * @return <code>true</code> if this object is a Data or Unified
	 * Cache, <code>false</code> otherwise. 
	 */
	public boolean is_dcache() {
		int rc = jhwloc_obj_type_is_dcache(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Check whether this object type is an Instruction Cache.  
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_obj_type_is_icache()</tt>.
	 *
	 * @return <code>true</code> if this object is an Instruction Cache,
	 * <code>false</code> otherwise. 
	 */
	public boolean is_icache() {
		int rc = jhwloc_obj_type_is_icache(HwlocObjectType.GetType(this.type));

		if(rc == 1)
			return true;
		else
			return false;
	}

	/********************** PRIVATE NATIVE METHODS 	**********************/
	private native String jhwloc_obj_type_string(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_normal(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_io(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_memory(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_cache(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_dcache(int jhwloc_obj_type);
	private native int jhwloc_obj_type_is_icache(int jhwloc_obj_type);
}
