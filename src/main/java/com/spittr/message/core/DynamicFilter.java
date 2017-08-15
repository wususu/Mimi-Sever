package com.spittr.message.core;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.github.shihyuho.jackson.databind.DynamicFilterMixIn;
import com.github.shihyuho.jackson.databind.DynamicFilterProvider;


public class DynamicFilter {

	private static DynamicFilter instance;

	static{
		instance = new DynamicFilter();
	}
	
	public static DynamicFilter getInstance(){
		instance.filterFieldSet = new HashSet<String>();
		return instance;
	}
	
	private Set<String> filterFieldSet;
	
	public DynamicFilter setFilterFieldSet(Set<String> fields){ 
		this.filterFieldSet = fields;
		return this;
	}
	
	public DynamicFilter addFilteFields(String field){
		this.filterFieldSet.add(field);
		return this;
	}
	
	public Object filter(Object object) throws JsonParseException, JsonMappingException, IOException {
		if (filterFieldSet.size() == 0) 
			return object;
				
		ObjectMapper mapper = new ObjectMapper();
		mapper.addMixIn(Object.class, DynamicFilterMixIn.class);
		mapper.setFilterProvider(new DynamicFilterProvider());
		
		PropertyFilter someFilter = SimpleBeanPropertyFilter.serializeAllExcept(filterFieldSet);
		
		String jsonWithoutSomeField = mapper
			.writer(new DynamicFilterProvider(someFilter)) // determine custom filter 
		    .writeValueAsString(object);
			Object fakeObject =  mapper.readValue(jsonWithoutSomeField, object.getClass());

		return fakeObject;
	}
}
