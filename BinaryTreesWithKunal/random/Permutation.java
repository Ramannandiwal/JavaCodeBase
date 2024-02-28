public class Permutation {
//    public  static void permuationprint(String a, String b){
//        if(a.isEmpty()){
//            System.out.println(b);
//            return;
//        }
//        char ch = a.charAt(0);
//        for(int i =0;i<=b.length();i++){
//
//            String first = b.substring(0,i);
//            String second = b.substring(i,b.length());
//            permuationprint(a.substring(1),first+ch+second);
//        }
    public static  void  permuationprint(String str ,String ans){
        if(str.length()==0){
            System.out.println(ans);
            return;
        }
        for(int i =0;i<str.length();i++){
            char ch = str.charAt(i);
            String newstr=str.substring(0,i)+str.substring(i+1);
            permuationprint(newstr,ans+ch);
        }
    }
    public static void main(String[] args) {
//        permuationprint("abc","");

    }
}
