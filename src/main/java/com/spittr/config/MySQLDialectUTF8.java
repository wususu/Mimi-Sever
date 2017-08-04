package com.spittr.config;

import org.hibernate.dialect.MySQL5InnoDBDialect;

public class MySQLDialectUTF8 extends MySQL5InnoDBDialect{

	@Override
	public String getTableTypeString(){
		return "ENGINE=InnoDb DEFAULT CHARSET=utf8";
	}
}
