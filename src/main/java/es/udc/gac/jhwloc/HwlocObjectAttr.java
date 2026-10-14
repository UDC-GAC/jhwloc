package es.udc.gac.jhwloc;

import es.udc.gac.jhwloc.HwlocEnumTypes.HwlocObjectType;

public abstract class HwlocObjectAttr {

	private HwlocObjectType type;

	protected HwlocObjectAttr(int type) {
		this.type = HwlocObjectType.GetType(type);
	}

	public HwlocObjectType getType() {
		return this.type;
	}
}
