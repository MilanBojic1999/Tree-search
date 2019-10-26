package Model;

import java.util.HashMap;

public class SymbolGraph<Item> extends Graph {

    private HashMap<Item,Integer> tabel;
    private Item[] list;
    private int n;
    public SymbolGraph(int v) {
        super(v);
        tabel=new HashMap<>();

        list=(Item[])new Object[V()];
        n=0;
    }

    public void addVertice (Item item){
        if(n==V())
            return;
        if(item instanceof Integer){
            tabel.put(item,(Integer)item);
            list[(Integer)item]=item;
        }else{
            list[n]=item;
            tabel.put(item,n++);
        }
    }

    public void addEdge(Item item1,Item item2,double width){
        if(!tabel.containsKey(item1))
            addVertice(item1);
        if(!tabel.containsKey(item2))
            addVertice(item2);
        int v=tabel.get(item1);
        int w=tabel.get(item2);
        addEdge(new Edge(v,w,width));
    }

    public int getIndex(Item item){
        if(tabel.containsKey(item))
            return tabel.get(item);
        return -1;
    }

    public Item getItem(int index){
        return list[index];
    }


}
