import java.lang.annotation.Annotation;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.*;
import java.net.URL;
import java.net.URLClassLoader;
import java.sql.Driver;
import java.util.Arrays;

/**
 * <b>Description: </b>
 * <b>Author: <b/>zhengcheng
 * <b>DateTime: </b>2018-07-30 14:19<br/>
 */
@Repeatable(Annos.class)
@interface Anno{}
@Retention(value = RetentionPolicy.RUNTIME)
@interface Annos{
    Anno[] value();
}
@SuppressWarnings(value="unchecked")
@Deprecated
@Anno
@Anno
public class ReflectTest {
    //3个类加载器
    //1.Bootstrap ClassLoader:根类加载器
    //2.Extension ClassLoader:扩展类加载器
    //3.System ClassLoader:系统类加载器

    //URLClassLoader 加载指定类
//    URL[] urls = {new URL("file:mysql-connector-java-5.1.30-bin.jar")};
//    URLClassLoader myClassLoader = new URLClassLoader(urls);
//    Driver driver = (Driver)myClassLoader.loadClass("com.mysql.jdbc.Driver").newInstance();

    public ReflectTest(){String name;}
    public void info(){
        System.out.println("执行无参的info方法");
    }
    public void info(String str){
        System.out.println("执行有参的info方法");
    }

    class Inner{};

    public static void main(String[] args) throws Exception{
        Class<ReflectTest> clazz = ReflectTest.class;//获取ReflectTest对应的class
        Constructor[] ctors = clazz.getDeclaredConstructors();//获取全部构造器
        for(Constructor c :ctors){
            System.out.println(c);
        }
        Constructor[] publicCtors = clazz.getConstructors();//获取public构造器
        for(Constructor c :publicCtors){
            System.out.println(c);
        }
        Method[] mtds = clazz.getMethods();//获取全部的public方法
        for(Method md : mtds){
            System.out.println(md);
        }
        Method info = clazz.getMethod("info",String.class);//获取指定方法
            //java8新增的方法
        Parameter[] parameters = info.getParameters();
        for(Parameter p:parameters){
            if(p.isNamePresent()){
                System.out.println(p.getName());//参数名
                System.out.println(p.getType());//形参类型
                System.out.println(p.getParameterizedType());//泛型类型
            }
        }
        Annotation[] anns = clazz.getAnnotations();//获取全部注解
        for(Annotation an : anns){
            System.out.println(an);
        }
        Arrays.toString(clazz.getAnnotationsByType(SuppressWarnings.class));//获取SuppressWarnings注解
        Class<?>[] inners = clazz.getDeclaredClasses();//获得全部内部类
        for(Class c : inners){
            System.out.println(c);
        }

        Class inClazz = Class.forName("ReflectTest$Inner");//使用Class.forName方法获取内部类
        inClazz.getDeclaredClasses();//访问类所在的外部类
        inClazz.getPackage();
        inClazz.getSuperclass();
        //使用反射生成并操作对象
        inClazz.newInstance();//使用inClazz对应类的默认构造器创建实例
        Constructor ctor = inClazz.getConstructor(String.class);//用指定构造器创建对象
        Object obj = ctor.newInstance("测试窗口");

        Method mtd = inClazz.getMethod("info",String.class);
        //mtd.invoke();//可用来执行方法

        Object arr = Array.newInstance(String.class,10);

        //使用Proxy和InvocationHandler创建动态代理
        InvocationHandler handler = new MyInvokationHandler(...);
        Proxy.newProxyInstance(ReflectTest.class.getClassLoader(),new Class[]{ReflectTest.class},handler);
    }

    class MyInvokationHandler implements InvocationHandler{

        //需要被代理的对象
        private Object target;
        public void setTarget(Object target){
            this.target = target;
        }
        //执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            DogUtil du = new DogUtil();
            du.method1();
            Object result = method.invoke(target,args);//通过反射以target作为主调来执行method方法
            du.method2();
            return result;
        }
    }

    class DogUtil {
        public void method1(){
            System.out.println("1");
        }
        public void method2(){
            System.out.println("2");
        }
    }
}
