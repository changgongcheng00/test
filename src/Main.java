import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
//        System.out.println("Hello World!");
//        System.out.println(args.length);
//        for (String arg:args) {
//            System.out.println(arg);
//        }
        /**
         * Scnner
         */
//        Scanner sc = new Scanner(System.in);
//        while (sc.hasNext()){
//            System.out.println(sc.next());
//        }
        /**
         * Buffer
         */
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String inputStr = null;
//        while((inputStr = br.readLine())!= null){
//            System.out.println(inputStr);
//        }
        /**
         * map循环获取 系统所有变量
         */
//        Map<String,String> map = System.getenv();
//        for (String name: map.keySet()) {
//            System.out.println(map.get(name));
//        }
//        String s1 = new String("A");
//        String s2 = new String("A");
//        System.out.println(s1.hashCode()+"="+s2.hashCode());
//        System.out.println(System.identityHashCode(s1)+"="+System.identityHashCode(s2));

        /**
         * runtime
         */
//        Runtime rt = Runtime.getRuntime();
//        System.out.println("处理器数量"+rt.availableProcessors()+"\n空闲内存："+rt.freeMemory()/1048576
//        +"\n总内存数量："+rt.totalMemory()/1048576+"\n可用最大内存"+rt.maxMemory()/1048576);

        /**
         * Object
         */
        Object o = new Object();
    }
}
