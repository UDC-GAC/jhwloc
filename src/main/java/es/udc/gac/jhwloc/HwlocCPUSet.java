package es.udc.gac.jhwloc;

public class HwlocCPUSet extends HwlocBitmap {

	HwlocCPUSet() {
		super();
	}

	HwlocCPUSet(long handle) {
		super(handle);
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
	public static HwlocCPUSet alloc() {
		return (HwlocCPUSet) HwlocBitmap.alloc(true);
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
	public static HwlocCPUSet alloc_full() {
		return (HwlocCPUSet) HwlocBitmap.alloc_full(true);
	}
}
