package util;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpExtractor {
	private static final String httpRegex = "^http(s)?://.*";
	private static final Pattern urlPattern = Pattern.compile(httpRegex);
	private static final String domainRegex = "^http(s)?://[^/?]*";
	private static final Pattern domainPattern = Pattern.compile(httpRegex);
	
	public static Set<String> extractURLs(String pageContext) {
		Set<String> result = new HashSet<String>();
		Document doc = Jsoup.parse(pageContext);
		Elements aTagList = doc.select("a");
		for (Element e : aTagList) {
			String href = e.attr("href");
			//HTTP://
			if (href.length() < 6)
				continue;
			Matcher matcher = urlPattern.matcher(href);
			if (matcher.matches())
				result.add(href);
			else if (href.charAt(0) != '/')
				result.add("http://" + href);
		}
		return result;
	}
	
	
public static String extractDomainName(String url) {
		Matcher matcher = domainPattern.matcher(url);
		if (!matcher.matches()) {
			return null;
		}
		return null;
	}
}
