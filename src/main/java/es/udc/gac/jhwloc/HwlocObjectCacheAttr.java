package es.udc.gac.jhwloc;

import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectCacheType;

public class HwlocObjectCacheAttr extends HwlocObjectAttr {

	private long size;
	private int depth;
	private int linesize;
	private int associativity;
	private HwlocObjectCacheType cacheType;

	HwlocObjectCacheAttr(int objectType, int cacheType, long size, int depth, int linesize, int associativity) {
		super(objectType);
		this.cacheType = HwlocObjectCacheType.GetType(cacheType);
		this.size = size;
		this.depth = depth;
		this.linesize = linesize;
		this.associativity = associativity;
	}

	public long getSize() {
		return size;
	}

	public int getDepth() {
		return depth;
	}

	public int getLinesize() {
		return linesize;
	}

	public int getAssociativity() {
		return associativity;
	}

	public HwlocObjectCacheType getCacheType() {
		return cacheType;
	}
}
