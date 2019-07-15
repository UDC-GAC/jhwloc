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

import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocCPUBindFlags;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocMEMBindFlags;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocMEMBindPolicy;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectCacheType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectType;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocTopologyFlags;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocTypeFilter;

public class HwlocTopology implements Cloneable {
	private long handler;
	private HwlocObject root;

	static {
		@SuppressWarnings("unused")
		int version = HWLOC.API_VERSION;
	}

	public HwlocTopology() {
		this.root = null;
	}

	private void setHandler(long handler) {
		this.handler = handler;
	}

	/**
	 * Allocate a topology context.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_init()</tt>.
	 *
	 * @return 0 on success, -1 on error.
	 */
	public int init() {
		return jhwloc_topology_init();
	}

	/**
	 * Build the actual topology once initialized with <tt>init()</tt>. No other routine may 
	 * be called earlier using this topology.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_load()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>On failure, the topology is reinitialized. It should be either destroyed with 
	 * <tt>destroy()</tt> or configured and loaded again.
	 * <li>This function may be called only once per topology.
	 * <li>The binding of the current thread or process may temporarily change during this
	 * call but it will be restored before it returns.
	 * </ul>
	 *
	 * @return 0 on success, -1 on error.
	 */
	public int load() {
		int rc = jhwloc_topology_load();

		if(rc == 0)
			this.root = jhwloc_get_root_obj();

		return rc;
	}

	/**
	 * Terminate and free a topology context.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_destroy()</tt>.
	 */
	public void destroy() {
		jhwloc_topology_destroy();
		this.root = null;
	}

	/**
	 * Verify that the topology is compatible with the current hwloc library.
	 * <p>
	 * This is useful when using the same topology structure (in memory) in
	 * different libraries that may use different hwloc installations (for
	 * instance if one library embeds a specific version of hwloc, while another
	 * library uses a default system-wide hwloc installation).
	 * <p>
	 * If all libraries/programs use the same hwloc installation, this function
	 * always returns success.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_abi_check()</tt>.
	 * 
	 * @return 0 on success, -1 on error.
	 */
	public int abi_check() {
		return jhwloc_topology_abi_check();
	}

	/**
	 * Run internal checks on a topology structure. The program aborts if an inconsistency 
	 * is detected in the given topology.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_check()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>This routine is only useful to developers.
	 * <li>The input topology should have been previously loaded with <tt>load()</tt>.
	 * </ul>
	 */
	public void check() {
		jhwloc_topology_check();
	}

	/**
	 * Duplicate a topology.
	 * <p>
	 * The entire topology structure as well as its objects are duplicated into a new one.
	 * <p>
	 * This is useful for keeping a backup while modifying a topology.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_dup()</tt>.
	 *
	 * @return A copy of this topology object or <tt>null</tt>.
	 */
	@Override
	public Object clone() {
		Object obj = null;

		long handler = jhwloc_topology_dup();

		if (handler == -1)
			return null;

		try {
			obj = super.clone();
		} catch(CloneNotSupportedException ex) {
			throw new HwlocException("Topology cannot be duplicated", ex);
		}

		((HwlocTopology) obj).setHandler(handler);

		return obj;
	}

	/**
	 * Get the depth of the hierarchical tree of objects.
	 * <p>
	 * This is the depth of <tt>HWLOC.OBJ_PU</tt> objects plus one.
	 * <p>
	 * Note
	 * <ul>
	 * <li> NUMA nodes, I/O and Misc objects are ignored when computing the
	 * depth of the tree (they are placed on special levels).
	 * </ul>
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_depth()</tt>.
	 *
	 * @return The depth of the hierarchical tree of objects.
	 */
	public int get_depth() {
		return jhwloc_topology_get_depth();
	}

	/**
	 * Does the topology context come from this system?
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_is_thissystem()</tt>.
	 *
	 * @return <code>true</code> if this topology was built using the system running 
	 * this program, <code>false</code> instead (for instance if using another file-system 
	 * root, a XML topology file, or a synthetic topology).
	 */
	public boolean is_thissystem() {
		int rc = jhwloc_topology_is_thissystem();

		if(rc == 1)
			return true;
		else
			return false;
	}

	/**
	 * Get OR'ed flags of <tt>HwlocTopologyFlag</tt> on this topology.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_flags()</tt>.
	 *
	 * @return the flags previously set with <tt>set_flags()</tt>.
	 */
	public EnumSet<HwlocTopologyFlags> get_flags() {
		long flags = jhwloc_topology_get_flags();
		return HwlocTopologyFlags.Hwloc2JavaFlags(flags);
	}

	/**
	 * Returns the type of objects at depth <tt>depth</tt>.
	 * <p>
	 * Note that <tt>depth</tt> should between 0 and <tt>get_depth()</tt>-1.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_depth_type()</tt>.
	 * 
	 * @param depth Depth.
	 * @return The type of objects at depth <tt>depth</tt> or <tt>null</tt> if
	 * depth <tt>depth</tt> does not exist.
	 */
	public HwlocObjectType get_depth_type(int depth) {
		int type = jhwloc_get_depth_type(depth);

		if(type == -1)
			return null;

		return HwlocObjectType.GetType(type);
	}

