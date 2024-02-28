public class DiceRoll {
    public static void printcombinaitno(String p,int target){
        if(target==0){
            System.out.println(p);
            return;
        }
        for(int i =1;i<=target;i++){
            printcombinaitno(p+i,target-i);
        }
    }
    public static void main(String[] args) {
        printcombinaitno("",5);
    }
}
