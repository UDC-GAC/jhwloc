package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;
import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocMEMBindPolicy;

import java.util.EnumSet;

public class MEMBinding {
	public static void main(String[] args) throws InterruptedException {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		HwlocBitmap cset;
		HwlocBitmap set;

		/* retrieve the entire set of NUMA nodes and count them */
		cset = topo.get_topology_nodeset();
		int nbnodes = cset.weight();
		/* there's always at least one NUMA node */
		System.out.println("There are "+nbnodes+" NUMA nodes in the machine");

		/* get the process memory binding as a nodeset */
		set = HwlocBitmap.alloc();
		if (set == null) {
			System.err.println("Failed to allocate a bitmap");
			topo.destroy();
			System.exit(-1);
		}

		HwlocMEMBindPolicy policy = topo.get_membind(set, EnumSet.of(HWLOC.MEMBIND_BYNODESET));
		if (policy ==  null) {
			System.err.println("Failed to retrieve memory binding and policy");
			topo.destroy();
			System.exit(-1);
		}

		System.out.println("Memory binding policy is "+policy.name());

		/* print the corresponding NUMA nodes */
		System.out.println("Bound to nodeset "+set+" with contains:");

		for (int i = set.first(); i != -1; i = set.next(i)) { 			
			HwlocObject obj = topo.get_numanode_obj_by_os_index(i);

			if (obj != null) {
				HwlocObjectNUMANodeAttr numaAttr = (HwlocObjectNUMANodeAttr) obj.getAttr();
				System.out.println("  NUMA node "+obj.getLogical_index()+" (OS index "+i+") with "+numaAttr.getLocalMemory()+" bytes of memory");
			}
		}

		HwlocObject obj = topo.get_obj_by_type(HWLOC.OBJ_NUMANODE, 0);
		System.out.println("Binding by nodeset "+obj.getNodeSet()+" using HWLOC_MEMBIND_BIND policy");
		topo.set_membind(obj.getNodeSet(), HWLOC.MEMBIND_BIND, EnumSet.of(HWLOC.MEMBIND_BYNODESET));

		policy = topo.get_membind(set, EnumSet.of(HWLOC.MEMBIND_BYNODESET));
		if (policy ==  null) {
			System.err.println("Failed to retrieve memory binding and policy");
			topo.destroy();
			System.exit(-1);
		}

		System.out.println("Memory binding policy is now "+policy.name());

		/* terminate this topology context */
		topo.destroy();
	}
}
