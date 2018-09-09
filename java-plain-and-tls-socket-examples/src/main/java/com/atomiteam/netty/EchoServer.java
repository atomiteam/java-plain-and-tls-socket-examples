package com.atomiteam.netty;

import java.net.InetSocketAddress;
import java.util.LinkedList;
import java.util.List;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.CharsetUtil;

public class EchoServer {

	private final int port;
	private List<SocketChannel> clients = new LinkedList<SocketChannel>();

	public EchoServer(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		final int port = 5222;
		new EchoServer(5222).start();
	}

	public void start() throws Exception {
		EventLoopGroup group = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap();
			b.group(group).channel(NioServerSocketChannel.class).localAddress(new InetSocketAddress(port))
					.childHandler(new ChannelInitializer<SocketChannel>() {
						@Override
						protected void initChannel(SocketChannel socketChannel) throws Exception {
							socketChannel.pipeline().addLast(new EchoServerHandler());
							System.out.println("Server channel initted");
							clients.add(socketChannel);
						}
					});

			System.out.println("Before sending msg to client");

			Thread thread = new Thread() {
				public void run() {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
					}
					clients.forEach(x -> {
						x.writeAndFlush(Unpooled.copiedBuffer("Late msg \n", CharsetUtil.UTF_8));
					});
				}
			};

			thread.start();

			ChannelFuture f = b.bind().sync();
			f.channel().closeFuture().sync();

		} finally {
			group.shutdownGracefully().sync();
		}
	}
}
