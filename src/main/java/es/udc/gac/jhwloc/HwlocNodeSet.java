package es.udc.gac.jhwloc;

public class HwlocNodeSet extends HwlocBitmap {

	HwlocNodeSet() {
		super();
	}

	HwlocNodeSet(long handler) {
		super(handler);
	}

	/**
	 * Allocate a new empty bitmap.
	 * <p>
	 * The bitmap should be freed by a corresponding call to {@code free()}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_alloc()}.
	 *
	 * @return A valid bitmap or {@code null}.
	 */
	public static HwlocNodeSet alloc() {
		return (HwlocNodeSet) HwlocBitmap.alloc(false);

	}

	/**
	 * Allocate a new full bitmap.
	 * <p>
	 * The bitmap should be freed by a corresponding call to {@code free()}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_alloc_full()}.
	 *
	 * @return A valid bitmap or {@code null}.
	 */
	public static HwlocNodeSet alloc_full() {
		return (HwlocNodeSet) HwlocBitmap.alloc_full(false);
	}
}
