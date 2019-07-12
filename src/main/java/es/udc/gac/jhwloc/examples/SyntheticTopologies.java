package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;
import java.io.IOException;

public class SyntheticTopologies {
	public static void main(String[] args) throws IOException {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		int ncores = topo.get_nbobjs_by_type(HWLOC.OBJ_CORE);
		int npus = topo.get_nbobjs_by_type(HWLOC.OBJ_PU);

		System.out.println("Current topology has "+ncores+" cores and "+npus+" hardware threads");
		
		String synthetic_topo = topo.export_synthetic();

		if (synthetic_topo == null) {
			System.out.println("topo.export_synthetic() failed!");
			System.exit(0);
		}

		topo.destroy();

		System.out.println("Exported current topology as:");
		System.out.println(synthetic_topo);

		HwlocTopology new_topo = new HwlocTopology();
		new_topo.init();

		synthetic_topo = "Package:1 [NUMANode(memory=12871360512)] L3Cache:1(size=12582912) L2Cache:4(size=262144) L1dCache:1(size=32768) L1iCache:1(size=32768) Core:1 PU:2";
		System.out.println("Import the following synthetic topology:");
		System.out.println(synthetic_topo);

		int rc = new_topo.set_synthetic(synthetic_topo);

		if (rc == -1) {
			System.out.println("topo.set_synthetic() failed!");
			System.exit(0);
		}

		new_topo.load();

		ncores = new_topo.get_nbobjs_by_type(HWLOC.OBJ_CORE);
		npus = new_topo.get_nbobjs_by_type(HWLOC.OBJ_PU);

		System.out.println("Imported synthetic topology has "+ncores+" cores and "+npus+" hardware threads");

		/* terminate this topology context */
		new_topo.destroy();
	}
}
