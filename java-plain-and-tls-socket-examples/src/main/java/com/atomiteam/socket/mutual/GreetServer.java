package com.atomiteam.socket.mutual;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

/**
 * 
 * TLS Server Socket that forces client authentication
 * 
 * The line below forces server to do client authentication:
 * 
 * <code>serverSocket.setNeedClientAuth(true);</code>
 * 
 * Check for ECDHClientKeyExchange in trace logs
 * 
 * @author sami altundag
 *
 */
public class GreetServer {
	private SSLServerSocket serverSocket;

	public void start(int port) {
		try {

			SSLServerSocketFactory ssf = (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();

			serverSocket = (SSLServerSocket) ssf.createServerSocket(port);
			// Need client auth!
			serverSocket.setNeedClientAuth(true);

			while (true)
				new EchoClientHandler(serverSocket.accept()).start();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void stop() {
		try {
			serverSocket.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static class EchoClientHandler extends Thread {
		private Socket clientSocket;
		private PrintWriter out;
		private BufferedReader in;

		public EchoClientHandler(Socket socket) {
			this.clientSocket = socket;
		}

		public void run() {
			try {
				out = new PrintWriter(clientSocket.getOutputStream(), true);
				in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

				String inputLine;
				while (true) {
					System.out.println("Server is waiting for a line");
					inputLine = in.readLine();
					System.out.println("Line is " + inputLine);
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					}
					out.println("Echoing  " + inputLine);
					System.out.println(inputLine);
				}

				in.close();
				out.close();
				clientSocket.close();
			} catch (IOException e) {
				throw new RuntimeException(e);
			}
		}
	}
}