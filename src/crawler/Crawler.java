package crawler;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import org.apache.http.HttpException;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

import util.FileHandler;
import util.HttpHelper;

public class Crawler implements Runnable {
	private final HttpClient client = HttpClients.createDefault();
	private final Queue<String> urlQueue = new LinkedList<String>();
	private boolean isRunning = true;
	@SuppressWarnings("unused")
	private String startUrl;
	
	@SuppressWarnings("unused")
	private Crawler(){}
	public Crawler(String startUrl) {
		//Initialize cache and start point
		//1.Push init url into stack
		this.startUrl = startUrl;
		UrlCache.add(startUrl);
		urlQueue.offer(startUrl);
	}
	
	
	@Override
	public void run() {
		String currentUrl = null;
		String pageContent = null;
		while (isRunning) {
			while (urlQueue.isEmpty()) {
				try {
					this.wait(5000);
				} catch (InterruptedException e) {
					System.out.println("Pending work, time to wake up.");
				}
			}
			currentUrl = urlQueue.poll();
			try {
				pageContent = HttpHelper.downloadHtml(currentUrl, client);
			} catch (HttpException e) {
				e.printStackTrace();
				System.err.println("Invalid URL.");
				continue;
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Connection is lost.");
				continue;
			}
			
			//2.Extract url list from this page
			Set<String> urlList = HttpHelper.extractLinks(pageContent);
			for (String url : urlList) {
				if (!UrlCache.contains(url)) {
					UrlCache.add(url);
					urlQueue.offer(url);
				}
			}
			
			//3.Store this page
			try {
				FileHandler.storeHTML(pageContent);
			} catch (IOException e) {
				e.printStackTrace();
				System.err.println("Exception occurs when writing file.");
				continue;
			}
		}
	}
	
	
	
}
