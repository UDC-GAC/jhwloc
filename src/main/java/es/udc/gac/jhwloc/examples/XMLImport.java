package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;
import java.io.File;
import java.util.EnumSet;

public class XMLImport {
	public static void main(String[] args) {
		if(args.length != 1) {
			System.out.println("Usage: java jhwloc.examples.XMLImport topology_file_path");
			System.exit(0);
		}

		File file = new File(args[0]);

		if (!file.exists()) {
			System.out.println("XML topology file "+args[0]+" not found!");
			System.exit(0);
		}

		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();

		/* Enable XML-file based topology */
		int rc = topo.set_xml(file.getAbsolutePath());

		if (rc == -1)
		{
			System.out.println("Error while loading XML topology file from "+file.getAbsolutePath());
			System.exit(0);
		}

		/* This flag is needed before loading the topology to re-enable CPU binding */
		rc = topo.set_flags(EnumSet.of(HWLOC.TOPOLOGY_FLAG_IS_THISSYSTEM));

		if (rc == -1) {
			System.out.println("topo.set_flags() failed!");
			System.exit(0);
		}

		/* build the XML topology imported above */		
		rc = topo.load();

		if (rc == -1) {
			System.out.println("topo.load() failed!");
			System.exit(0);
		}

		System.out.println("XML topology file loaded from "+args[0]);

		System.out.println("Display the first object of each level:");
		HwlocObject obj = null;
		for(int i=0; i<topo.get_depth(); i++) {
			obj = topo.get_obj_by_depth(i, 0);
			print(obj);
		}

		/* terminate this topology context */
		topo.destroy();
	}

	private static void print(HwlocObject obj) {
		System.out.println("obj type "+obj.toString()+" depth "+obj.getDepth()+" os_index "+obj.getOS_index());
	}
}
