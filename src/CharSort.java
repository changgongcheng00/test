import java.util.Scanner;

/**
 * 获取输入的字符串，对字符串培训
 * str.toCharArray() 冒泡
 */
public class CharSort {
    public static void main(String[] args) {
        System.out.println("请输入字符串");
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        Select select = new Select();
        System.out.println("排序后的顺序为："+select.sort(s));
    }
}
class Select{
    public static String sort(String str){
        //将字符串转为char数组
        char[] s1 = str.toCharArray();
        //排序,
//        for (int i = 0; i < s1.length; i++) {
//            for (int j = 0; j < i; j++) {
//                if(s1[i]<s1[j]){
//                    char temp = s1[i];
//                    s1[i]= s1[j];
//                    s1[j]= temp;
//                }
//            }
//        }
        for (int i = 0; i < s1.length-1; i++) {
            for (int j = 0; j < s1.length-i-1; j++) {
                if(s1[j]>s1[j+1]){
                    char temp = s1[j];
                    s1[j]= s1[j+1];
                    s1[j+1]=temp;
                }
            }
        }
        //重组字符串
        String st = new String(s1);
        return st;
    }
}