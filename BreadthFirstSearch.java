import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch extends Search {
    private boolean[] marked;
    private int[] edgeTo;
    private Graph graph;
    public static final BreadthFirstSearch INSTANCE=new BreadthFirstSearch();

    public BreadthFirstSearch() {
    }

    public BreadthFirstSearch(Graph graph,int s) {
        super(graph,s);
    }

    @Override
    public List<Edge> search(Graph g, int s, int e) {
        boolean[] _marked=new boolean[g.V()];
        int[] _edgeTo=new int[g.V()];

        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty() && !queue.contains(e)){
            int v=queue.poll();
            _marked[v]=true;
            for(Edge edge:graph.adj(v)){
                int w=edge.other(v);
                if(!_marked[v]){
                    _edgeTo[w]=v;
                    queue.add(w);
                }
            }
        }

        return edgeTo(g,_edgeTo,s,e);
    }

    @Override
    public void walk(int s) {
        Queue<Integer> queue=new LinkedList<>();
        queue.add(s);
        while (!queue.isEmpty()){
            int v=queue.poll();
            marked[v]=true;
            for(Edge edge:graph.adj(v)){
                int w=edge.other(v);
                if(!marked[w]){
                    edgeTo[w]=v;
                    queue.add(w);
                }
            }
        }
    }
}