	/**
	 * Returns the depth of objects of type <tt>type</tt>.
	 * <p> 
	 * Java binding of the hwloc operation <tt>hwloc_get_type_depth()</tt>.
	 *
	 * @param type Object type.
	 * @return the depth of objects of type <tt>type</tt>. If no object of this type is
	 * present on the underlying architecture, or if the OS doesn't provide this kind
	 * of information, the function returns HWLOC.TYPE_DEPTH_UNKNOWN.
	 * <p>
	 * If type is absent but a similar type is acceptable, see also <tt>get_type_or_below_depth()</tt>
	 * and <tt>get_type_or_above_depth()</tt>.
	 * <p>
	 * If HWLOC.OBJ_GROUP is given, the function may return HWLOC.TYPE_DEPTH_MULTIPLE
	 * if multiple levels of Groups exist.
	 * <p>
	 * If a NUMA node, I/O or Misc object type is given, the function returns a virtual
	 * value because these objects are stored in special levels that are not CPU-related.
	 * This virtual depth may be passed to other hwloc functions such as <tt>get_obj_by_depth()</tt>
	 * but it should not be considered as an actual depth by the application. In particular,
	 * it should not be compared with any other object depth or with the entire topology depth.
	 */
	public int get_type_depth(HwlocObjectType type) {
		return jhwloc_get_type_depth(HwlocObjectType.GetType(type));
	}

	/**
	 * Returns the depth of objects of type <tt>type</tt> or above.
	 * <p> 
	 * Java binding of the hwloc operation <tt>hwloc_get_type_or_above_depth()</tt>.
	 *
	 * @param type Object type.
	 * @return the depth of objects of type <tt>type</tt> or above. If no object of this type is
	 * present on the underlying architecture, the function returns the depth of the first
	 * "present" object typically containing <tt>type</tt>.
	 * <p>
	 * This function is only meaningful for normal object types.  If a memory, I/O or Misc 
	 * object type is given, the corresponding virtual depth is always returned (see <tt>get_type_depth()</tt>).
	 * <p>
	 * May return HWLOC.TYPE_DEPTH_MULTIPLE for HWLOC.OBJ_GROUP just like <tt>get_type_depth()</tt>.
	 */
	public int get_type_or_above_depth(HwlocObjectType type) {
		return jhwloc_get_type_or_above_depth(HwlocObjectType.GetType(type));
	}

	/**
	 * Returns the depth of objects of type <tt>type</tt> or below.
	 * <p> 
	 * Java binding of the hwloc operation <tt>hwloc_get_type_or_below_depth()</tt>.
	 *
	 * @param type Object type.
	 * @return the depth of objects of type <tt>type</tt> or below. If no object of this type is
	 * present on the underlying architecture, the function returns the depth of the first
	 * "present" object typically found inside <tt>type</tt>.
	 * <p>
	 * This function is only meaningful for normal object types.  If a memory, I/O or Misc 
	 * object type is given, the corresponding virtual depth is always returned (see <tt>get_type_depth()</tt>).
	 * <p>
	 * May return HWLOC.TYPE_DEPTH_MULTIPLE for HWLOC.OBJ_GROUP just like <tt>get_type_depth()</tt>.
	 */
	public int get_type_or_below_depth(HwlocObjectType type) {
		return jhwloc_get_type_or_below_depth(HwlocObjectType.GetType(type));
	}

	/**
	 * Returns the width of level type <tt>type</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_nbobjs_by_type()</tt>.
	 *
	 * @param type Object type.
	 * @return If no object for that <tt>type</tt> exists, 0 is returned. If there are several 
	 * levels with objects of that <tt>type</tt>, -1 is returned.
	 */
	public int get_nbobjs_by_type(HwlocObjectType type) {
		return jhwloc_get_nbobjs_by_type(HwlocObjectType.GetType(type));
	}

	/**
	 * Returns the width of level at depth <tt>depth</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_nbobjs_by_depth()</tt>.
	 * 
	 * @param depth Depth.
	 * @return -1 if depth <tt>depth</tt> does not exist.
	 */
	public int get_nbobjs_by_depth(int depth){
		return jhwloc_get_nbobjs_by_depth(depth);
	}

