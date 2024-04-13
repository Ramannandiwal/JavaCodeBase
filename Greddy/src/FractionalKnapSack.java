import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class FractionalKnapSack {

    public static void main(String[] args) {
        StringBuilder a = new StringBuilder();

        double[] val = {60, 100, 120};
        double[] weight = {10, 20, 30};
        double[][] pw = new double[val.length][2];
        for (int i = 0; i < val.length; i++) {
            pw[i][0] = i;
            pw[i][1] = (double) val[i] / weight[i];
        }
        double W = 50; // Convert W to double
        Arrays.sort(pw, Comparator.comparingDouble(o -> o[1]));
        double maxProfit = 0;
        for (int i = pw.length - 1; i >= 0; i--) {
            if (weight[(int) pw[i][0]] <= W) {
                maxProfit += val[(int) pw[i][0]];
                W -= weight[(int) pw[i][0]];
            } else {
                maxProfit += (W / weight[(int) pw[i][0]]) * val[(int) pw[i][0]]; // Corrected formula
                break;
            }
        }
        System.out.println(maxProfit);
    }

    public static int birtheday(String month , int date){
        if(month.equals("july")&&date==5){
            return 1;
        }
        return 0;
    }
}
