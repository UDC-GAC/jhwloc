package es.udc.gac.jhwloc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests on a synthetic topology, so that the results do not depend on the machine:
 * 2 NUMA nodes with 2 cores each and 2 processing units per core.
 */
class TopologyTest {
	private HwlocTopology topo;

	@BeforeEach
	void load() {
		topo = new HwlocTopology();
		assertEquals(0, topo.init());
		assertEquals(0, topo.set_synthetic("node:2 core:2 pu:2"));
		assertEquals(0, topo.load());
	}

	@AfterEach
	void destroy() {
		topo.destroy();
	}

	@Test
	void countsObjectsByType() {
		assertEquals(1, topo.get_nbobjs_by_type(HWLOC.OBJ_MACHINE));
		assertEquals(2, topo.get_nbobjs_by_type(HWLOC.OBJ_NUMANODE));
		assertEquals(4, topo.get_nbobjs_by_type(HWLOC.OBJ_CORE));
		assertEquals(8, topo.get_nbobjs_by_type(HWLOC.OBJ_PU));
	}

	@Test
	void findsObjectsAndTheirCpusets() {
		HwlocObject core = topo.get_obj_by_type(HWLOC.OBJ_CORE, 3);

		assertNotNull(core);
		assertEquals(HWLOC.OBJ_CORE, core.getType());
		assertEquals(3, core.getLogical_index());
		assertEquals(2, core.getCPUSet().weight());
		assertEquals(6, core.getCPUSet().first());
		assertEquals(8, topo.get_root_obj().getCPUSet().weight());
	}

	@Test
	void specialDepthsAreTheValuesOfHwloc() {
		assertEquals(-1, HWLOC.TYPE_DEPTH_UNKNOWN);
		assertEquals(-2, HWLOC.TYPE_DEPTH_MULTIPLE);
		assertEquals(-3, HWLOC.TYPE_DEPTH_NUMANODE);
		assertEquals(Integer.MAX_VALUE, HWLOC.TYPE_UNORDERED);
	}

	@Test
	void specialDepthsCanBeComparedAndUsedAsInput() {
		assertEquals(HWLOC.TYPE_DEPTH_NUMANODE, topo.get_type_depth(HWLOC.OBJ_NUMANODE));
		assertEquals(2, topo.get_nbobjs_by_depth(HWLOC.TYPE_DEPTH_NUMANODE));
		assertEquals(HWLOC.OBJ_NUMANODE, topo.get_depth_type(HWLOC.TYPE_DEPTH_NUMANODE));
		assertEquals(topo.get_obj_by_type(HWLOC.OBJ_CORE, 0).getDepth(), topo.get_type_depth(HWLOC.OBJ_CORE));
	}

	@Test
	void unknownDepthIsReturnedWhenThereIsNoSuchLevel() {
		assertEquals(HWLOC.TYPE_DEPTH_UNKNOWN, topo.get_type_depth(HWLOC.OBJ_L3CACHE));
		// It used to throw a HwlocException
		assertEquals(HWLOC.TYPE_DEPTH_UNKNOWN, topo.get_cache_type_depth(3, HWLOC.OBJ_CACHE_UNIFIED));
	}

	@Test
	void comparesObjectTypes() {
		assertTrue(HWLOC.compare_types(HWLOC.OBJ_PU, HWLOC.OBJ_MACHINE) > 0);
		assertTrue(HWLOC.compare_types(HWLOC.OBJ_MACHINE, HWLOC.OBJ_PU) < 0);
		assertEquals(0, HWLOC.compare_types(HWLOC.OBJ_CORE, HWLOC.OBJ_CORE));
		// Memory objects cannot be compared with the objects that contain processors
		assertEquals(HWLOC.TYPE_UNORDERED, HWLOC.compare_types(HWLOC.OBJ_NUMANODE, HWLOC.OBJ_CORE));
	}

	@Test
	void destroyCanBeCalledMoreThanOnce() {
		HwlocTopology other = new HwlocTopology();
		other.init();
		other.destroy();
		other.destroy();

		// A topology that was never initialized is ignored too
		new HwlocTopology().destroy();
	}

	@Test
	void canBeUsedInTryWithResources() {
		try (HwlocTopology other = new HwlocTopology()) {
			assertEquals(0, other.init());
			assertEquals(0, other.set_synthetic("core:3 pu:2"));
			assertEquals(0, other.load());
			assertEquals(3, other.get_nbobjs_by_type(HWLOC.OBJ_CORE));
		}
	}
}
