package com.example.demo.model;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Data {
	private Data() {}
	
	private static final Map<Long, String> dataStore = new ConcurrentHashMap<>();
	
	public static Map<Long, String> getDataStore(){
		return dataStore;
	}
}
