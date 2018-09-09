package com.atomiteam.socket.plain;

/**
 * 
 *  Server Application that talks in non-ssl.
 * 
 * 
 * @author sami altundag
 *
 */
public class ApplicationClient {

	public static void main(String[] args) throws Exception{
		
		GreetClient client1 = new GreetClient();
		client1.startConnection("localhost", 6666);
		client1.read();
		
	}

}
