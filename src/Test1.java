import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntBinaryOperator;
import java.util.function.IntToLongFunction;
import java.util.function.IntUnaryOperator;

/**
 * <b>Description: </b>
 * <b>Author: <b/>zhengcheng
 * <b>DateTime: </b>2018-06-29 9:22<br/>
 */
public class Test1 {
    public static void main(String[] args) {
//        System.out.print("hello world");
//        System.gc();
        /*for (int i = 0; i < 10; i++) {
            System.out.println(i);
            i *=0.1;
            System.out.println(i);
        }
        System.out.println("循环结束");*/

        int[] a ={1,2,3,4};
        int[] b = new int[]{1,2,3,4};
        System.out.println(Arrays.equals(a,b));
        System.out.println(Arrays.toString(Arrays.copyOf(b,10)));
        System.out.println(Arrays.binarySearch(a,2));
        Arrays.sort(a,0,2);
        System.out.println(Arrays.toString(a));
        System.out.println(Arrays.binarySearch(a,0,2,2));
        Arrays.parallelSort(a);
        System.out.println(a);


    }
}
