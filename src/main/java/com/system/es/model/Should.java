package com.system.es.model;

public class Should extends BaseQueryCondition {
	private NestedBool bool;

	public NestedBool getBool() {
		return bool;
	}

	public void setBool(NestedBool bool) {
		this.bool = bool;
	}

}
