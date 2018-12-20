package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.HwlocTopology;
import es.udc.gac.jhwloc.HWLOC;

public class HelloWorld {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		/* get the number of objects of type HWLOC.OBJ_CORE */
		int ncores = topo.get_nbobjs_by_type(HWLOC.OBJ_CORE);

		/* get the number of objects of type HWLOC.OBJ_PU */
		int npus = topo.get_nbobjs_by_type(HWLOC.OBJ_PU);

		System.out.println("Hello World from jhwloc: This machine has "+ncores+" cores and "+npus+" hardware threads");

		/* terminate this topology context */
		topo.destroy();
	}
}
