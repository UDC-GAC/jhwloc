package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class NUMA {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		int i = 0;
		HwlocObject obj;
		HwlocObjectNUMANodeAttr numaAttr;

		/* get the number of objects of type HWLOC.OBJ_NUMANODE */
		int nodes = topo.get_nbobjs_by_type(HWLOC.OBJ_NUMANODE);

		for(i=0; i<nodes; i++) {
			obj = topo.get_obj_by_type(HWLOC.OBJ_NUMANODE, i);
			numaAttr = (HwlocObjectNUMANodeAttr) obj.getAttr();
			System.out.println("NUMA node "+nodes+" with "+numaAttr.getLocalMemory()+" bytes of local memory");
		}

		long total = topo.get_root_obj().getTotalMemory() / (1024*1024);
		System.out.println("This machine has "+nodes+" NUMA nodes totaling "+total+" MiB of memory");

		/* terminate this topology context */
		topo.destroy();
	}
}
