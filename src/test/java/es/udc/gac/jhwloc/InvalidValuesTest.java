package es.udc.gac.jhwloc;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The Java API only produces valid enum codes, but the native methods can be
 * reached with reflection. They used to pass an invalid code to hwloc, which crashed
 * the JVM; now they must throw a HwlocException.
 */
class InvalidValuesTest {
	private static final int INVALID_CODE = 9999;
	private HwlocTopology topo;

	@BeforeEach
	void load() {
		topo = new HwlocTopology();
		topo.init();
		topo.set_synthetic("core:2 pu:2");
		topo.load();
	}

	@AfterEach
	void destroy() {
		topo.destroy();
	}

	@Test
	void compareTypesRejectsInvalidCodes() throws Exception {
		assertRejected(HWLOC.class, null, "jhwloc_compare_types", INVALID_CODE, 0);
	}

	@Test
	void depthMethodsRejectInvalidCodes() throws Exception {
		assertRejected(HwlocTopology.class, topo, "jhwloc_get_type_or_above_depth", INVALID_CODE);
		assertRejected(HwlocTopology.class, topo, "jhwloc_get_type_or_below_depth", INVALID_CODE);
		assertRejected(HwlocTopology.class, topo, "jhwloc_get_type_depth", INVALID_CODE);
	}

	private static void assertRejected(Class<?> clazz, Object target, String method, int... codes) throws Exception {
		Class<?>[] types = new Class<?>[codes.length];
		Object[] args = new Object[codes.length];

		for (int i = 0; i < codes.length; i++) {
			types[i] = int.class;
			args[i] = codes[i];
		}

		Method m = clazz.getDeclaredMethod(method, types);
		m.setAccessible(true);

		InvocationTargetException e = assertThrows(InvocationTargetException.class, () -> m.invoke(target, args));
		assertTrue(e.getCause() instanceof HwlocException, method + " threw " + e.getCause());
	}
}
