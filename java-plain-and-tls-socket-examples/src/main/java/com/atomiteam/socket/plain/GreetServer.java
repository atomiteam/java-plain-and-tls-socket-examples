package com.atomiteam.socket.plain;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class GreetServer {
	private ServerSocket serverSocket;

	public void start(int port) {
		try {
			serverSocket = new ServerSocket(port);
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
					System.out.println("Pending for line");
					inputLine = in.readLine();
					System.out.println("Read " + inputLine);
					if (".".equals(inputLine)) {
						out.println("bye");
						break;
					}
					out.println("Echo >  " + inputLine);
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