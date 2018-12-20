package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class Ancestor {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		HwlocObject root = topo.get_root_obj();
		HwlocObject firstpu = topo.get_obj_by_type(HWLOC.OBJ_PU, 0);

		System.out.println("isAncestor(firstpu, root): "+isAncestor(firstpu, root));

		/* terminate this topology context */
		topo.destroy();
	}

	private static boolean isAncestor(HwlocObject obj1, HwlocObject obj2) {
		while (obj1 != null) {
			if (obj1 == obj2)
				return true;

			obj1 = obj1.getParent();
		}
		return false;
	}
}
