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
	 * The bitmap should be freed by a corresponding call to <tt>free()</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_bitmap_alloc()</tt>.
	 *
	 * @return A valid bitmap or <tt>null</tt>.
	 */
	public static HwlocNodeSet alloc() {
		return (HwlocNodeSet) HwlocBitmap.alloc(false);

	}

	/**
	 * Allocate a new full bitmap.
	 * <p>
	 * The bitmap should be freed by a corresponding call to <tt>free()</tt>.
	 * <p>
	 * Java binding of the hwloc operation <tt>hwloc_bitmap_alloc_full()</tt>.
	 *
	 * @return A valid bitmap or <tt>null</tt>.
	 */
	public static HwlocNodeSet alloc_full() {
		return (HwlocNodeSet) HwlocBitmap.alloc_full(false);
	}
}
