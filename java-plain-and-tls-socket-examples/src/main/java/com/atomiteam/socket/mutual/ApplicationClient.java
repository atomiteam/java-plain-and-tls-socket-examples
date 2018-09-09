package com.atomiteam.socket.mutual;

public class ApplicationClient {

	public static void main(String[] args) throws Exception {

		String keyStore = ApplicationServer.class.getClassLoader()
				.getResource("com/atomiteam/socket/mutual/client.pkcs12").getFile();
		String trustStore = ApplicationServer.class.getClassLoader()
				.getResource("com/atomiteam/socket/mutual/client.trust").getFile();

		System.setProperty("javax.net.ssl.trustStore", trustStore);
		System.setProperty("javax.net.ssl.trustStorePassword", "password");
		System.setProperty("javax.net.ssl.trustStoreType", "JKS");

		System.setProperty("javax.net.ssl.keyStore", keyStore);
		System.setProperty("javax.net.ssl.keyStorePassword", "password");

		System.setProperty("javax.net.debug", "all");

		GreetClient client1 = new GreetClient();
		client1.startConnection("localhost", 6666);
		client1.read();

	}

}
