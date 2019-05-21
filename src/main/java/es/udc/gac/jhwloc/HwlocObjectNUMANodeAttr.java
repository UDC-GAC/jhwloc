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

public class HwlocObjectNUMANodeAttr extends HwlocObjectAttr {

	private long local_memory;

	HwlocObjectNUMANodeAttr(int objectType, long local_memory) {
		super(objectType);
		this.local_memory = local_memory;
	}

	public long getLocalMemory() {
		return local_memory;
	}
}
