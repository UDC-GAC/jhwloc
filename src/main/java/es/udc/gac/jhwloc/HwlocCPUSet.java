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
	 * The bitmap should be freed by a corresponding call to {@code free()}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_alloc()}.
	 *
	 * @return A valid bitmap or {@code null}.
	 */
	public static HwlocCPUSet alloc() {
		return (HwlocCPUSet) HwlocBitmap.alloc(true);
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
	public static HwlocCPUSet alloc_full() {
		return (HwlocCPUSet) HwlocBitmap.alloc_full(true);
	}
}
