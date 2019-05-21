/*
 * Copyright (C) 2019 Universidade da Coruña
 * 
 * This file is part of jhwloc.
 * 
 * jhwloc is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * jhwloc is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with jhwloc. If not, see <http://www.gnu.org/licenses/>.
 */
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
