public class Edge {
    int src;
    int dest;
    int weight ;
    Edge(int src, int dest){
        this.src=src;
        this.dest=dest;
    }
    Edge(int src,int dest,int weight){
        this.src=src;
        this.dest=dest;
        this.weight=weight;
    }
}
