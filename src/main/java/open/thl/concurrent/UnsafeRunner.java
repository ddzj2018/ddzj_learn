package open.thl.concurrent;

import java.lang.reflect.Field;

import java.util.concurrent.CountDownLatch;

import java.util.concurrent.ExecutorService;

import java.util.concurrent.Executors;

import sun.misc.Unsafe;

public class UnsafeRunner {
	public static void main(String args[]) throws InterruptedException {
		int size = 1000000;
		CountDownLatch countDownLatch = new CountDownLatch(size);
		TomUnsafeRunner tomRunner = new TomUnsafeRunner(false, countDownLatch,"runner");
		ExecutorService executorService = Executors.newCachedThreadPool();
		for (int i = 1; i <= size; i++) {
			executorService.execute(new Thread2RunUnsafe(countDownLatch,tomRunner, i + "_��"));
		}
		countDownLatch.await();
		executorService.shutdown();
		// new Thread(volatileRunner).start();
	}
	static class Thread2RunUnsafe implements Runnable {
		private CountDownLatch countDownLatch;
		private TomUnsafeRunner tomRunner;
		private String name;
		public Thread2RunUnsafe(CountDownLatch countDownLatch,TomUnsafeRunner tomRunner, String name) {
			super();
			this.countDownLatch = countDownLatch;
			this.tomRunner = tomRunner;
			this.name = name;
		}
		public void run() {
			System.out.println(this.name + ":running...");
			this.tomRunner.doWork();
			System.out.println(this.name + ":����...");
			this.countDownLatch.countDown();
		}
	}

	static class TomUnsafeRunner {
		volatile boolean shutdownRequested = false;
		// boolean shutdownRequested = false;
		String name;
		int unsafe_var = 23;
		public TomUnsafeRunner(boolean shutdownRequested,CountDownLatch countDownLatch, String name) {
			super();
			this.shutdownRequested = shutdownRequested;
			this.name = name;
		}
		public void shutdown() {
			this.shutdownRequested = true;
		}
		public void doWork() {

			// //////////////////һ��д��///////////////
//			 if (unsafe_var == 23) {
//			 System.out.println("���жϳ����ˣ�unsafe_var ==23��������Ϊ46..");
//			 try {
//			 //ģ��ҵ�����
//			 Thread.sleep(1000);
//			 } catch (InterruptedException e) {
//			 e.printStackTrace();
//			 }
//			 unsafe_var = 46;
//			 }
			// ///////////////��JAVA CAS����
			Unsafe unsafe = UnsafeSupport.getInstance();
			Class clazz = TomUnsafeRunner.class;
			Field[] fields = clazz.getDeclaredFields();
			// ��ȡ����ƫ����������ͨ�����ƫ��������������
			for (Field f : fields) {
				System.out.println(f.getName() + ":"+ unsafe.objectFieldOffset(f));
			}
			// arg0, arg1, arg2, arg3 �ֱ���Ŀ�����ʵ����Ŀ���������ƫ��������ǰԤ��ֵ��Ҫ���ֵ
			// unsafe.compareAndSwapInt(arg0, arg1, arg2, arg3)
			// ƫ���������һ�㲻����,intParam������Ե�ƫ����
			// unsafe_var:8
			long intParamOffset = 12;
			if (unsafe.compareAndSwapInt(this, intParamOffset, 23, 46)) {
				System.out.println("���жϳ����ˣ�unsafe_var == 23��������Ϊ46..");
				try {
					// //ģ��ҵ�����
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

	static class UnsafeSupport {
		// private static Logger log = Logger.getLogger(UnsafeSupport.class);
		private static Unsafe unsafe;
		static {
			Field field;
			try {
				// �ɷ�����Unsafe���õ���Ϣ
				field = Unsafe.class.getDeclaredField("theUnsafe");
				field.setAccessible(true);
				// ��ȡ��̬����,Unsafe������JVMʱ��rt.jarװ��
				unsafe = (Unsafe) field.get(null);
			} catch (Exception e) {
				System.out.println(e);
				// log.error("Get Unsafe instance occur error", e);
			}
		}
		/**
		 * ��ȡ{@link Unsafe }
		 */
		public static Unsafe getInstance() {
			return unsafe;
		}
	}
}
