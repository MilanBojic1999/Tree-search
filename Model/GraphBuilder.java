package Model;

import Model.Stanja.Stanje;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.List;

public class GraphBuilder {

    /**
     * iz fajla čita podatke, pravi novi graf pa ga vraća
     * @param filename ime fajla
     * @return vraća novi graf
     */

    public static SymbolGraph buildGraph(String filename){
        File file=new File("src"+File.separator+"Files"+File.separator+filename+".txt");
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

    /**
     * Izgradnja grafa od Stanja
     * @param pocStanje odnosno koren grafa
     * @param krajStanje graf se pravi doklegod ne dođe do ovog čvora
     * @return novi Simbol Graf
     */

    public static SymbolGraph<Stanje> buildGraphStanje(Stanje pocStanje, Stanje krajStanje){
        SymbolGraph<Stanje> graph=new SymbolGraph(pocStanje);
        Queue<Stanje> queue=new LinkedList<>();
        queue.add(pocStanje);
        while (!graph.contains(krajStanje) && !queue.isEmpty()){
            Stanje tr=queue.poll();
            assert tr != null;
            for(Stanje nw:tr.generisiStanja()){
                if(!graph.contains(nw)) {
                    if(nw.compareTo(krajStanje)<tr.compareTo(krajStanje)+1) {
                        graph.addVertice(nw);
                        queue.add(nw);
                        if(!graph.hasEdgeToVertace(graph.getIndex(nw)))
                            graph.addEdge(tr,nw,tr.compareTo(nw));
                    }
                }
            }
        }

        return graph;
    }

    /**
     * iz pocetnog stanja gradi graf, sve dok ne dođe do jednog od
     * krajnjih stanja
     * @param pocStanje koren grafa
     * @param krajStanje stanja do kogih se gradi graf
     * @return novi Simbol graf
     */

    public static SymbolGraph<Stanje> buildGraphStanje(Stanje pocStanje, List<Stanje> krajStanje){
        SymbolGraph<Stanje> graph=new SymbolGraph<>(pocStanje);
        Queue<Stanje> queue=new LinkedList<>();
        queue.add(pocStanje);
        while (!graph.containsAny(krajStanje) && !queue.isEmpty()){
            Stanje tr=queue.poll();
            assert tr != null;
            for(Stanje nw:tr.generisiStanja()){
                if(!graph.contains(nw)) {
                    graph.addVertice(nw);
                    queue.add(nw);
                    graph.addEdge(tr,nw,tr.compareTo(nw));
                    if(nw.isGoal(nw)){
                        for(Stanje dd:nw.generisiStanja()){
                            if(!graph.contains(tr)){
                                graph.addVertice(tr);
                                graph.addEdge(nw,dd,nw.compareTo(dd));
                            }
                        }
                        System.out.println(graph.N());
                        return graph;
                    }
                }
            }
        }
        return graph;
    }

}
