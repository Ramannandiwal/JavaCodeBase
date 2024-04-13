import java.util.Stack;

class MyQueue {
    Stack<Integer> first;
    Stack<Integer> second;

    public MyQueue() {
first=new Stack<>();
second=new Stack<>();
    }

    public void push(int x) {
     first.push(x);
    }

    public int pop() {
       if(empty())return -1;
       while (!first.empty()){
           second.push(first.pop());
       }
       int removed = second.pop();
       while (!second.empty()){
           first.push(second.pop());
       }
       return removed;
    }

    public int peek() {
 while (!first.empty()){
     second.push(first.pop());
 }
 int peekelement = second.lastElement();
 while (!second.isEmpty()){
     first.push(second.pop());
 }
 return peekelement;
    }

    public boolean empty() {
return first.empty();
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */