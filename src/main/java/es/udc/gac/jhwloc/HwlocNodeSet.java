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
		long rc = jhwloc_bitmap_alloc();

		if(rc == -1)
			return null;

		return new HwlocNodeSet(rc);
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
		long rc = jhwloc_bitmap_alloc_full();

		if(rc == -1)
			return null;

		return new HwlocNodeSet(rc);
	}
}
