package crawler;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UrlCache {
//	private final static List<Set<String>> cachePool = new ArrayList<Set<String>>();
	private final static Set<String> cachePool = Collections.synchronizedSet(new HashSet<String>());
//	static {
//		for (int i = 0; i < 26; i++)
//			cachePool.add(Collections.synchronizedSet(new HashSet<String>()));
//	}

	public static boolean contains(String url) {
		return cachePool.contains(url);
	}
	
	public static boolean add(String url) {
		return cachePool.add(url);
	}
	
	public static List<Object> getUrlList() {
		return Arrays.asList(cachePool.toArray());
	}
}
