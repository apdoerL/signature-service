package org.apdoer.signature.service.enums;

public enum VerbEnum {

	/**
	 * qingqiu leixing
	 */
	GET, POST, DELETE, PUT;
	
	/**
	 * 构造SystemTypeENUM对象
	 * @param type
	 * @return SystemTypeENUM
	 */
	public static VerbEnum formValue(final String type) {
		for (VerbEnum tp : VerbEnum.values()) {
			if (tp.name().equalsIgnoreCase(type)) {
				return tp;
			}
		}
		return null;
	}
	
	public static boolean isValid(final String type) {
		for (VerbEnum tp : VerbEnum.values()) {
			if (tp.name().equalsIgnoreCase(type)) {
				return true;
			}
		}
		return false;
	}
}
