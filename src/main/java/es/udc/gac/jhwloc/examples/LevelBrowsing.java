package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class LevelBrowsing {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();
		HwlocObject obj = null;
		int i;

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		System.out.println("Display the first object of each level:");

		for(i=0; i<topo.get_depth(); i++) {
			obj = topo.get_obj_by_depth(i, 0);
			print(obj);
		}

		System.out.println("Display all objects of the PU level:");

		for(i=0; i<topo.get_nbobjs_by_type(HWLOC.OBJ_PU); i++) {
			obj = topo.get_obj_by_type(HWLOC.OBJ_PU, i);
			print(obj);
		}

		System.out.println("Display all objects of the last level:");

		int depth = topo.get_depth() - 1;
		obj = null;

		while ((obj = topo.get_next_obj_by_depth(depth, obj)) != null)
			print(obj);

		/* terminate this topology context */
		topo.destroy();
	}

	private static void print(HwlocObject obj) {
		System.out.println("obj type "+obj.toString()+" depth "+obj.getDepth()+" os_index "+obj.getOS_index());
	}
}
