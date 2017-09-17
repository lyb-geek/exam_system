package com.system.es.model;

import com.alibaba.fastjson.annotation.JSONField;

public class Delete {
	@JSONField(name = "_index")
	private String index;
	@JSONField(name = "_type")
	private String type;
	@JSONField(name = "_id")
	private String id;

	public String getIndex() {
		return index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
