import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public double myPow(double x, int n) {
        double result = 1;
        if(n>=0){
            for(int i =1;i<=n;i++){
                result*=x;
            }
        }else{


            for(int i =1;i<=Math.abs(n);i++){
                result*=x;
            }
            System.out.println(result);
            return 1/result;
        }
        return result;
    }
    public static void main(String[] args) {
        System.out.println(new Main().myPow(2,-2));
    }
}