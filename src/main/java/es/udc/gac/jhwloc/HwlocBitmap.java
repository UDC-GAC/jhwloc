package es.udc.gac.jhwloc;

public class HwlocBitmap implements Cloneable {

	private long handler;

	protected HwlocBitmap() {
		this.handler = -1;
	}

	HwlocBitmap(long handler) {
		this.handler = handler;
	}

	private void setHandler(long handler) {
		this.handler = handler;
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
	public static HwlocBitmap alloc() {
		long rc = jhwloc_bitmap_alloc();

		if(rc == -1)
			return null;

		return new HwlocBitmap(rc);
	}

	protected static HwlocBitmap alloc(boolean cpuset) {
		long rc = jhwloc_bitmap_alloc();

		if(rc == -1)
			return null;

		if (cpuset)
			return new HwlocCPUSet(rc);
		else
			return new HwlocNodeSet(rc);
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
	public static HwlocBitmap alloc_full() {
		long rc = jhwloc_bitmap_alloc_full();

		if(rc == -1)
			return null;

		return new HwlocBitmap(rc);
	}

	protected static HwlocBitmap alloc_full(boolean cpuset) {
		long rc = jhwloc_bitmap_alloc_full();

		if(rc == -1)
			return null;

		if (cpuset)
			return new HwlocCPUSet(rc);
		else
			return new HwlocNodeSet(rc);
	}

	/**
	 * Copy the contents of bitmap {@code src} into the already allocated bitmap {@code dst}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_copy()}.
	 * 
	 * @param dst Destination bitmap.
	 * @param src Source bitmap.
	 * @return 0 on success, -1 on error.
	 */
	public static int copy(HwlocBitmap dst, HwlocBitmap src) {
		return jhwloc_bitmap_copy(dst, src);
	}

	/**
	 * Add index {@code id} in this bitmap.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_set()}.
	 * 
	 * @param id Index
	 * @return 0 on success, -1 on error.
	 */
	public int set(int id) {
		return jhwloc_bitmap_set(id);
	}

	/**
	 * Add indexes from {@code begin} to {@code end} in this bitmap.
	 * <p>
	 * If {@code end} is -1, the range is infinite. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_set_range()}.
	 * 
	 * @param begin Start index
	 * @param end End index
	 * @return 0 on success, -1 on error.
	 */
	public int set_range(int begin, int end) {
		return jhwloc_bitmap_set_range(begin, end);
	}

	/**
	 * Remove index {@code id} from this bitmap.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_clr()}.
	 * 
	 * @param id Index
	 * @return 0 on success, -1 on error.
	 */
	public int clr(int id) {
		return jhwloc_bitmap_clr(id);
	}

	/**
	 * Remove indexes from {@code begin} to {@code end} in this bitmap.
	 * <p>
	 * If {@code end} is -1, the range is infinite. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_clr_range()}.
	 * 
	 * @param begin Start index
	 * @param end End index
	 * @return 0 on success, -1 on error.
	 */
	public int clr_range(int begin, int end) {
		return jhwloc_bitmap_clr_range(begin, end);
	}

	/**
	 * Empty this bitmap bitmap and add bit {@code id}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_only()}.
	 * 
	 * @param id Index
	 * @return 0 on success, -1 on error.
	 */
	public int only(int id) {
		return jhwloc_bitmap_only(id);
	}

	/**
	 * Fill this bitmap and clear the index {@code id}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_allbut()}.
	 * 
	 * @param id Index
	 * @return 0 on success, -1 on error.
	 */
	public int allbut(int id) {
		return jhwloc_bitmap_allbut(id);
	}

	/**
	 * Test whether bitmap {@code sub_bitmap} is part of bitmap {@code super_bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_isincluded()}.
	 * <p>
	 * Note
	 * <ul>
	 * <li>The empty bitmap is considered included in any other bitmap.
	 * </ul>
	 * 
	 * @param sub_bitmap Sub bitmap.
	 * @param super_bitmap Super bitmap.
	 * @return <code>true</code> if {@code sub_bitmap} is part of {@code super_bitmap},
	 * <code>false</code> instead.
	 */
	public static boolean isincluded(HwlocBitmap sub_bitmap, HwlocBitmap super_bitmap) {
		int rc = jhwloc_bitmap_isincluded(sub_bitmap, super_bitmap);

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Free the C memory allocated by this bitmap.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_free()}.
	 * <p>
	 * It can be called more than once, the memory is only freed the first time. Only
	 * bitmaps that were allocated by the user have to be freed: the ones that belong to
	 * the topology (e.g. {@code HwlocObject.getCPUSet()}) must not be.
	 */
	public void free() {
		if(handler != -1 && handler != 0) {
			jhwloc_bitmap_free();
			handler = -1;
		}
	}

	/**
	 * Duplicate this bitmap by allocating a new bitmap and copying bitmap contents.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_dup()}.
	 *
	 * @return A copy of this bitmap object or {@code null}.
	 */
	@Override
	public Object clone() {
		Object obj = null;

		long handler = jhwloc_bitmap_dup();

		if (handler == -1)
			return null;

		try {
			obj = super.clone();
		} catch(CloneNotSupportedException ex) {
			System.out.println("This should never happen! (CloneNotSupportedException)");
		}

		((HwlocBitmap) obj).setHandler(handler);

		return obj;
	}

	/**
	 * Stringify this bitmap into a newly allocated string.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_asprintf()}.
	 *
	 * @return The string representation.
	 */
	@Override
	public String toString() {
		return jhwloc_bitmap_asprintf();
	}

	/**
	 * Stringify a bitmap into a newly allocated list string.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_list_asprintf()}.
	 *
	 * @return The list string representation.
	 */
	public String toStringList() {
		return jhwloc_bitmap_list_asprintf();
	}
	
	/**
	 * Stringify a bitmap into a newly allocated taskset-specific string.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_taskset_asprintf()}.
	 *
	 * @return The taskset-specific string representation.
	 */
	public String toStringTasket() {
		return jhwloc_bitmap_taskset_asprintf();
	}

	/**
	 * {@code OR} this bitmap object and {@code bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_or()}.
	 *
	 * @param bitmap Bitmap with which to perform the {@code OR} operation.
	 * @return The bitmap result of the {@code OR} operation.
	 */
	public HwlocBitmap or(HwlocBitmap bitmap) {
		long handler = jhwloc_bitmap_or(bitmap);
		return new HwlocBitmap(handler);
	}

	/**
	 * {@code AND} this bitmap object and {@code bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_and()}.
	 *
	 * @param bitmap Bitmap with which to perform the {@code AND} operation.
	 * @return The bitmap result of the {@code AND} operation.
	 */
	public HwlocBitmap and(HwlocBitmap bitmap) {
		long handler = jhwloc_bitmap_and(bitmap);
		return new HwlocBitmap(handler);
	}

	/**
	 * {@code AND} this bitmap object and the NEGATION of {@code bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_andnot()}.
	 *
	 * @param bitmap Bitmap with which to perform the {@code ANDNOT} operation.
	 * @return The bitmap result of the {@code ANDNOT} operation.
	 */
	public HwlocBitmap andnot(HwlocBitmap bitmap) {
		long handler = jhwloc_bitmap_andnot(bitmap);
		return new HwlocBitmap(handler);
	}

	/**
	 * {@code XOR} this bitmap object and {@code bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_xor()}.
	 *
	 * @param bitmap Bitmap with which to perform the {@code XOR} operation.
	 * @return The bitmap result of the {@code XOR} operation.
	 */
	public HwlocBitmap xor(HwlocBitmap bitmap) {
		long handler = jhwloc_bitmap_xor(bitmap);
		return new HwlocBitmap(handler);
	}

	/**
	 * {@code NEGATE} this bitmap object.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_not()}.
	 *
	 * @return The bitmap result of the {@code NOT} operation.
	 */
	public HwlocBitmap not() {
		long handler = jhwloc_bitmap_not();
		return new HwlocBitmap(handler);
	}

	/**
	 * Keep a single index among those set in this bitmap.
	 * <p>
	 * May be useful before binding so that the process does not have a 
	 * chance of migrating between multiple logical CPUs in the original mask.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_singlify()}.
	 * 
	 * @return 0 on success, -1 on error.
	 */
	public int singlify() {
		return jhwloc_bitmap_singlify();
	}

	/**
	 * Test whether this bitmap object and {@code bitmap} intersects.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_intersects()}.
	 *
	 * @param bitmap Bitmap with which to test intersection.
	 * @return <code>true</code> if this bitmap intersect with {@code bitmap},
	 * <code>false</code> instead.
	 */
	public boolean intersects(HwlocBitmap bitmap) {

		int rc = jhwloc_bitmap_intersects(bitmap);

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Test whether this bitmap object is equal to {@code bitmap}.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_isequal()}.
	 *
	 * @param bitmap Bitmap with which to test equality.
	 * @return <code>true</code> if this bitmap is equals to {@code bitmap},
	 * <code>false</code> instead.
	 */
	@Override
	public boolean equals(Object bitmap) {

		if(bitmap == null)
			return false;

		if(!(bitmap instanceof HwlocBitmap))
			return false;

		if (bitmap == this)
			return true;

		int rc = jhwloc_bitmap_isequal((HwlocBitmap)bitmap);

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Compare this bitmap object and {@code bitmap} in lexicographic order.
	 * <p>
	 * Lexicographic comparison of bitmaps, starting for their highest indexes.  Compare
	 * last indexes first, then second, etc. The empty bitmap is considered lower than
	 * anything.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_compare()}.
	 * 
	 * @param bitmap Bitmap with which to perform the comparison.
	 * @return Result of the comparison.
	 */
	public int compare(HwlocBitmap bitmap) {
		return jhwloc_bitmap_compare(bitmap);
	}

	/**
	 * Compare this bitmap object and {@code bitmap} using their lowest index.
	 * <p>
	 * Smaller least significant bit is smaller. The empty bitmap is considered higher than anything.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_compare_first()}.
	 * 
	 * @param bitmap Bitmap with which to perform the comparison.
	 * @return Result of the comparison.
	 */
	public int compare_first(HwlocBitmap bitmap) {
		return jhwloc_bitmap_compare_first(bitmap);
	}

	/**
	 * Empty this bitmap.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_zero()}.
	 */
	public void zero() {
		jhwloc_bitmap_zero();
	}

	/**
	 * Fill this bitmap with all possible indexes (even if those objects don’t exist or are otherwise unavailable).
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_fill()}.
	 */
	public void fill() {
		jhwloc_bitmap_fill();
	}

	/**
	 * Test whether index {@code id} is part of this bitmap.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_isset()}.
	 * 
	 * @param id Index
	 * @return <code>true</code> if {@code id} is part of this bitmap,
	 * <code>false</code> instead.
	 */
	public boolean isset(int id) {
		int rc = jhwloc_bitmap_isset(id);

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Test whether this bitmap is empty.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_iszero()}.
	 *
	 * @return <code>true</code> if this bitmap is empty, <code>false</code> instead.
	 */
	public boolean iszero() {
		int rc = jhwloc_bitmap_iszero();

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Test whether this bitmap is completely full.
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_isfull()}.
	 *
	 * @return <code>true</code> if this bitmap is completely full, <code>false</code> instead.
	 */
	public boolean isfull() {
		int rc = jhwloc_bitmap_isfull();

		if(rc == 1)
			return true;

		return false;
	}

	/**
	 * Compute the first index (least significant bit) in this bitmap. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_first()}.
	 * 
	 * @return -1 if no index is set.
	 */
	public int first() {
		return jhwloc_bitmap_first();
	}

	/**
	 * Compute the first unset index (least significant bit) in this bitmap. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_first_unset()}.
	 * 
	 * @return -1 if no index is unset.
	 */
	public int first_unset() {
		return jhwloc_bitmap_first_unset();
	}

	/**
	 * Compute the last index (most significant bit) in this bitmap. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_last()}.
	 * 
	 * @return -1 if no index is set or if this bitmap is infinitely set.
	 */
	public int last() {
		return jhwloc_bitmap_last();
	}

	/**
	 * Compute the last unset index (most significant bit) in this bitmap. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_last_unset()}.
	 * 
	 * @return -1 if no index is unset or if this bitmap is infinitely set.
	 */
	public int last_unset() {
		return jhwloc_bitmap_last_unset();
	}

	/**
	 * Compute the next index in this bitmap which is after index {@code prev}.
	 * <p>
	 * If {@code prev} is -1, the first index is returned. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_next()}.
	 * 
	 * @param prev Previous index
	 * @return -1 no index with higher index is set.
	 */
	public int next(int prev) {
		return jhwloc_bitmap_next(prev);
	}

	/**
	 * Compute the next unset index in this bitmap which is after index {@code prev}.
	 * <p>
	 * If {@code prev} is -1, the first unset index is returned. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_next_unset()}.
	 * 
	 * @param prev Previous index
	 * @return -1 no index with higher index is unset.
	 */
	public int next_unset(int prev) {
		return jhwloc_bitmap_next_unset(prev);
	}

	/**
	 * Compute the "weight" of this bitmap. 
	 * <p>
	 * Java binding of the hwloc operation {@code hwloc_bitmap_weight()}.
	 * 
	 * @return The number of indexes that are in this bitmap or -1 if this bitmap
	 * is infinitely set.
	 */
	public int weight() {
		return jhwloc_bitmap_weight();
	}

	/**
	 * Compute the hash code of this bitmap from its current content, so a bitmap must not
	 * be modified while it is used as a key of a hash table.
	 * 
	 * @return hash code.
	 */
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + this.toString().hashCode();
		return hash;
	}

	/********************** PRIVATE NATIVE METHODS 	**********************/
	private static native long jhwloc_bitmap_alloc();
	private static native long jhwloc_bitmap_alloc_full();
	private native void jhwloc_bitmap_free();
	private native long jhwloc_bitmap_dup();
	private static native int jhwloc_bitmap_copy(HwlocBitmap dst, HwlocBitmap src);
	private native int jhwloc_bitmap_set(int id);
	private native int jhwloc_bitmap_set_range(int begin, int end);
	private native int jhwloc_bitmap_clr(int id);
	private native int jhwloc_bitmap_clr_range(int begin, int end);
	private native int jhwloc_bitmap_only(int id);
	private native int jhwloc_bitmap_allbut(int id);
	private native long jhwloc_bitmap_or(HwlocBitmap bitmap);
	private native long jhwloc_bitmap_and(HwlocBitmap bitmap);
	private native long jhwloc_bitmap_andnot(HwlocBitmap bitmap);
	private native long jhwloc_bitmap_xor(HwlocBitmap bitmap);
	private native long jhwloc_bitmap_not();
	private native int jhwloc_bitmap_singlify();
	private native int jhwloc_bitmap_intersects(HwlocBitmap bitmap);
	private static native int jhwloc_bitmap_isincluded(HwlocBitmap sub_bitmap, HwlocBitmap super_bitmap);
	private native int jhwloc_bitmap_isequal(HwlocBitmap bitmap);
	private native int jhwloc_bitmap_compare(HwlocBitmap bitmap);
	private native int jhwloc_bitmap_compare_first(HwlocBitmap bitmap);
	private native void jhwloc_bitmap_zero();
	private native void jhwloc_bitmap_fill();
	private native int jhwloc_bitmap_isset(int id);
	private native int jhwloc_bitmap_iszero();
	private native int jhwloc_bitmap_isfull();
	private native String jhwloc_bitmap_asprintf();
	private native String jhwloc_bitmap_list_asprintf();
	private native String jhwloc_bitmap_taskset_asprintf();
	private native int jhwloc_bitmap_first();
	private native int jhwloc_bitmap_first_unset();
	private native int jhwloc_bitmap_last();
	private native int jhwloc_bitmap_last_unset();
	private native int jhwloc_bitmap_next(int prev);
	private native int jhwloc_bitmap_next_unset(int prev);
	private native int jhwloc_bitmap_weight();
}
