package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;

public class Cache {
	public static void main(String[] args) {
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		int levels = 0;
		int size = 0;
		HwlocObject obj;
		HwlocObjectCacheAttr cacheAttr;

		for (obj = topo.get_obj_by_type(HWLOC.OBJ_PU, 0); obj != null; obj = obj.getParent()) {
			if (obj.is_cache()) {
				levels++;
				cacheAttr = (HwlocObjectCacheAttr) obj.getAttr();
				size += cacheAttr.getSize();
				System.out.println("Cache level "+levels+" with "+cacheAttr.getSize()+" bytes and associativity "+cacheAttr.getAssociativity());
			}
		}

		int total = size / 1024;
		System.out.println("Logical processor 0 has "+levels+" caches totaling "+total+" KiB");

		/* terminate this topology context */
		topo.destroy();
	}
}
