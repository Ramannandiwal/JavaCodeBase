import java.util.Stack;

public class Main {
    public static int calculate(String s) {
       Stack<Character> s1 = new Stack<>();
       Stack<Integer> s2 = new Stack<>();

       s="("+s+")";
       for(int i = 0;i<s.length();i++){
           char c = s.charAt(i);
           if(c=='('||c=='+'||c=='-'){
               s1.push(c);
           } else if (c==')') {
               int result = 0;
               while (s1.peek()!='('){
                   int n2 = s2.pop();
                   int n1 = s2.pop();
                   char d = s1.pop();
                   result=result+((d=='+')?n1+n2:n1-n2);
                   s2.push(result);
                   s1.pop();
               }
               s1.pop();
           }
           else{
                int m = Integer.valueOf(c-'0');
                s2.push(m);
           }
       }
       return s2.pop();
    }



    public static void main(String[] args) {
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
    }
}
