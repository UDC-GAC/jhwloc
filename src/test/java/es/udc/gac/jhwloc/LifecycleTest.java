package es.udc.gac.jhwloc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

/**
 * Using a topology that was not initialized or that was already destroyed used to
 * pass a null pointer to hwloc, which crashed the JVM.
 */
class LifecycleTest {

	private static HwlocTopology loadedTopology() {
		HwlocTopology topo = new HwlocTopology();
		assertEquals(0, topo.init());
		assertEquals(0, topo.set_synthetic("core:2 pu:2"));
		assertEquals(0, topo.load());
		return topo;
	}

	@Test
	void usingATopologyThatWasNotInitializedThrows() {
		HwlocTopology topo = new HwlocTopology();

		assertThrows(IllegalStateException.class, () -> topo.load());
		assertThrows(IllegalStateException.class, () -> topo.get_nbobjs_by_type(HWLOC.OBJ_CORE));
		assertThrows(IllegalStateException.class, () -> topo.get_type_depth(HWLOC.OBJ_PU));
	}

	@Test
	void usingADestroyedTopologyThrows() {
		HwlocTopology topo = loadedTopology();
		assertEquals(2, topo.get_nbobjs_by_type(HWLOC.OBJ_CORE));

		topo.destroy();

		assertThrows(IllegalStateException.class, () -> topo.get_nbobjs_by_type(HWLOC.OBJ_CORE));
		assertThrows(IllegalStateException.class, () -> topo.get_nbobjs_by_depth(0));
		assertThrows(IllegalStateException.class, () -> topo.export_synthetic());
	}

	@Test
	void theMessageSaysWhatIsWrong() {
		HwlocTopology topo = new HwlocTopology();

		IllegalStateException e = assertThrows(IllegalStateException.class, () -> topo.get_depth());
		assertEquals("The topology is not initialized or was destroyed", e.getMessage());
	}

	@Test
	void aDestroyedTopologyDoesNotAffectAnotherOne() {
		HwlocTopology first = loadedTopology();
		HwlocTopology second = loadedTopology();

		first.destroy();

		assertEquals(2, second.get_nbobjs_by_type(HWLOC.OBJ_CORE));
		second.destroy();
	}
}
