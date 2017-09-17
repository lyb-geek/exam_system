package com.system.es.model;

import java.util.Map;

import com.alibaba.fastjson.annotation.JSONField;

public abstract class BaseQueryCondition {
	private Map<String, Object> match;
	private Map<String, Object> term;
	@JSONField(name = "multi_match")
	private MutiMatch multiMatch;
	private Map<String, Object> range;
	private Nested nested;

	public Map<String, Object> getMatch() {
		return match;
	}

	public void setMatch(Map<String, Object> match) {
		this.match = match;
	}

	public Map<String, Object> getTerm() {
		return term;
	}

	public void setTerm(Map<String, Object> term) {
		this.term = term;
	}

	public MutiMatch getMultiMatch() {
		return multiMatch;
	}

	public void setMultiMatch(MutiMatch multiMatch) {
		this.multiMatch = multiMatch;
	}

	public Map<String, Object> getRange() {
		return range;
	}

	public void setRange(Map<String, Object> range) {
		this.range = range;
	}

	public Nested getNested() {
		return nested;
	}

	public void setNested(Nested nested) {
		this.nested = nested;
	}

}
