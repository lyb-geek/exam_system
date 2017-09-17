package com.system.es.model;

import com.alibaba.fastjson.annotation.JSONField;

public class BulkDocument {

	private Index index;

	private Delete delete;

	@JSONField(serialize = false)
	private Object content;

	public Index getIndex() {
		return index;
	}

	public void setIndex(Index index) {
		this.index = index;
	}

	public Object getContent() {
		return content;
	}

	public void setContent(Object content) {
		this.content = content;
	}

	public Delete getDelete() {
		return delete;
	}

	public void setDelete(Delete delete) {
		this.delete = delete;
	}

}
