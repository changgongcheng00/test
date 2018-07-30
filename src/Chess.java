import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <b>Description: </b>
 * <b>Author: <b/>zhengcheng
 * <b>DateTime: </b>2018-07-02 16:24<br/>
 */
public class Chess {
    private static int BOARD_SIZE = 15;
    private String [][] board;
    final static int [][] str = new int[BOARD_SIZE][BOARD_SIZE];
    /**
     * <b>Description: 初始化棋盘</b>
     * <b>Author: <b/>zhengcheng
     */
    public void initBoard(){
        board = new String[BOARD_SIZE][BOARD_SIZE];
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                board[i][j]="＋";
            }
        }
    }
    /**
     * <b>Description: 输出棋盘</b>
     * <b>Author: <b/>zhengcheng
     */
    public void printBoard(){
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     * <b>Description: 坐标去重复</b>
     * <b>Author: <b/>zhengcheng
     */
    public static boolean removeRepeat(int xPox,int yPox){
        if(str[xPox][yPox] == 0){
            str[xPox][yPox] = 1;
        }else{
            return false;
        }
        return true;
    }

    /**
     * <b>Description: 电脑生成的位置已有棋子时，则重新生成位置</b>
     * <b>Author: <b/>zhengcheng
     */
    public static void computer(Chess gb){
        int axPox = (int)(Math.random()*BOARD_SIZE);
        int bxPox = (int)(Math.random()*BOARD_SIZE);
        if(!removeRepeat(axPox,bxPox)){
            computer(gb);
        }else{
            gb.board[axPox][bxPox]="○";
        }
    }
    /**
     * <b>Description: 棋盘满时校验</b>
     * <b>Author: <b/>zhengcheng
     */
    public static boolean full(){
        int sum =0;
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                sum +=str[i][j];
            }
        }
        if(sum >= BOARD_SIZE*BOARD_SIZE){
            System.out.println("棋盘满啦");
            return false;
        }else{
            return true;
        }

    }

    public static void main(String[] args) throws IOException {
        Chess gb = new Chess();
        gb.initBoard();
        gb.printBoard();
        System.out.println("下棋开始,请输入1,2类型的数据，要求在1-15之间");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = null;
        while ((inputStr = br.readLine()) != null){
            //人下棋,先判断棋盘有没有空余位置
            if(!full()){
                break;
            }
            if(!inputStr.matches("([1-9]|(1[0-5])),([1-9]|(1[0-5]))")){
                System.out.println("输入不合法");
                continue;
            }
            String[] posStrArr = inputStr.split(",");
            int xPox = Integer.parseInt(posStrArr[0]);
            int yPox = Integer.parseInt(posStrArr[1]);
            if(!removeRepeat(xPox-1,yPox-1)){
                System.out.println("该位置已有棋子，请重新选择");
                continue;
            }
            gb.board[xPox-1][yPox-1]="●";
            //电脑下棋,先判断棋盘有没有空余位置
            if(!full()){
                break;
            }
            computer(gb);
            gb.printBoard();
        }
    }
}
