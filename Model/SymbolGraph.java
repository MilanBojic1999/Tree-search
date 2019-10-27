package Model;

import Model.Stanja.Stanje;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class SymbolGraph<Item> extends Graph {

    private HashMap<Item,Integer> tabel;
    private Item[] list;
    private int n;

    public SymbolGraph(int v) {
        super(v);
        System.out.println("Napravljen graf sa "+V()+" čvorova");
        tabel=new HashMap<>();

        list=(Item[])new Object[V()];
        n=0;
    }

    public<S extends Stanje> SymbolGraph(S item){
        this(item.getMaxCombination());
        addVertice((Item)item);
    }

    public int N(){
        return n;
    }

    public void addVertice (Item item){
        if(n==V()) {
            System.out.println("No more space"+n);
            return;
        }
        if(tabel.containsKey(item))
            return;
        //System.out.println(item);
        if(item instanceof Integer){
            tabel.put(item,(Integer)item);
            list[(Integer)item]=item;
            n++;
        }else{
            list[n]=item;
            tabel.put(item,n++);
        }
    }

    public void addEdge(Item item1,Item item2,double width){
        if(!tabel.containsKey(item1)) {
            addVertice(item1);
        }
        if(!tabel.containsKey(item2)) {
            addVertice(item2);
        }
        int v=tabel.get(item1);
        int w=tabel.get(item2);
        //if(!hasEdgeToVertace(w))
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

    public boolean contains(Item item){
        return tabel.containsKey(item);
    }

    public boolean containsAny(List<Item> items){
        return !Collections.disjoint(items,tabel.keySet());
    }


}
