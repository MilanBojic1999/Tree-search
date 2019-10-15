import java.util.List;
import java.util.Stack;

public class DepthFirstSearch extends Search{

    public static final DepthFirstSearch INSTANCE=new DepthFirstSearch();

    public DepthFirstSearch(Graph graph,int s) {
        super(graph,s);
    }

    private DepthFirstSearch() {
        super();
    }

    @Override
    public List<Edge> search(Graph g,int s, int e) {
        boolean[] _marked=new boolean[graph.V()];
        int[] _edgeTo=new int[graph.V()];
        Stack<Integer> nodes=new Stack<>();
        nodes.push(s);

        while (!nodes.isEmpty() && !nodes.contains(e)){

            int v=nodes.pop();
            _marked[v]=true;
            for(Edge edge:g.adj(v)){
                int w=edge.other(v);
                if(!_marked[w]){
                    _edgeTo[w]=v;
                    /*if(w==e)
                        return edgeTo(g,_edgeTo,s,e);*/
                    nodes.push(w);
                }
            }

        }

        return edgeTo(g,_edgeTo,s,e);
    }


    @Override
    public void walk(int s) {
        Stack<Integer> nodes=new Stack<>();
        nodes.push(s);
        while (!nodes.isEmpty()){
            int v=nodes.pop();
            marked[v]=true;
            for(Edge e:graph.adj(v)) {
                int w = e.other(v);
                if (!marked[w]) {
                    edgeTo[w] = v;
                    nodes.push(w);
                }
            }
        }
    }


}
