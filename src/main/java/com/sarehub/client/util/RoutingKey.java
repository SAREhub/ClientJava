package com.sarehub.client.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.StringJoiner;

/**
 * 
 */
public class RoutingKey implements Iterable<String> {
	
	private List<String> parts;
	
	public RoutingKey() {
		 parts = new ArrayList<String>();
	}
	
	/**
	 * @param parts
	 */
	public RoutingKey(List<String> parts) {
		 this.parts = parts;
	}
	
	public RoutingKey(String[] parts) {
		this(new ArrayList<String>(Arrays.asList(parts)));
	}
	
	/**
	 * @param part
	 */
	public RoutingKey addPart(String part) {
		parts.add(part);
		return this;
	}
	
	/**
	 * @param index
	 */
	public String getPart(int index) {
		return parts.get(index);
	}
	
	public String[] getParts() {
		return parts.toArray(new String[parts.size()]);
	}
	
	public boolean isEmpty() {
		return parts.isEmpty();
	}
		
	@Override
	public Iterator<String> iterator() {
		return parts.iterator();
	}

	
	@Override
	public String toString() {
		StringJoiner joiner = new StringJoiner(".");
		for (String part : parts) {
			joiner.add(part);
		}
		
		return joiner.toString();
	}

	
	
	
}
