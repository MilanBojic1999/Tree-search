package Model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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


}
