package com.cobble.takeaway.util;

import java.util.Collection;
import java.util.Iterator;

public class CollectionUtilx {

	private static final int INITIAL_HASH = 7;
	private static final int MULTIPLIER = 31;

	private static final String EMPTY_STRING = "";
	private static final String NULL_STRING = "null";
	private static final String ARRAY_START = "{";
	private static final String ARRAY_END = "}";
	private static final String EMPTY_ARRAY = ARRAY_START + ARRAY_END;
	private static final String ARRAY_ELEMENT_SEPARATOR = ", ";

	public static String nullSafeToString(Collection<?> collection) {
		if (collection == null) {
			return NULL_STRING;
		}
		int length = collection.size();
		if (length == 0) {
			return EMPTY_STRING;
		}
		StringBuilder sb = new StringBuilder();
		Iterator<?> it = collection.iterator();
		sb.append(it.next());
		while (it.hasNext()) {
			sb.append(ARRAY_ELEMENT_SEPARATOR);
			sb.append(it.next());
		}
		return sb.toString();
	} 
}
