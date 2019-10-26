package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GraphBuilder {
    public static final GraphBuilder Instance=new GraphBuilder();

    /**
     * iz fajla čita podatke, pravi novi graf pa ga vraća
     * @param filename ime fajla
     * @return vraća novi graf
     */

    public SymbolGraph buildGraph(String filename){
        File file=new File("src"+File.separator+"Files"+File.separator+filename);
        SymbolGraph<Integer> graph=null;
        try(BufferedReader br=new BufferedReader(new FileReader(file))){
            int v=Integer.parseInt(br.readLine());
            graph=new SymbolGraph<>(v);
            String line=br.readLine();
            while (line!=null){
                String[] info=line.split(" ");
                int v1=Integer.parseInt(info[0]);
                int v2=Integer.parseInt(info[1]);
                double width=Double.parseDouble(info[2]);
                graph.addEdge(v1,v2,width);
                line=br.readLine();
            }
        }catch (IOException e){}

        return graph;
    }

    public static SymbolGraph<Stanje> buildGraphStanje(Stanje pocStanje,Stanje krajStanje){
        SymbolGraph<Stanje> graph=new SymbolGraph(pocStanje);
        Queue<Stanje> queue=new LinkedList<>();
        queue.add(pocStanje);
        while (!graph.contains(krajStanje) || !queue.isEmpty()){
            Stanje tr=queue.poll();
            assert tr != null;
            for(Stanje nw:tr.generisiStanja()){
                if(!graph.contains(nw)) {
                    graph.addVertice(nw);
                    queue.add(nw);
                }
                graph.addEdge(tr,nw,tr.compareTo(nw));
            }
        }

        return graph;
    }


   /* static SymbolGraph<Stanje> buildGraphStanje(Stanje pocStanje, List<Stanje> krajStanje){
        SymbolGraph<Stanje> graph=new SymbolGraph<>(pocStanje);
        Queue<Stanje> queue=new LinkedList<>();
        queue.add(pocStanje);
        while (!graph.containsAny(krajStanje) && !queue.isEmpty()){
            Stanje tr=queue.poll();
            assert tr != null;
            for(Stanje nw:tr.generisiStanje()){
                if(!graph.contains(nw)) {
                    graph.addNode(nw);
                    queue.add(nw);
                    graph.addEdge(tr,nw);
                }

            }
        }

        return graph;
    }*/

}
