package open.thl.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufUtil;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.ChannelHandler.Sharable;
import io.netty.util.CharsetUtil;

@Sharable
public class EchoClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
	/**
	 *此方法会在连接到server后被调用 
	 * */
	public void channelActive(ChannelHandlerContext ctx) {
		System.out.println("====contect server:"+ctx);
		ctx.write(Unpooled.copiedBuffer("Netty rocks!", CharsetUtil.UTF_8));
	}
	/**
	 *此方法会在接收到server数据后调用 
	 * */
	public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
		System.out.println("Client received: " + ByteBufUtil.hexDump(in.readBytes(in.readableBytes())));
	}
	/**
	 *捕捉到异常 
	 * */
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
		cause.printStackTrace();
		ctx.close();
	}

}