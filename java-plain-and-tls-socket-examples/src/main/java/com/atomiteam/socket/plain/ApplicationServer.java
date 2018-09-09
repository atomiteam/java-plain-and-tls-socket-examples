package com.atomiteam.socket.plain;

public class ApplicationServer {

	public static void main(String[] args) throws Exception {
		GreetServer server = new GreetServer();
		server.start(6666);

	}

}
