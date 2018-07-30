import java.io.*;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousChannelGroup;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <b>Description: </b>
 * <b>Author: <b/>zhengcheng
 * <b>DateTime: </b>2018-07-27 14:30<br/>
 */
public class WebTest {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        //InetAddress
        InetAddress ip = InetAddress.getByName("www.baidu.com");//获取实例
        ip.isReachable(2000);//是否可达
        ip.getHostAddress();//获取ip
        InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
        local.isReachable(2000);
        local.getCanonicalHostName();//获取全限定域名
        //URLDecoder URLEncoder
        String k = URLDecoder.decode("","utf-8");
        String v = URLEncoder.encode("","GBK");

        //URL,URLConnection,URLPermission,下面为一个工具类部分代码
        URL url = new URL("");
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setConnectTimeout(5);
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Charset","UTF-8");
        int fileSize = conn.getContentLength();//文件大小
        conn.disconnect();
        int threadNum = 10;//线程数
        int currentPartSize = fileSize/threadNum+1;
        RandomAccessFile file = new RandomAccessFile("下载文件保存位置","rw");
        file.setLength(fileSize);//设置本地文件大小
        file.close();
        for (int i = 0; i < threadNum; i++) {
            int startPos = i*currentPartSize;//计算每个线程下载的开始位置
            RandomAccessFile currentPart = new RandomAccessFile("下载文件保存位置","rw");//每个线程用RandomAccessFile下载
            currentPart.seek(startPos);//定位每个线程的下载位置
            private DownThread[] threads;
            threads[i] = new DownThread(startPos,currentPartSize,currentPart)
            threads[i].start();
        }

        //Socket 客户端发起，服务器ServerSocket的accept的accept()向下执行
        Socket so = new Socket("127.0.0.1",30000);//客户端
        so.setSoTimeout(1000000);
        BufferedReader br = new BufferedReader(new InputStreamReader(so.getInputStream()));
        String line = br.readLine();
        br.close();
        so.close();

        ServerSocket ss = new ServerSocket(30000);//服务端
        while (true){
            Socket s = ss.accept();
            PrintStream ps = new PrintStream(s.getOutputStream());//将输出流包装城PrintStream
            ps.close();
            s.close();
        }

        //BIO多线程阻塞 NIO非阻塞-循环处理请求 AIO 异步NIO.2
        //AIOsocket
        //Server
        AsynchronousServerSocketChannel serverChanel = AsynchronousServerSocketChannel.open();//创建对象
        serverChanel.bind(new InetSocketAddress(30000));//在指定地址、端口监听
        while(true){
            Future<AsynchronousSocketChannel> future = serverChanel.accept();//循环接受来自客户端的链接
            AsynchronousSocketChannel socketChannel = future.get();
            socketChannel.write(ByteBuffer.wrap("欢迎来到AIO".getBytes("UTF-8"))).get();
        }
        //Client
        ByteBuffer buff = ByteBuffer.allocate(1024);
        Charset utf = Charset.forName("utf-8");
        AsynchronousSocketChannel clientChannel = AsynchronousSocketChannel.open();//创建对象
        clientChannel.connect(new InetSocketAddress("127.0.0.1",3000)).get();//连接远程服务器
        clientChannel.read(buff).get();//读取数据
        buff.flip();
        String content = utf.decode(buff).toString();
        //线程池 AIO
        ExecutorService executorService = Executors.newFixedThreadPool(20);//创建一个线程池
        AsynchronousChannelGroup channelGroup = AsynchronousChannelGroup.withThreadPool(executorService);//以指定线程池 创建一个AsynchronousChannelGroup
            //以指定线程池 创建一个AsynchronousServerSocketChannel,指定监听本机端口
        AsynchronousServerSocketChannel serverChannel = AsynchronousServerSocketChannel.open(channelGroup).bind(new InetSocketAddress(3000));
        serverChanel.accept();

        //代理服务器java.net.Proxy 代理选择器ProxySelector
    }

    private static class DownThread extends Thread{
        private int startPos;//当前线程下载位置
        private int currentPartSize;//当前线程负责下载的文件大小
        private RandomAccessFile currentPart;//当前线程下载的文件块
        public int length;//当前线程已下载字节数

        public DownThread(int startPos, int currentPartSize, RandomAccessFile currentPart) {
            this.startPos = startPos;
            this.currentPartSize = currentPartSize;
            this.currentPart = currentPart;
        }
        @Override
        public void run(){
            try {
                URL url = new URL("地址");
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setConnectTimeout(5);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Accept-Language","zh-CN");
                conn.setRequestProperty("Charset","UTF-8");
                InputStream inStream = conn.getInputStream();
                inStream.skip(this.startPos);//跳过startPos个字节，表明线程只下载自己负责的那部分文件
                byte[] buffer = new byte[1024];
                int hasRead = 0;
                while(length < currentPartSize && (hasRead = inStream.read(buffer)) !=-1){
                    currentPart.write(buffer,0,hasRead);
                    length += hasRead;//累计该线程下载的总大小
                }
                currentPart.close();
                inStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
