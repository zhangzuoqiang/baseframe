package cn.hs.core.cache;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

public class Test {

	public static void main(String[] args) {
		int start = 51;
		int end = 70;
		Map<Integer, String> testMap = new LinkedHashMap<Integer, String>();
		for (int i = 1; i <= 100; i++) {
			testMap.put(i, String.valueOf(i) + "_value");
		}
		Set<Entry<Integer, String>> testSet = testMap.entrySet();
		for (Iterator<Map.Entry<Integer, String>> iter = testSet.iterator(); iter
				.hasNext();) {
			Map.Entry<Integer, String> m = iter.next();
			if (m.getKey() <= end && m.getKey() >= start) {
				System.out.println(m.getValue());
			}
		}
	}

}
