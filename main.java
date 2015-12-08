package com.twitter;

public class TwitterMain {
	
	public static void main(String[] args) {
		
		final TwitterStream stream = new TwitterStream();
		stream.start();
	}

}
