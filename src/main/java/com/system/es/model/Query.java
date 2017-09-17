package com.system.es.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Query {
	@JSONField(name = "multi_match")
	private MutiMatch multiMatch;

	private Bool bool;

	public Bool getBool() {
		return bool;
	}

	public void setBool(Bool bool) {
		this.bool = bool;
	}

	public MutiMatch getMultiMatch() {
		return multiMatch;
	}

	public void setMultiMatch(MutiMatch multiMatch) {
		this.multiMatch = multiMatch;
	}

}
