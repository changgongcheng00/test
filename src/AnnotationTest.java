import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.lang.annotation.*;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

public class AnnotationTest {
    //annotation5个基本
    @Override//重写父类的方法
    @Deprecated//标示 过时
    @SuppressWarnings(value="unchecked")//关闭整个类的编译器警告
    @SafeVarargs//抑制堆污染警告
    @FunctionalInterface//指定接口必须是函数式接口

    @Retention(value=RententionPolicy.RUNTIME)//修饰注解定义 保留时间
    @Target(ElementType.FIELD)//定义，修饰那些程序单元
    @Documented //api文档会包含注解说明
    @Inherited//指定注解具有继承性

    @interface Test; //自定义注解

    public static void main(String[] args) throws Exception{
        //文件流 NIO 将文件的一段映射到内存中
        File f = new File("");
        FileChannel inChannel = new FileInputStream(f).getChannel();
        FileChannel outChannel = new FileOutputStream("a.txt").getChannel();
        //字符集
        Charset cs = Charset.forName("ISO-8859-1");
        CharsetEncoder cnEncoder = cs.newEncoder();
        CharsetDecoder cnDecoder = cs.newDecoder();
        CharBuffer cbuff = CharBuffer.allocate(8);
        cbuff.put("1");
        cbuff.flip();
        ByteBuffer bbuff = cnEncoder.encode(cbuff);
        cnDecoder.decode(bbuff);
        //文件锁
        FileLock lock = inChannel.tryLock();
        Thread.sleep(10000);
        lock.release();
        //JAVA7的NIO2
        Path path = Paths.get(".");
        path.getRoot();
        Files.copy(Paths.get("11.java"),new FileOutputStream("a.txt"));//复制
        List<String> lines =Files.readAllLines(Paths.get("1.java"),Charset.forName("gbk"));//读取所有行
        Files.size(Paths.get(""));//判断文件大小
        Files.write("")//写入
        //FileVisitor 遍历文件和目录
        Files.walkFileTree(Paths.get("g:","publish","codes","15"),new SimpleFileVisitor<Path>()){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs){
                //访问文件触发
                if(file.endsWith()){
                    return FileVisitResult.TERMINATE;
                }
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult preVisitDirectory(Path dir,BasicFileAttributes attrs){
                return; FileVisitResult.CONTINUE;
            }
        }
        Files.getAttribute("",BasicFileAttributes.class);//访问文件属性


    }
}
