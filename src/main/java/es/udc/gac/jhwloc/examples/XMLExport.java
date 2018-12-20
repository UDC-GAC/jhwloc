package es.udc.gac.jhwloc.examples;

import es.udc.gac.jhwloc.*;
import java.io.File;
import java.io.IOException;

public class XMLExport {
	public static void main(String[] args) throws IOException {
		File file = null;
		HwlocTopology topo = new HwlocTopology();

		/* initialize a topology context */
		topo.init();
		/* build the topology created and configured above */		
		topo.load();

		try {
			file = File.createTempFile("topo.xml", null);
		} catch(IOException ioe) {
			throw ioe;
		}

		int rc = topo.export_xml(file.getAbsolutePath());

		if (rc == -1) {
			System.out.println("topo.export_xml() failed!");
			System.exit(0);
		}

		System.out.println("XML topology file created at "+file.getAbsolutePath());

		/* terminate this topology context */
		topo.destroy();
	}
}
