package es.udc.gac.jhwloc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class BitmapTest {

	@Test
	void setsAndQueriesBits() {
		HwlocBitmap bitmap = HwlocBitmap.alloc();
		assertNotNull(bitmap);

		try {
			assertTrue(bitmap.iszero());

			bitmap.set(0);
			bitmap.set(2);
			bitmap.set(5);

			assertTrue(bitmap.isset(2));
			assertFalse(bitmap.isset(1));
			assertEquals(3, bitmap.weight());
			assertEquals(0, bitmap.first());
			assertEquals(5, bitmap.last());
			assertEquals(2, bitmap.next(0));
			assertEquals("0,2,5", bitmap.toStringList());

			bitmap.clr(2);
			assertFalse(bitmap.isset(2));
			assertEquals(2, bitmap.weight());

			bitmap.zero();
			assertTrue(bitmap.iszero());
		} finally {
			bitmap.free();
		}
	}

	@Test
	void combinesBitmaps() {
		HwlocBitmap a = HwlocBitmap.alloc();
		HwlocBitmap b = HwlocBitmap.alloc();

		try {
			a.set_range(0, 3);
			b.set_range(2, 5);

			assertTrue(a.intersects(b));
			assertEquals("0-5", copyOf(a).or(b).toStringList());
			assertEquals("2-3", copyOf(a).and(b).toStringList());
			assertEquals("0-1", copyOf(a).andnot(b).toStringList());
		} finally {
			a.free();
			b.free();
		}
	}

	@Test
	void freeCanBeCalledMoreThanOnce() {
		HwlocBitmap bitmap = HwlocBitmap.alloc();

		bitmap.free();
		bitmap.free();
	}

	// The operations modify the bitmap that they are called on
	private static HwlocBitmap copyOf(HwlocBitmap bitmap) {
		return (HwlocBitmap) bitmap.clone();
	}
}
