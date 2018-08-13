import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class CollectionTest {
    public static void main(String[] args) {
        Collection books = new HashSet<String>();
        books.add("a");books.add("b");books.add("c");
        //Lambda遍历
        books.forEach(obj -> System.out.println(obj));
        //iterator遍历,迭代器不能修改值
        Iterator it = books.iterator();
        while(it.hasNext()){
            String book = (String)it.next();
            System.out.println(book);
        }
        //Lambda遍历iterator
        it.forEachRemaining(obj -> System.out.println(obj));
        //foreach 修改值将导致 ConcurrentModificationException
        for (Object obj:books ) {
            String book = (String)obj;
            System.out.println(book);
        }
        //Predicate java8新增
        books.removeIf(ele ->((String)ele).length() >10);
        books.forEach(ele -> ((String)ele).contains("a"));
        //Stream Java
        IntStream is = IntStream.builder()
                .add(20).add(12).add(-2).add(19).build();
        System.out.println(is.anyMatch(ele->ele>20));
        IntStream newIs = is.map(ele -> ele*2+1);//将is映射成一个stream,每个元素2倍+1
        newIs.forEach(System.out::println);
        System.out.println(books.stream().filter(ele->((String)ele).contains("a")));
        //HashSet 不是同步的，多线程需要通过代码保证同步
        //计算hash获取位置，将值比较。同样的值会导致链式存储，因此尽量不修改参与计算的hashCode()、equals()的实例变量
        //linkedHashSet 使用链表维护元素的次序，迭代访问全部元素时有很好的性能

        //TreeSet 是SortedSet的实现类，根据元素实际值的大小培训，红黑树-自平衡二叉查找树
        //要求添加的值为统一类型（比较大小）

        //EnumSet 是一个专为枚举类设计的集合类，元素是指定枚举类型的枚举值，顺序以枚举值在Enum类内定义的顺序排序

        //set性能分析
        //HashSet性能优于TreeSet，LinkedHashSet普通插入删除略慢，遍历更快
        //EnumSet性能最好，但只能单一枚举类型。Set的三个实现类均线程不安全
        SortedSet s = Collections.synchronizedSortedSet(new TreeSet());

        //list
        //ArrayList和Vector 少用Vector
        List list = new ArrayList();
        list.add("1");
        list.add(new String("2"));
        list.set(1,"3");
        System.out.println(list.subList(0,1));
        list.sort((o1, o2) -> ((String)o1).length() - ((String)o2).length());
        System.out.println(list);
        list.replaceAll(ele ->((String)ele).length());
        System.out.println(list);
        //listIterator
        ListIterator lit = list.listIterator();
        while(lit.hasNext()){
            System.out.println(lit.next());
        }
        while(lit.hasPrevious()){//向上迭代
            System.out.println(lit.previous());
        }
        List fixedList = Arrays.asList("1","2");//固定长度的List
        //Queue 队列 先进先出FIFO 按加入队列顺序排序
        //PriorityQueue 按元素大小重新排序
        //Deque接口 双端队列 Queue子接口
        //ArrayDeque Deque的实现类 可以作为栈或者队列使用

        //LinkedList 实现了Deque接口 使用迭代器遍历
        // 随机访问性能差 插入、删除性能出色
        LinkedList llist = new LinkedList();
        llist.offer("队列尾部");
        llist.push("栈的顶部");
        llist.offerFirst("队列头部，相当于栈顶部");
        System.out.println(llist.peekFirst());//访问
        System.out.println(llist.pollLast());//访问并删除
        System.out.println(llist.pop());//将栈顶元素弹出“栈”

        //HashMap
        Map map = new HashMap();
        map.put("1","2");
        System.out.println(map.containsValue(2));
        LinkedHashMap lhm = new LinkedHashMap();
        lhm.put("1","2");
        lhm.put("2","3");
        lhm.forEach((key,value)-> System.out.println(key+"-->"+value));

        //Properties
        //Properties是Hashtable的子类
        Properties props = new Properties();
        props.setProperty("1","2");
        try {
            props.store(new FileOutputStream("a.ini"),"comment line");
        } catch (IOException e) {
            e.printStackTrace();
        }

        //同步控制
        Collection c = Collections.synchronizedCollection(new ArrayList<>());
    }
}
