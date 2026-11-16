package es.udc.gac.jhwloc;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

// This class must not touch HWLOC or Topology before the bitmap: it checks that HwlocBitmap loads the native library by
// itself. It needs its own JVM (reuseForks=false in the pom), because any other class that ran first would have loaded it.
class BitmapFirstUseTest {

	@Test
	void bitmapWorksBeforeAnyTopologyExists() {
		HwlocBitmap bitmap = HwlocBitmap.alloc();
		assertNotNull(bitmap);

		try {
			bitmap.set(3);
			assertTrue(bitmap.isset(3));
		} finally {
			bitmap.free();
		}
	}
}
