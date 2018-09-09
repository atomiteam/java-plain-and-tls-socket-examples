package com.atomiteam.socket.tls;

import com.atomiteam.socket.mutual.ApplicationServer;

public class ApplicationClient {

	public static void main(String[] args) throws Exception {

		String trustStore = ApplicationServer.class.getClassLoader()
				.getResource("com/atomiteam/socket/tls/client.trust").getFile();

		System.setProperty("javax.net.ssl.trustStore", trustStore);
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");

		System.setProperty("javax.net.debug", "all");

		GreetClient client1 = new GreetClient();
		client1.startConnection("localhost", 6666);
		client1.read();

	}

}
