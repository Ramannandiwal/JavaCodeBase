import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Graph {


 static class Edge{
        int src ;
        int dest;
        boolean visited;

        Edge(int src, int destination){
            this.src=src;
            this.dest=destination;

        }
    }

    public static void main(String[] args) {
//        Scanner sc = new Scanner(System.in);
//        int veritce = 5;
//        ArrayList<ArrayList<Edge>> outer = new ArrayList<>();
//        for(int i=1;i<=veritce;i++){
//            System.out.println("Enter the nubmer of Edges ");
//            int EdgeNubmer = sc.nextInt();
//            ArrayList<Edge> list = new ArrayList<>();
//            for(int j = 0;j<EdgeNubmer;j++){
//                System.out.println("Enter the desitonation ");
//
//                int destinaiton = sc.nextInt();
//                  list.add(new Edge(i,destinaiton));
//
//
//            }
//            outer.add(list);
//        }
        int vertice = 7;
        ArrayList<ArrayList<Edge>> outer = new ArrayList<>();

        for(int i =0;i<7;i++){
            outer.add(i,new ArrayList<>());
        }

        outer.get(0).add(new Edge(0,1));
        outer.get(0).add(new Edge(0,2));
        outer.get(1).add(new Edge(1,3));
        outer.get(1).add(new Edge(1,0));
        outer.get(2).add(new Edge(2,4));
        outer.get(2).add(new Edge(2,0));
        outer.get(3).add(new Edge(3,5));
        outer.get(3).add(new Edge(3,1));
        outer.get(4).add(new Edge(4,5));
        outer.get(4).add(new Edge(4,2));
        outer.get(5).add(new Edge(5,6));
        outer.get(5).add(new Edge(5,3));
        outer.get(5).add(new Edge(5,4));





        for(ArrayList<Edge> local :outer)
        {
            for(Edge a : local){
                System.out.println(a.src+"----->"+a.dest);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(outer.get(0).get(0).src);
        outer.get(0).get(0).visited=true;
//        while (!queue.isEmpty()){
//            int vertices = queue.poll();
//            System.out.print(vertices+" ");
//            for(int j =0;j<outer.get(vertices).size();j++){
//                if(!outer.get(vertices).get(j).visited){
//                    queue.add(outer.get(vertices).get(j).dest);
//                    outer.get(vertices).get(j).visited=true;
//                }
//            }
//
//
//
//        }
        while (!queue.isEmpty()) {
            int vertices = queue.poll();
            System.out.print(vertices + " ");
            for (int j = 0; j < outer.get(vertices).size(); j++) {
                Edge currentEdge = outer.get(vertices).get(j);
                if (!currentEdge.visited) { // Check if the destination vertex is not visited
                    queue.add(currentEdge.dest);
                    currentEdge.visited = true; // Mark destination as visited
                }
            }
        }


    }
    }

