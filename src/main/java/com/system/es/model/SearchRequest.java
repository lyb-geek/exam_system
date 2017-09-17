package com.system.es.model;

import java.util.Map;

public class SearchRequest {

	private Query query;
	private Integer from = 0;
	private Integer size = 10;

	private Map<String, Map<String, Map<String, Object>>> aggs;

	public Map<String, Map<String, Map<String, Object>>> getAggs() {
		return aggs;
	}

	public void setAggs(Map<String, Map<String, Map<String, Object>>> aggs) {
		this.aggs = aggs;
	}

	public Integer getFrom() {
		return from;
	}

	public void setFrom(Integer from) {
		this.from = from;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public Query getQuery() {
		return query;
	}

	public void setQuery(Query query) {
		this.query = query;
	}

}
