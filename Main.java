import Model.Edge;
import Model.Graph;
import Model.Search.*;

public class Main {
    public static void main(String[] args) {
        Graph g=GraphBuilder.Instance.buildGraph("Graph1.txt");
        /*Search search=new BidirectionalSearch(g,1);
        for(Edge w:search.search(g,0,10)){
            System.out.print(w+" ");
        }*/

        //GraphView gv=new GraphView(g,0);

    }
}
