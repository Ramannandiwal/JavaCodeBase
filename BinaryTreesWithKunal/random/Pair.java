public class Pair
{
    public static int pair(int n ){
        if(n==1||n==2){
            return n;
        }
        int single =pair(n-1);
        int doublepair = pair(n-2);
        doublepair=(n-1)*doublepair;
        return single+doublepair;

    }
    public static void main(String[] args) {
        System.out.println(pair(3));
    }
}
