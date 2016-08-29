package open.thl.netty;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelHandler.Sharable;
/**
 * Sharable��ʾ�˶�����channel�乲��
 * handler�������ǵ���ϸҵ����
 * */
@Sharable//ע��@Sharable�ܹ�������channels�乲��
public class EchoServerHandler extends ChannelInboundHandlerAdapter{
	public void channelRead(ChannelHandlerContext ctx, Object msg) { 
		System.out.println("server received data :" + msg); 
		ctx.write(msg);//д�����ݣ�
	} 
	public void channelReadComplete(ChannelHandlerContext ctx) { 
		ctx.writeAndFlush(Unpooled.EMPTY_BUFFER) //flush��ȫ��д�ص�����
		.addListener(ChannelFutureListener.CLOSE); //��flush��Ϻ�ر�channel
	} 
	public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) { 
		cause.printStackTrace();//��׽�쳣��Ϣ
		ctx.close();//�����쳣ʱ�ر�channel 
	} 	
}