public class Subset {
    public static void printsubset(String a, String b){
        if(a.isEmpty()){
            System.out.println(b);
            return;
        }
        printsubset(a.substring(1),b+a.charAt(0));
        printsubset(a.substring(1),b);


    }
    public static void main(String[] args) {
        printsubset("abc","");
    }
}
