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
 * Netty����JBOSS�ṩ��һ��java��Դ��ܡ�Netty�ṩ�첽�ġ��¼�����������Ӧ�ó����ܺ͹��ߣ����Կ��ٿ��������ܡ�
 * �߿ɿ��Ե�����������Ϳͻ��˳���Ҳ����˵��Netty ��һ������NIO�Ŀͻ����������˱�̿�ܣ�ʹ��Netty
 * ����ȷ������ٺͼ򵥵Ŀ�����һ������Ӧ�ã�����ʵ����ĳ��Э��Ŀͻ��������Ӧ�á�
 * 
 * @author zhouchangwei
 *
 */
public class NettyServerExample {
	 private static final int port = 8080;
		public void start() throws InterruptedException {
			ServerBootstrap b = new ServerBootstrap();// ������������
			EventLoopGroup group = new NioEventLoopGroup();// ͨ��nio��ʽ���������Ӻʹ�������
			try {
				b.group(group);
				b.channel(NioServerSocketChannel.class);// ����nio���͵�channel
				b.localAddress(new InetSocketAddress(port));// ���ü����˿�
				b.childHandler(new ChannelInitializer<SocketChannel>() {//�����ӵ���ʱ�ᴴ��һ��channel
							protected void initChannel(SocketChannel ch) throws Exception {
								// pipeline����channel�е�Handler����channel�����м���һ��handler������ҵ��
								ch.pipeline().addLast("myHandler", new EchoServerHandler());
							}
						});
				ChannelFuture f = b.bind().sync();// ������ϣ��_ʼ��server��ͨ������syncͬ����������ֱ���󶨳ɹ�
				System.out.println(NettyServerExample.class.getName() + " started and listen on " + f.channel().localAddress());
				f.channel().closeFuture().sync();// Ӧ�ó����һֱ�ȴ���ֱ��channel�ر�
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				group.shutdownGracefully().sync();//�ر�EventLoopGroup���ͷŵ�ȫ����Դ�����������߳�
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
