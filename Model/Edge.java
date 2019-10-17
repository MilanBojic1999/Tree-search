package Model;

public class Edge implements Comparable<Edge>{

    private int v;
    private int w;
    private double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public int ether(){
        return v;
    }

    public boolean edgeOf(int v,int w){
        if(this.v==v && this.w==w)
            return true;
        if(this.w==v && this.v==w)
            return true;

        return false;
    }

    public int other(int i){
        if (i==v) return w;
        if(i==w) return v;
        return -1;
    }

    public double weight(){
        return weight;
    }

    @Override
    public int hashCode() {
        final int prime=53;
        int hash=(v*10+w)*prime;
        hash=hash+(int)(weight*prime);
        return hash;
    }

    @Override
    public String toString() {
        return v+"<- "+weight+" ->"+w;
    }

    @Override
    public int compareTo(Edge o) {
        return (int)(this.weight-o.weight);
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj) return true;
        if(obj == null) return false;
        if(!(obj instanceof Edge)) return false;
        Edge other=(Edge) obj;
        return (this.w==other.w) && (this.v==other.v);
    }

    public static void main(String[] args) {
        Edge edge=new Edge(1,2,0.53);
        System.out.println(edge.toString());
        System.out.println(edge.hashCode());
    }
}
