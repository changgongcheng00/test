/**
 * 新建线程类，新建10个线程运行这个类
 * wait sleep start nofifyAll
 */
public class ThreadTest {
    private static final Object obj = new Object();
    static class R implements Runnable{
        int i;
        R(int i){
            this.i = i;
        }
        @Override
        public void run() {
            try {
                /**
                 * synchronized 保持代码段不被多个线程执行
                 */
                synchronized (obj){
                    System.out.println("线程"+i+"等待中");
                    obj.wait();
                    System.out.println("线程"+i+"运行了");
                    Thread.sleep(3000);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 建10个线程 并启动，notifyR中被wait的obj
     * @param args
     */
    public static void main(String[] args) {
        Thread[] rs = new Thread[10];
        for (int i = 0; i < 10; i++) {
            rs[i] = new Thread(new R(i));
        }
        for (Thread r:rs) {
            r.start();
        }
        synchronized (obj){
            obj.notifyAll();
        }
    }
}
