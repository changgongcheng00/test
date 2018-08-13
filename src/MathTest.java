import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Random;

public class MathTest {
    public static void main(String[] args) {
        System.out.println(Math.toDegrees(1.57));//弧度转成角度
        System.out.println(Math.toRadians(90));//角度转成弧度
        System.out.println(Math.acos(1.2));//计算反余弦，返回角度范围在0到pi之间
        System.out.println(Math.asin(0.8));//计算反正弦，返回角度范围在-pi/2到pi/2之间
        System.out.println(Math.atan(2.3));//计算反正切，返回的角度范围在-pi/2到pi/2之间
        System.out.println(Math.cos(1.57));//计算三角余弦
        System.out.println(Math.cosh(1.2));//计算双曲余弦
        System.out.println(Math.sin(1.57));//计算正弦
        System.out.println(Math.sinh(1.2));//计算双曲正弦
        System.out.println(Math.tan(0.8));//计算正切
        System.out.println(Math.tanh(2.1));//计算双曲正切
        System.out.println(Math.atan2(0.1,0.2));//将矩形坐标装换成极坐标
        System.out.println(Math.floor(-1.2));//取整，返回小于目标数的最大整数
        System.out.println(Math.ceil(1.2));//取整，返回大于目标数的最大整数
        System.out.println(Math.floor(2.3));//四舍五入取整
        System.out.println(Math.sqrt(2.3));//计算平方根
        System.out.println(Math.cbrt(9));//计算立方根
        System.out.println(Math.exp(2));//返回欧拉数e的n次幂
        System.out.println(Math.hypot(4,4));//返回 没有中间溢出或者下溢
        System.out.println(Math.IEEEremainder(5,2));//根据IEEE754标准的规定，对两个参数进行余数运算
        System.out.println(Math.pow(3,2));//计算乘方
        System.out.println(Math.log(12));//计算自然对数
        System.out.println(Math.log10(9));//计算底数为10的对数
        System.out.println(Math.log1p(9));//返回参数与1之和的自然对数
        System.out.println(Math.abs(-4.5));//计算绝对值
        System.out.println(Math.copySign(1.2,-1.0));//返回带有第二个浮点符号的，第一个浮点参数
        System.out.println(Math.signum(2.3));//符号函数，如果参数为0，则返回0；如果参数大于0，则返回1.0；如果参数小于0，则返回-1.0
        System.out.println(Math.max(2.3,4.5));//找出最大值
        System.out.println(Math.min(1.2,3.4));//找出最小值
        System.out.println(Math.nextAfter(1.2,1.0));//返回第一个参数和第二个参数之间 与第一个参数相邻的浮点数
        System.out.println(Math.nextUp(1.2));//返回比目标数略大的浮点数
        System.out.println(Math.random());//返回一个随机数 大于等于0.0 小于1.0

        Random rand = new Random(System.currentTimeMillis());//种子
        System.out.println(rand.nextBoolean());
        byte[] buffer = new byte[16];
        rand.nextBytes(buffer);
        System.out.println(Arrays.toString(buffer));
        System.out.println(rand.nextDouble());//生成0.0~1.0
        System.out.println(rand.nextFloat());
        System.out.println(rand.nextGaussian());//生成平均值是0.0，标准差是1.0的伪高斯数
        System.out.println(rand.nextInt());//生成一个处于int范围的伪随机数
        System.out.println(rand.nextInt(26));//生成一个0-26之间的伪随机数
        System.out.println(rand.nextLong());//生成一个long取值范围的伪随机整数

        //BigDecimal 优先使用string构造器
        BigDecimal f1 = new BigDecimal("0.05");
        BigDecimal f2 = BigDecimal.valueOf(0.01);
        System.out.println(f1.add(f2));//加
        System.out.println(f1.subtract(f2));//减
        System.out.println(f1.multiply(f2));//乘
        System.out.println(f1.divide(f2));//除
    }
}
