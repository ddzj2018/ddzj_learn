package open.thl.concurrent;

class MyThread implements Runnable{
    private int ticketsCont=5; //һ����5�Ż�Ʊ

    private String name; //����, Ҳ�����̵߳�����

    public MyThread(String name){
        this.name=name;
    }

    public void run(){
        
        while(ticketsCont>0){
            ticketsCont--; //�������Ʊ��������һ��Ʊ
            System.out.println(name+"������1��Ʊ��ʣ��Ʊ��Ϊ:"+ticketsCont);
        }
        
    }
}

public class TicketsThread
{
    public static void main(String args[]){
        
        //���������̣߳�ģ������������Ʊ
        MyThread mt1=new MyThread("����1");
        MyThread mt2=new MyThread("����2");
        MyThread mt3=new MyThread("����3");

        //���������̣߳�Ҳ���Ǵ��ڣ���ʼ��Ʊ
//        mt1.start();
//        mt2.start();
//        mt3.start();

    }
}