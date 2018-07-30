import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <b>Description: </b>
 * <b>Author: <b/>zhengcheng
 * <b>DateTime: </b>2018-07-26 17:47<br/>
 */
public class ThreadTest extends Thread{
    @Override
    public void run(){//定义线程执行体
        //通过this获取当前线程，getName()获取当前线程名字
    }
    public static void main(String[] args) {
        new ForkJoinWorkerThread().getPool();
    }

    //后台线程，守护线程
    //sleep 线程睡眠 阻塞 给其他线程执行机会
    //yield 线程让步 就绪 给更高优先级线程执行机会
    //wait notify notifyAll

    //synchronized 同步监视器
    synchronized (){

    }
    public synchronized void draw(){}
    //Lock java5
    public static void main(String[] args) {
        private final ReentrantLock lock =new ReentrantLock();
        lock.lock();
        lock.unlock();
    }

    //阻塞队列
    public static void main(String[] args) throws IOException {
        BlockingDeque<String> bq = new ArrayBlockingQueue<>(2);
        bq.put("a");//add();offer();
        //BlockingQueue空时，使用take()取元素会阻塞线程，使用poll()取元素会返回false

        //建一个socket多线程
        List<Socket> socketList = Collections.synchronizedList(new ArrayList<>());
        ServerSocket ss = new ServerSocket(30000);
        Socket s = ss.accept();
        socketList.add(s);

    }

    //线程组
    ThreadGroup mainGroup = Thread.currentThread().getThreadGroup();

    //线程池 Executors
    ExecutorService pool = Executors.newFixedThreadPool(6);
    Runnable target = ()->{
        Thread.currentThread().getName();
    };
    pool.submit(target);//提交线程
    pool.shutdown();//关闭线程
    //通用池 ForkJoinPool 是ExecutorService的实现类 讲大任务拆分成小任务
    ForkJoinPool poolss = new ForkJoinPool();
    poolss.submit(new PrintTask(0,300));
    poolss.awaitTermination(2,50);
    poolss.shutdown;

    //线程相关类
    //ThreadLocal,ConcurrentHashMap,ConcurrentSkipListMap,ConcuttentSkipListSet,ConcurrentLinkedQueue,ConcurrentLinkedDeque

}

class ThreadSecond implements Runnable{
    @Override
    public void run() {
        //通过Thread.currentThread()获取当前线程，通过new Thread(target,name)创建线程
    }
}
//Callable和Future创建线程
//1.创建Callable实现类 2.使用FutureTask类来包装Callable对象 3.使用FutureTask对象作为Thread对象的target创建并启动线程
//4.调用FutureTask对象的get()方法来获得子线程执行结束后的返回值
class ThreadThird implements Callable<Integer>{
    @Override
    public Object call() throws Exception {
        return null;
    }
    public static void main(String[] args) {
        ThreadThird rt = new ThreadThird();
        FutureTask<String> task = new FutureTask<String>((Callable<String>)()->{
            Thread.currentThread().getName();
            call();
        });
        new Thread(task,"00").start();
    }

    //一般推荐接口欧方式创建多线程
}