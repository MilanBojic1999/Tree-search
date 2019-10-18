import Model.Edge;
import Model.Graph;
import Model.GraphBuilder;
import Model.Search.BreadthFirstSearch;
import View.App;

public class Main {
    public static void main(String[] args) {
        Graph g= GraphBuilder.Instance.buildGraph("Graph1.txt");
        /*for(int i=0;i<g.V();i++){
            for(Edge edge:g.adj(i))
                System.out.print(edge+" ");
            System.out.println();
        }*/

    }
}
