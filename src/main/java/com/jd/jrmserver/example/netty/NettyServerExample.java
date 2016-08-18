package com.jd.jrmserver.example.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;




/**
 * Netty是由JBOSS提供的一个java开源框架。Netty提供异步的、事件驱动的网络应用程序框架和工具，用以快速开发高性能、
 * 高可靠性的网络服务器和客户端程序。也就是说，Netty 是一个基于NIO的客户，服务器端编程框架，使用Netty
 * 可以确保你快速和简单的开发出一个网络应用，例如实现了某种协议的客户，服务端应用。
 * 
 * @author zhouchangwei
 *
 */
public class NettyServerExample {
	 private static final int port = 8080;
		public void start() throws InterruptedException {
			ServerBootstrap b = new ServerBootstrap();// 引导辅助程序
			EventLoopGroup group = new NioEventLoopGroup();// 通过nio方式来接收连接和处理连接
			try {
				b.group(group);
				b.channel(NioServerSocketChannel.class);// 设置nio类型的channel
				b.localAddress(new InetSocketAddress(port));// 设置监听端口
				b.childHandler(new ChannelInitializer<SocketChannel>() {//有连接到达时会创建一个channel
							protected void initChannel(SocketChannel ch) throws Exception {
								// pipeline管理channel中的Handler，在channel队列中加入一个handler来处理业务
								ch.pipeline().addLast("myHandler", new EchoServerHandler());
							}
						});
				ChannelFuture f = b.bind().sync();// 配置完毕，開始绑定server，通过调用sync同步方法堵塞直到绑定成功
				System.out.println(NettyServerExample.class.getName() + " started and listen on " + f.channel().localAddress());
				f.channel().closeFuture().sync();// 应用程序会一直等待，直到channel关闭
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				group.shutdownGracefully().sync();//关闭EventLoopGroup，释放掉全部资源包含创建的线程
			}
		}
		public static void main(String[] args) {
			try {
				new NettyServerExample().start();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}
