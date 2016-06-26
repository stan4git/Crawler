package console;

import java.util.LinkedList;

import crawler.Crawler;

public class MainThread {

	public static void main(String[] args) {
		Crawler[] crawler = new Crawler[1];
		for (int i = 0; i < crawler.length; i++) {
			crawler[i] = new Crawler("https://www.google.com/finance");
			crawler[i].run();
		}
		while (true) {}
	}

}
