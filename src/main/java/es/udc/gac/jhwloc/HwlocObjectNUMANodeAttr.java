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
