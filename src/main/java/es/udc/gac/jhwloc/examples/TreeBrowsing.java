package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class TreeBrowsing {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();
		HwlocObject obj = null;

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		obj = topo.get_root_obj();

		if(obj != null)
			print(obj);

		/* terminate this topology context */
		topo.destroy();
	}

	private static void print(HwlocObject obj) {
		System.out.println("obj type "+obj.toString()+" depth "+obj.getDepth()+" os_index "+obj.getOS_index());

		HwlocObject[] children = obj.getChildren();

		if (children != null) {
			for(int i=0;i<children.length;i++)
				print(children[i]);
		}
	}
}