	/**
	 * Returns the topology object at logical index <tt>idx</tt> with type <tt>type</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_obj_by_type()</tt>.	
	 *
	 * @param type Object type.
	 * @param idx Logical index.
	 * @return If no object for that <tt>type</tt> exists, <tt>null</tt> is returned. If
	 * there are several levels with objects of that <tt>type</tt> (HWLOC.OBJ_GROUP),
	 * <tt>null</tt> is returned and the caller may fallback to <tt>get_obj_by_depth()</tt>.
	 */
	public HwlocObject get_obj_by_type(HwlocObjectType type, int idx){
		long handler = jhwloc_get_obj_by_type(HwlocObjectType.GetType(type), idx);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Returns the topology object at logical index <tt>idx</tt> from depth <tt>depth</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_obj_by_depth()</tt>.
	 *
	 * @param depth Depth.
	 * @param idx Logical index.
	 * @return If no object for that index and depth exists, <tt>null</tt> is returned. 
	 */
	public HwlocObject get_obj_by_depth(int depth, int idx) {
		long handler = jhwloc_get_obj_by_depth(depth, idx);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Export the topology into an XML file.
	 * <p>
	 * This file may be loaded later through <tt>set_xml()</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_export_xml()</tt>.
	 * 
	 * @param xmlpath Path to XML file.
	 * @return 0 on success, -1 on error.
	 */
	public int export_xml(String xmlpath) {
		return jhwloc_topology_export_xml(xmlpath);
	}

	/**
	 * Enable XML-file based topology.
	 * <p>
	 * Gather topology information from the XML file given at xmlpath. Setting the environment variable 
	 * HWLOC_XML-FILE may also result in this behavior. This file may have been generated earlier with 
	 * <tt>export_xml()</tt> or lstopo file.xml.
	 * <p>
	 * Note that this method does not actually load topology information; it just tells hwloc where 
	 * to load it from. You’ll still need to invoke <tt>load()</tt> to actually load the topology information.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_xml()</tt>.
	 *
	 * @param xmlpath Path to XML file.
	 * @return 0 on success, -1 on error.
	 */
	public int set_xml(String xmlpath) {
		return jhwloc_topology_set_xml(xmlpath);
	}

	/**
	 * Export the topology as a synthetic string.
	 * <p>
	 * The exported string may be given back to <tt>set_synthetic()</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_export_synthetic()</tt>.
	 * 
	 * @return The string representing the topology or <tt>null</tt> if the topology could not be 
	 * exported, for instance if it is not symmetric.
	 */
	public String export_synthetic() {
		return jhwloc_topology_export_synthetic();
	}

	/**
	 * Enable synthetic topology.
	 * <p>
	 * Gather topology information from the given description, a space-separated string of
	 * {@literal <}type:number{@literal >}
	 * describing the object type and arity at each level. All types may be omitted (space-separated
	 * string of numbers) so that hwloc chooses all types according to usual topologies.
	 * <p>
	 * Setting the environment variable HWLOC_SYNTHETIC may also result in this behavior.
	 * <p>
	 * Note that this method does not actually load topology information; it just tells hwloc where 
	 * to load it from. You’ll still need to invoke <tt>load()</tt> to actually load the topology information.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_synthetic()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>For convenience, this backend provides empty binding hooks which just return success.
	 * <li>On success, the synthetic component replaces the previously enabled component (if any), but
	 * the topology is not actually modified until <tt>load()</tt>. 
	 * </ul>
	 * 
	 * @param description String that describes the topology configuration.
	 * @return If description was properly parsed and describes a valid topology configuration, this
	 * function returns 0 on success, -1 on error.
	 */
	public int set_synthetic(String description) {
		return jhwloc_topology_set_synthetic(description);
	}

	/**
	 * Change which process the topology is viewed from.
	 * <p>
	 * On some systems, processes may have different views of the machine, for instance the set
	 * of allowed CPUs. By default, hwloc exposes the view from the current process. Calling
	 * <tt>set_pid()</tt> permits to make it expose the topology of the machine from the point
	 * of view of another process.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_pid()</tt>.
	 * 
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @return 0 on success, -1 on error.
	 */
	public int set_pid(int pid) {
		return jhwloc_topology_set_pid(pid);
	}

	/**
	 * Set OR'ed flags of <tt>HwlocTopologyFlags</tt> to this non-yet-loaded topology.
	 * <p>
	 * If this function is called multiple times, the last invocation will erase and replace the 
	 * set of flags that was previously set.
	 * <p>
	 * The flags set in the topology may be retrieved with <tt>get_flags()</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_flags()</tt>.
	 *
	 * @param flags OR'ed flags of <tt>HwlocTopologyFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int set_flags(EnumSet<HwlocTopologyFlags> flags) {
		return jhwloc_topology_set_flags(HwlocTopologyFlags.Java2HwlocFlags(flags));
	}

	/**
	 * Returns the top-object of the topology-tree.
	 * <p>
	 * Its type is <tt>HWLOC.OBJ_MACHINE</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_root_obj()</tt>.
	 *
	 * @return The top-object of the topology-tree or <tt>null</tt>.
	 */
	public HwlocObject get_root_obj() {
		if(this.root != null)
			return this.root;

		this.root = jhwloc_get_root_obj();

		return this.root;
	}

	/**
	 * Returns the next object at depth <tt>depth</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_next_obj_by_depth()</tt>.
	 * 
	 * @param depth Depth.
	 * @param prev Previous object.
	 * @return If <tt>prev</tt> is <tt>null</tt>, return the first object at depth <tt>depth</tt>.
	 */
	public HwlocObject get_next_obj_by_depth(int depth, HwlocObject prev){
		long handler = jhwloc_get_next_obj_by_depth(depth, prev);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Returns the next object of type <tt>type</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_next_obj_by_type()</tt>.
	 *
	 * @param type Object type.
	 * @param prev Previous object.
	 * @return If <tt>prev</tt> is <tt>null</tt>, return the first object at type <tt>type</tt>. If 
	 * there are multiple or no depth for given type, return <tt>null</tt> and let the caller fallback 
	 * to <tt>get_next_obj_by_depth()</tt>.
	 */
	public HwlocObject get_next_obj_by_type(HwlocObjectType type, HwlocObject prev) {
		long handler = jhwloc_get_next_obj_by_type(HwlocObjectType.GetType(type), prev);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Returns the object of type HWLOC.OBJ_NUMANODE with <tt>os_index</tt>.
	 * <p>
	 * This function is useful for converting a nodeset into the NUMA node objects it 
	 * contains. When retrieving the current binding (e.g.  with <tt>get_membind()</tt> with
	 * HWLOC.MEMBIND_BYNODESET), one may iterate over the bits of the resulting nodeset and
	 * find the corresponding NUMA nodes with this function.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_numanode_obj_by_os_index()</tt>.
	 * 
	 * @param os_index OS index.
	 * @return Return the object of type HWLOC.OBJ_NUMANODE with <tt>os_index</tt> or <tt>null</tt>
	 * otherwise.
	 */
	public HwlocObject get_numanode_obj_by_os_index(int os_index) {
		long handler = jhwloc_get_numanode_obj_by_os_index(os_index);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Returns the object of type HWLOC.OBJ_PU with <tt>os_index</tt>.
	 * <p>
	 * This function is useful for converting a CPU set into the PU objects it contains
	 *  When retrieving the current binding (e.g. with <tt>get_cpubind()</tt>), one may
	 *  iterate over the bits of the resulting CPU set and find the corresponding PUs with
	 *  this function.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_pu_obj_by_os_index()</tt>.
	 * 
	 * @param os_index OS index.
	 * @return Return the object of type HWLOC.OBJ_PU with <tt>os_index</tt> or <tt>null</tt>
	 * otherwise.
	 */
	public HwlocObject get_pu_obj_by_os_index(int os_index) {
		long handler = jhwloc_get_pu_obj_by_os_index(os_index);

		if(handler == -1)
			return null;

		return HwlocTopology.GetHwlocObject(this.root, handler);
	}

	/**
	 * Bind current process or thread on CPUs given in physical <tt>cpuset</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_set_cpubind()</tt>.
	 * 
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @throws HwlocException if the action is not supported or the binding cannot be enforced.
	 */
	public void set_cpubind(HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) throws HwlocException {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_set_cpubind(cpuset, hwloc_flags);

		if(rc == -1)
			throw new HwlocException("An error has occurred");
		if(rc == -2)
			throw new HwlocException("Action is not supported");
		if(rc == -3)
			throw new HwlocException("Binding cannnot be enforced");
	}

	/**
	 * Bind a process <tt>pid</tt> on CPUs given in physical <tt>cpuset</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_set_proc_cpubind()</tt>.
	 * 
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @throws HwlocException if the action is not supported or the binding cannot be enforced.
	 */
	public void set_proc_cpubind(int pid, HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) throws HwlocException {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_set_proc_cpubind(pid, cpuset, hwloc_flags);

		if(rc == -1)
			throw new HwlocException("An error has occurred");
		if(rc == -2)
			throw new HwlocException("Action is not supported");
		if(rc == -3)
			throw new HwlocException("Binding cannnot be enforced");
	}

	/**
	 * Bind a thread <tt>thread</tt> on CPUs given in physical <tt>cpuset</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_set_thread_cpubind()</tt>.
	 * 
	 * @param tid <tt>pthread_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @throws HwlocException if the action is not supported or the binding cannot be enforced.
	 */
	public void set_thread_cpubind(int tid, HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) throws HwlocException {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_set_thread_cpubind(tid, cpuset, hwloc_flags);

		if(rc == -1)
			throw new HwlocException("An error has occurred");
		if(rc == -2)
			throw new HwlocException("Action is not supported");
		if(rc == -3)
			throw new HwlocException("Binding cannnot be enforced");
	}

	/**
	 * Get current process or thread binding.
	 * <p>
	 * Writes into <tt>cpuset</tt> the physical cpuset which the process or thread (according to
	 * <tt>flags</tt>) was last bound to.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_cpubind()</tt>.
	 * 
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int get_cpubind(HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		return jhwloc_get_cpubind(cpuset, hwloc_flags);
	}

	/**
	 * Get the current physical binding of process <tt>pid</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_proc_cpubind()</tt>.
	 *
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int get_proc_cpubind(int pid, HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		return jhwloc_get_proc_cpubind(pid, cpuset, hwloc_flags);
	}

	/**
	 * Get the current physical binding of thread <tt>thread</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_thread_cpubind()</tt>.
	 * 
	 * @param tid <tt>pthread_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int get_thread_cpubind(int tid, HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		return jhwloc_get_thread_cpubind(tid, cpuset, hwloc_flags);
	}

	/**
	 * Get the last physical CPU where the current process or thread ran.
	 * <p>
	 * The operating system may move some tasks from one processor to another at any time according 
	 * to their binding, so this function may return something that is already outdated.
	 * <p>
	 * <tt>flags</tt> can include either <tt>HWLOC.CPUBIND_PROCESS</tt> or <tt>HWLOC.CPUBIND_THREAD</tt> to 
	 * specify whether the query should be for the whole process (union of all CPUs on which all threads are 
	 * running), or only the current thread. If the process is single-threaded, <tt>flags</tt> can be set to 
	 * <tt>null</tt> to let hwloc use whichever method is available on the underlying OS.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_last_cpu_location()</tt>.
	 * 
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int get_last_cpu_location(HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		return jhwloc_get_last_cpu_location(cpuset, hwloc_flags);
	}

	/**
	 * Get the last physical CPU where a process ran.
	 * <p>
	 * The operating system may move some tasks from one processor to another at any time according 
	 * to their binding, so this function may return something that is already outdated.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_proc_last_cpu_location()</tt>.
	 * 
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param cpuset CPU set.
	 * @param flags OR'ed flags of <tt>HwlocCPUBindFlags</tt>.
	 * @return 0 on success, -1 on error.
	 */
	public int get_proc_last_cpu_location(int pid, HwlocCPUSet cpuset, EnumSet<HwlocCPUBindFlags> flags) {

		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocCPUBindFlags.Java2HwlocFlags(flags);

		return jhwloc_get_proc_last_cpu_location(pid, cpuset, hwloc_flags);
	}

	/**
	 * Get complete CPU set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_complete_cpuset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned cpuset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the complete CPU set of logical processors of the system or <tt>null</tt>.
	 */
	public HwlocCPUSet get_complete_cpuset() {
		long handler = jhwloc_topology_get_complete_cpuset();

		if(handler == -1)
			return null;

		return new HwlocCPUSet(handler);
	}

	/**
	 * Get allowed CPU set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_allowed_cpuset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned cpuset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the CPU set of allowed logical processors of the system or <tt>null</tt>.
	 */
	public HwlocCPUSet get_allowed_cpuset() {
		long handler = jhwloc_topology_get_allowed_cpuset();

		if(handler == -1)
			return null;

		return new HwlocCPUSet(handler);
	}

	/**
	 * Get topology CPU set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_topology_cpuset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned cpuset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the CPU set of logical processors of the system for which hwloc provides 
	 * topology information or <tt>null</tt>. This is equivalent to the cpuset of the
	 * system object.
	 */
	public HwlocCPUSet get_topology_cpuset() {
		long handler = jhwloc_topology_get_topology_cpuset();

		if(handler == -1)
			return null;

		return new HwlocCPUSet(handler);
	}

	/**
	 * Get complete node set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_complete_nodeset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned nodeset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the complete node set of memory of the system or <tt>null</tt>.
	 */
	public HwlocNodeSet get_complete_nodeset() {
		long handler = jhwloc_topology_get_complete_nodeset();

		if(handler == -1)
			return null;

		return new HwlocNodeSet(handler);
	}

	/**
	 * Get topology node set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_topology_nodeset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned nodeset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the node set of memory of the system for which hwloc provides topology
	 * information or <tt>null</tt>. This is equivalent to the nodeset of the system
	 * object.
	 */
	public HwlocNodeSet get_topology_nodeset() {
		long handler = jhwloc_topology_get_topology_nodeset();

		if(handler == -1)
			return null;

		return new HwlocNodeSet(handler);
	}

	/**
	 * Get allowed node set.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_allowed_nodeset()</tt>.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The returned nodeset is not newly allocated and should thus not be changed or 
	 * freed; <tt>clone()</tt> must be used to obtain a local copy.
	 * </ul>
	 * 
	 * @return the node set of allowed memory of the system or <tt>null</tt>.
	 */
	public HwlocNodeSet get_allowed_nodeset() {
		long handler = jhwloc_topology_get_allowed_nodeset();

		if(handler == -1)
			return null;

		return new HwlocNodeSet(handler);
	}

	/**
	 * Set the filtering for the given object type.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_type_filter()</tt>.	
	 *
	 * @param type Object type.
	 * @param filter Filter type.
	 * @return 0 on success, -1 on error.
	 */
	public int set_type_filter(HwlocObjectType type, HwlocTypeFilter filter) {
		return jhwloc_topology_set_type_filter(HwlocObjectType.GetType(type), HwlocTypeFilter.GetType(filter));
	}

	/**
	 * Get the current filtering for the given object type.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_get_type_filter()</tt>.	
	 * 
	 * @param type Object type.
	 * @return The current filtering or <tt>null</tt>.
	 */
	public HwlocTypeFilter get_type_filter(HwlocObjectType type) {
		int rc = jhwloc_topology_get_type_filter(HwlocObjectType.GetType(type));

		if (rc == -1)
			return null;

		return HwlocTypeFilter.GetType(rc);
	}

	/**
	 * Set the filtering for all object types.
	 * <p>
	 * If some types do not support this filtering, they are silently ignored.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_all_types_filter()</tt>.	
	 * 
	 * @param filter Filter type.
	 * @return 0 on success, -1 on error.
	 */
	public int set_all_types_filter(HwlocTypeFilter filter) {
		return jhwloc_topology_set_all_types_filter(HwlocTypeFilter.GetType(filter));
	}

	/**
	 * Set the filtering for all cache object types.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_cache_types_filter()</tt>.	
	 * 
	 * @param filter Filter type.
	 * @return 0 on success, -1 on error.
	 */
	public int set_cache_types_filter(HwlocTypeFilter filter) {
		return jhwloc_topology_set_cache_types_filter(HwlocTypeFilter.GetType(filter));
	}

	/**
	 * Set the filtering for all instruction cache object types.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_icache_types_filter()</tt>.	
	 * 
	 * @param filter Filter type.
	 * @return 0 on success, -1 on error.
	 */
	public int set_icache_types_filter(HwlocTypeFilter filter) {
		return jhwloc_topology_set_icache_types_filter(HwlocTypeFilter.GetType(filter));
	}

	/**
	 * Set the filtering for all I/O object types.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_topology_set_io_types_filter()</tt>.	
	 * 
	 * @param filter Filter type.
	 * @return 0 on success, -1 on error.
	 */
	public int set_io_types_filter(HwlocTypeFilter filter) {
		return jhwloc_topology_set_io_types_filter(HwlocTypeFilter.GetType(filter));
	}

	/**
	 * Returns the depth of parents where memory objects are attached.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_memory_parents_depth()</tt>.	
	 * 
	 * @return The depth of Normal parents of all memory children if all these parents have 
	 * the same depth. For instance, the depth of the Package level if all NUMA nodes are 
	 * attached to Package objects.
	 * HWLOC.TYPE_DEPTH_MULTIPLE if Normal parents of all memory children do not have the same 
	 * depth., For instance if some NUMA nodes are attached to Packages while others are attached
	 * to Groups.
	 */
	public int get_memory_parents_depth() {
		return jhwloc_get_memory_parents_depth();
	}

	/**
	 * Query the default memory binding policy and physical locality of the current process or thread.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_membind()</tt>.	
	 * 
	 * @param set Bitmap set.
	 * @param flags OR'ed flags of <tt>HwlocMEMBindFlags</tt>.
	 * @return This function has two output parameters: <tt>set</tt> and <tt>policy</tt>. The values returned
	 * depend on both the <tt>flags</tt> passed in and the current memory binding policies and nodesets in
	 * the queried target.
	 * <p>
	 * Passing the HWLOC.MEMBIND_PROCESS flag specifies that the query target is the current policies and
	 * nodesets for all the threads in the current process. Passing HWLOC.MEMBIND_THREAD specifies that 
	 * the query target is the current policy and nodeset for only the thread invoking this function.
	 * <p>
	 * If neither of these flags are passed (which is the most portable method), the process is assumed to
	 * be single threaded. This allows hwloc to use either process-based OS functions or thread-based OS
	 * functions, depending on which are available.
	 * <p>
	 * HWLOC.MEMBIND_STRICT is only meaningful when HWLOC.MEMBIND_PROCESS is also specified. In this 
	 * case, hwloc will check the default memory policies and nodesets for all threads in the process. If
	 * they are not identical, <tt>null</tt> is returned. If they are identical, the values are returned
	 * in <tt>set</tt> and <tt>policy</tt>.
	 * <p>
	 * Otherwise, if HWLOC.MEMBIND_PROCESS is specified (and HWLOC.MEMBIND_STRICT is not specified), the
	 * default set from each thread is logically OR'ed together. If all threads' default policies are the same,
	 * <tt>policy</tt> is set to that policy. If they are different, <tt>policy</tt> is set to HWLOC.MEMBIND_MIXED.
	 * <p>
	 * In the HWLOC.MEMBIND_THREAD case (or when neither HWLOC.MEMBIND_PROCESS or HWLOC.MEMBIND_THREAD is 
	 * specified), there is only one set and policy; they are returned in <tt>set</tt> and <tt>policy</tt>, respectively.
	 * <p>
	 * If HWLOC.MEMBIND_BYNODESET is specified, <tt>set</tt> is considered a nodeset. Otherwise it's a cpuset.
	 * <p>
	 * If any other flags are specified, <tt>null</tt> is returned.
	 */
	public HwlocMEMBindPolicy get_membind(HwlocBitmap set, EnumSet<HwlocMEMBindFlags> flags) {
		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocMEMBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_get_membind(set, hwloc_flags);

		if(rc < 0)
			return null;

		return HwlocMEMBindPolicy.GetType(rc);
	}

	/**
	 * Query the default memory binding policy and physical locality of the specified process.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_proc_membind()</tt>.	
	 * 
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param set Bitmap set.
	 * @param flags OR'ed flags of <tt>HwlocMEMBindFlags</tt>.
	 * @return See <tt>get_membind()</tt>.
	 */
	public HwlocMEMBindPolicy get_proc_membind(int pid, HwlocBitmap set, EnumSet<HwlocMEMBindFlags> flags) {
		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocMEMBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_get_proc_membind(pid, set, hwloc_flags);

		if(rc < 0)
			return null;

		return HwlocMEMBindPolicy.GetType(rc);
	}

	/**
	 * Set the default memory binding policy of the current process or thread to prefer the NUMA 
	 * node(s) specified by <tt>set</tt>.
	 * <p>
	 * If neither HWLOC.MEMBIND_PROCESS nor HWLOC_MEMBIND_THREAD is specified, the current process
	 * is assumed to be single-threaded. This is the most portable form as it permits hwloc to use
	 * either process-based OS functions or thread-based OS functions, depending on which are available.
	 * <p>
	 * If HWLOC.MEMBIND_BYNODESET is specified, set is considered a nodeset. Otherwise it's a cpuset.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_set_membind()</tt>.	
	 * 
	 * @param set Bitmap set.
	 * @param policy Memory binding policy
	 * @param flags OR'ed flags of <tt>HwlocMEMBindFlags</tt>.
	 * @throws HwlocException if the action is not supported or the binding cannot be enforced. 
	 */
	public void set_membind(HwlocBitmap set, HwlocMEMBindPolicy policy, EnumSet<HwlocMEMBindFlags> flags) throws HwlocException {
		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocMEMBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_set_membind(set, HwlocMEMBindPolicy.GetType(policy), hwloc_flags);

		if(rc == -1)
			throw new HwlocException("An error has occurred");
		if(rc == -2)
			throw new HwlocException("Action is not supported");
		if(rc == -3)
			throw new HwlocException("Binding cannnot be enforced");
	}

	/**
	 * Set the default memory binding policy of the specified process or thread to prefer the NUMA 
	 * node(s) specified by <tt>set</tt>.
	 * <p>
	 * If neither HWLOC.MEMBIND_PROCESS nor HWLOC_MEMBIND_THREAD is specified, the current process
	 * is assumed to be single-threaded. This is the most portable form as it permits hwloc to use
	 * either process-based OS functions or thread-based OS functions, depending on which are available.
	 * <p>
	 * If HWLOC.MEMBIND_BYNODESET is specified, set is considered a nodeset. Otherwise it's a cpuset.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_set_proc_membind()</tt>.	
	 * 
	 * @param pid <tt>pid_t</tt> on Unix platforms, and <tt>HANDLE</tt> on native Windows platforms.
	 * @param set Bitmap set.
	 * @param policy Memory binding policy
	 * @param flags OR'ed flags of <tt>HwlocMEMBindFlags</tt>.
	 * @throws HwlocException if the action is not supported or the binding cannot be enforced. 
	 */
	public void set_proc_membind(int pid, HwlocBitmap set, HwlocMEMBindPolicy policy, EnumSet<HwlocMEMBindFlags> flags) throws HwlocException {
		int hwloc_flags;

		if(flags == null)
			hwloc_flags = 0;
		else
			hwloc_flags = HwlocMEMBindFlags.Java2HwlocFlags(flags);

		int rc = jhwloc_set_proc_membind(pid, set, HwlocMEMBindPolicy.GetType(policy), hwloc_flags);

		if(rc == -1)
			throw new HwlocException("An error has occurred");
		if(rc == -2)
			throw new HwlocException("Action is not supported");
		if(rc == -3)
			throw new HwlocException("Binding cannnot be enforced");
	}

	/**
	 * Convert a NUMA node set into a CPU set and handle non-NUMA cases.
	 * <p>
	 * If the topology contains no NUMA nodes, the machine is considered as a single memory 
	 * node, and the following behavior is used: if <tt>nodeset</tt> is empty, the returned
	 * cpuset will be emptied as well. Otherwise, the returned cpuset will be entirely filled.
	 * This is useful for manipulating memory binding sets
	 * 
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_cpuset_from_nodeset()</tt>.	
	 * 
	 * @param nodeset NUMA node set.
	 * @return CPU set or <tt>null</tt> on error. 
	 */
	public HwlocCPUSet cpuset_from_nodeset(HwlocNodeSet nodeset) {
		long handler = jhwloc_cpuset_from_nodeset(nodeset);

		if (handler == -1)
			return null;

		return new HwlocCPUSet(handler); 
	}

	/**
	 * Convert a CPU set into a NUMA node set and handle non-NUMA cases.
	 * <p>
	 * If some NUMA nodes have no CPUs at all, this function never sets their indexes in 
	 * the return node set, even if a full CPU set is given in input.
	 * <p>
	 * If the topology contains no NUMA nodes, the machine is considered as a single memory
	 * node, and the following behavior is used: If <tt>cpuset</tt> is empty, the returned
	 * nodeset will be emptied as well. Otherwise, the returned nodeset will be entirely filled.
	 * 
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_cpuset_to_nodeset()</tt>.	
	 * 
	 * @param cpuset CPU set.
	 * @return NUMA node set or <tt>null</tt> on error.
	 */
	public HwlocNodeSet cpuset_to_nodeset(HwlocCPUSet cpuset) {
		long handler = jhwloc_cpuset_to_nodeset(cpuset);

		if (handler == -1)
			return null;

		return new HwlocNodeSet(handler);
	}

	/**
	 * Find the depth of cache objects matching cache level and type.
	 * 
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_get_cache_type_depth()</tt>.	
	 * 
	 * @param cachelevel Cache level.
	 * @param cachetype Cache object type.
	 * @return Return the depth of the topology level that contains cache objects whose attributes 
	 * match <tt>cachelevel</tt> and <tt>cachetype</tt>.
	 * <p>
	 * If no cache level matches, HWLOC.TYPE_DEPTH_UNKNOWN is returned.
	 * <p>
	 * If <tt>cachetype</tt> is HWLOC.OBJ_CACHE_UNIFIED, the depth of the unique matching unified 
	 * cache level is returned.
	 * <p>
	 * If cachetype is HWLOC.OBJ_CACHE_DATA or HWLOC.OBJ_CACHE_INSTRUCTION, either a matching 
	 * cache, or a unified cache is returned.
	 * <p>
	 * If cachetype is -1, it is ignored and multiple levels may match. The function returns either 
	 * the depth of a uniquely matching level or HWLOC.TYPE_DEPTH_MULTIPLE. 
	 */
	public int get_cache_type_depth(int cachelevel, HwlocObjectCacheType cachetype) {
		return jhwloc_get_cache_type_depth(cachelevel, HwlocObjectCacheType.GetType(cachetype));
	}

	static HwlocObject GetHwlocObject(HwlocObject rootHwlocObject, long handler) {
		if (rootHwlocObject.getHandler() == handler)
			return rootHwlocObject;

		HwlocObject obj;
		HwlocObject[] children = rootHwlocObject.getChildren();

		if (children != null) {
			for (int i=0;i<children.length;i++) {

				obj = GetHwlocObject(children[i], handler);

				if(obj != null)
					return obj;
			}
		}

		children = rootHwlocObject.getMemoryChildren();

		if (children != null) {
			for (int i=0;i<children.length;i++) {

				obj = GetHwlocObject(children[i], handler);

				if(obj != null)
					return obj;
			}
		}

		return null;
	}

	/********************** PRIVATE NATIVE METHODS 	**********************/
	private native int jhwloc_topology_init();
	private native int jhwloc_topology_load();
	private native void jhwloc_topology_destroy();
	private native long jhwloc_topology_dup();
	private native int jhwloc_topology_abi_check();
	private native void jhwloc_topology_check();
	private native int jhwloc_topology_get_depth();
	private native int jhwloc_get_depth_type(int depth);
	private native int jhwloc_get_memory_parents_depth();
	private native int jhwloc_get_type_depth(int jhwloc_obj_type);
	private native int jhwloc_get_type_or_above_depth(int jhwloc_obj_type);
	private native int jhwloc_get_type_or_below_depth(int jhwloc_obj_type);
	private native int jhwloc_get_nbobjs_by_type(int jhwloc_obj_type);
	private native int jhwloc_get_nbobjs_by_depth(int depth);
	private native int jhwloc_topology_is_thissystem();
	private native long jhwloc_topology_get_flags();
	private native long jhwloc_get_obj_by_type(int jhwloc_obj_type, int idx);
	private native long jhwloc_get_obj_by_depth(int depth, int idx);
	private native long jhwloc_get_numanode_obj_by_os_index(int os_index);
	private native long jhwloc_get_pu_obj_by_os_index(int os_index);
	private native int jhwloc_topology_export_xml(String xmlpath);
	private native int jhwloc_topology_set_xml(String xmlpath);
	private native String jhwloc_topology_export_synthetic();
	private native int jhwloc_topology_set_synthetic(String description);
	private native int jhwloc_topology_set_pid(int pid);
	private native int jhwloc_topology_set_flags(long flags);
	private native HwlocObject jhwloc_get_root_obj();
	private native long jhwloc_get_next_obj_by_depth(int depth, HwlocObject prev);
	private native long jhwloc_get_next_obj_by_type(int jhwloc_obj_type, HwlocObject prev);
	private native int jhwloc_set_cpubind(HwlocCPUSet cpuset, int flags);
	private native int jhwloc_set_proc_cpubind(int pid, HwlocCPUSet cpuset, int flags);
	private native int jhwloc_set_thread_cpubind(int tid, HwlocCPUSet cpuset, int flags);
	private native int jhwloc_get_cpubind(HwlocCPUSet cpuset, int flags);
	private native int jhwloc_get_proc_cpubind(int pid, HwlocCPUSet cpuset, int flags);
	private native int jhwloc_get_thread_cpubind(int tid, HwlocCPUSet cpuset, int flags);
	private native int jhwloc_get_last_cpu_location(HwlocCPUSet cpuset, int flags);
	private native int jhwloc_get_proc_last_cpu_location(int pid, HwlocCPUSet cpuset, int flags);
	private native long jhwloc_topology_get_complete_cpuset();
	private native long jhwloc_topology_get_topology_cpuset();
	private native long jhwloc_topology_get_allowed_cpuset();
	private native long jhwloc_topology_get_complete_nodeset();
	private native long jhwloc_topology_get_topology_nodeset();
	private native long jhwloc_topology_get_allowed_nodeset();
	private native int jhwloc_topology_set_type_filter(int jhwloc_obj_type, int jhwloc_type_filter);
	private native int jhwloc_topology_get_type_filter(int jhwloc_obj_type);
	private native int jhwloc_topology_set_all_types_filter(int jhwloc_type_filter);
	private native int jhwloc_topology_set_cache_types_filter(int jhwloc_type_filter);
	private native int jhwloc_topology_set_icache_types_filter(int jhwloc_type_filter);
	private native int jhwloc_topology_set_io_types_filter(int jhwloc_type_filter);
	private native int jhwloc_get_membind(HwlocBitmap bitmap, int flags);
	private native int jhwloc_get_proc_membind(int pid, HwlocBitmap bitmap, int flags);
	private native int jhwloc_set_membind(HwlocBitmap bitmap, int membindpolicy, int flags);
	private native int jhwloc_set_proc_membind(int pid, HwlocBitmap bitmap, int membindpolicy, int flags);
	private native long jhwloc_cpuset_from_nodeset(HwlocNodeSet nodeset);
	private native long jhwloc_cpuset_to_nodeset(HwlocCPUSet cpuset);
	private native int jhwloc_get_cache_type_depth(int cachelevel, int jhwloc_obj_cache_type);
}
