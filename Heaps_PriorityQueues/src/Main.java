import java.util.PriorityQueue;

class Student implements Comparable<Student> {
    int rank ;
    String name ;

    Student(int rank, String name){
        this.rank = rank;
        this.name = name;
    }

    @Override
    public int compareTo(Student o) {
        return  this.rank-o.rank;
    }

}
public class Main {



    public static void main(String[] args) {


         Student s = new Student(1,"kunal");

         Student s1 = new Student(2,"kumar");
         Student s2 = new Student(3,"kumari");
         Student s3 = new Student(4,"kumariya");
         Student s4 = new Student(5,"kumariya");
         Student s5 = new Student(6,"kumariya");
        PriorityQueue<Student> pq = new PriorityQueue<>();
                pq.add(s);
                pq.add(s1);
                pq.add(s2);
                pq.add(s3);
                pq.add(s4);
                pq.add(s5);
                while (!pq.isEmpty()){
                    System.out.println(pq.peek().name+" "+pq.poll().rank);
                }


    }


}