package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class Bitmaps {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		int nbpus = topo.get_nbobjs_by_type(HWLOC.OBJ_PU);
		HwlocObject firstpu = topo.get_obj_by_type(HWLOC.OBJ_PU, 0);
		HwlocObject lastpu = topo.get_obj_by_type(HWLOC.OBJ_PU, nbpus-1);

		HwlocBitmap set = (HwlocBitmap) firstpu.getCPUSet().clone();
		System.out.println("bitmap for first PU is "+set);
		System.out.println("bitmap for last  PU is "+lastpu.getCPUSet());
		HwlocBitmap res = set.or(lastpu.getCPUSet());
		System.out.println("OR bitmap is "+res);
		System.out.println("OR bitmap (list string) is "+res.toStringList());
		System.out.println("OR bitmap (taskset string) is "+res.toStringTasket());
		set.free();
		res.free();

		/* terminate this topology context */
		topo.destroy();
	}
}
