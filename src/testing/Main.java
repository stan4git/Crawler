package testing;

import java.io.IOException;
import java.util.regex.Pattern;

import util.FileHandler;

public class Main {

	public static void main(String[] args) {
		boolean ret = Pattern.matches("^http(s)?://[^/?]*", "http://www.baidu.com");
		System.out.println(ret);
	}
}
