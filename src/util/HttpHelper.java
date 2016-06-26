package util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;

import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;

public class HttpHelper {
	private final static String USER_AGENT = "Mozilla/5.0";
	
	
	public static String downloadHtml(String url, HttpClient client)
			throws HttpException, IOException {
		HttpGet request = new HttpGet(url);
		request.addHeader("User-Agent", USER_AGENT);
		HttpResponse response = null;
		response = client.execute(request);
		int responseCode = response.getStatusLine().getStatusCode();
		
		//Should be modified to handle more status code
		if (responseCode >= 200 && responseCode < 300) {
			BufferedReader br = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
			StringBuffer result = new StringBuffer();
			String line = null;
			while ((line = br.readLine()) != null)
				result.append(line);
			return result.toString();
		} else {
			throw new HttpException("Invaild URL");
		}
	}
	
	
	public static Set<String> extractLinks(String pageContext) {
		return HttpExtractor.extractURLs(pageContext);
	}
	
	
}
