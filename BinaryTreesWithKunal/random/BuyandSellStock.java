public class BuyandSellStock {
    public static int maxProfit(int[] prices) {
      int maxProfit =0;
      for(int i =0;i<prices.length;i++){
          int tempProfit=0;
          for(int j =i+1;j<prices.length;j++){
              tempProfit=prices[i]-prices[j];
              maxProfit=Math.min(tempProfit,maxProfit);
          }
      }

      return Math.abs(maxProfit);
    }

    public static void main(String[] args) {
        System.out.println(maxProfit( new int[]{7,6,4,3,1}));
    }
}
