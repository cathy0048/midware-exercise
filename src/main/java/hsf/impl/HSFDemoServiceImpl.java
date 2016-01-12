package hsf.impl;

import hsf.HSFDemoService;

public class HSFDemoServiceImpl implements HSFDemoService {

	@Override
	public String sayHello(String name) {
		return "Hello, " + name + "!";
	}
	
	public static void main(String args[]) {
		char c = 0x202e;
		System.out.println(c);
	}
}
