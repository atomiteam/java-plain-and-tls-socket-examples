package com.atomiteam.socket.mutual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLSocketFactory;

public class GreetClient {
	private Socket clientSocket;
	private PrintWriter out;
	private BufferedReader in;

	public void read() {
		BufferedReader sysin = new BufferedReader(new InputStreamReader(System.in));

		String inputLine = null;
		while (true) {
			try {
				System.out.println("Enter a new line");
				inputLine = sysin.readLine();
				System.out.println("Line is " + inputLine);
			} catch (IOException e) {

			}
			if (".".equals(inputLine)) {
				out.println("bye");
				break;
			}
			String reply = this.sendMessage(inputLine);
			System.out.println("Reply from server: " + reply);
		}

	}

	public void startConnection(String ip, int port) {
		try {
			SSLSocketFactory f = (SSLSocketFactory) SSLSocketFactory.getDefault();

			clientSocket = f.createSocket(ip, port);
			
			out = new PrintWriter(clientSocket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String sendMessage(String msg) {
		try {
			out.println(msg);
			String resp = in.readLine();
			return resp;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stopConnection() {
		try {
			in.close();
			out.close();
			clientSocket.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}