package com.system.es.enu;

import com.system.es.common.EsCommon;

public enum TableEnum {
	student(EsCommon.TABLE_STUDENT);

	private String tableName;

	private TableEnum(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public static TableEnum getTableEnumByName(String name) {
		TableEnum[] tableEnums = TableEnum.values();
		for (TableEnum tableEnum : tableEnums) {
			if (tableEnum.tableName.equalsIgnoreCase(name)) {
				return tableEnum;
			}
		}

		return null;
	}

	public static void main(String[] args) {
		System.out.println(TableEnum.getTableEnumByName(EsCommon.TABLE_STUDENT));
	}

}
