import jdk.nashorn.api.tree.BinaryTree;
import jdk.nashorn.api.tree.ExpressionTree;
import jdk.nashorn.api.tree.Tree;
import jdk.nashorn.api.tree.TreeVisitor;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * Author: 长弓成
 * DateTime: 2018-03-13 15:19
 */
public class Test {
    BinaryTree bt = new BinaryTree() {
        @Override
        public ExpressionTree getLeftOperand() {
            return null;
        }

        @Override
        public ExpressionTree getRightOperand() {
            return null;
        }

        @Override
        public long getStartPosition() {
            return 0;
        }

        @Override
        public long getEndPosition() {
            return 0;
        }

        @Override
        public Kind getKind() {
            return null;
        }

        @Override
        public <R, D> R accept(TreeVisitor<R, D> visitor, D data) {
            return null;
        }
    };
    public int get(int b){
        int a =0;
        try{
            System.out.println(20);
            a++;
            return a;
        }catch(Exception e){
            System.out.println(30);
            a++;
            return a;
        }finally{
            System.out.println(40);
            a++;
            return a;
        }
    }

    public static void main(String[] args) {
        Test t = new Test();
        int a =t.get(1);
        System.out.println(a);

        int x =1;
        int y =2;
        x = x+y;
        y = x-y;
        x = x-y;
        System.out.println("x="+x+",y="+y);

        String str = "16984534189";
        char[] ch = str.toCharArray();
        for(int i=0;i<ch.length;i++){
            for(int j=0;j<ch.length-i-1;j++){
                if(ch[j]>ch[j+1]){
                    char temp;
                    temp = ch[j];
                    ch[j] = ch[j+1];
                    ch[j+1] = temp;
                }
            }
        }
        System.out.println(ch);
    }
}

