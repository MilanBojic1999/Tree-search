package Model.Stanja;

import java.util.ArrayList;
import java.util.List;

public class ThreeDices implements Stanje {

    private int firstDice;
    private int secondDice;
    private int thirdDice;

    public ThreeDices(int firstDice, int secondDice, int thirdDice) {
        this.firstDice = firstDice;
        this.secondDice = secondDice;
        this.thirdDice = thirdDice;
    }

    @Override
    public int getMaxCombination() {
        return 217;
    }

    @Override
    public int hashCode() {
        return (firstDice*100+secondDice*10+thirdDice);
    }

    @Override
    public boolean equals(Object obj) {
        return hashCode()==obj.hashCode();
    }

    @Override
    public List<Stanje> generisiStanja() {
        List<Stanje> list=new ArrayList<>();
        for(int num:rotate(firstDice)){
            ThreeDices nnt=new ThreeDices(num,secondDice,thirdDice);
            list.add(nnt);
            if(isGoal(nnt))
                return list;
        }

        for(int num:rotate(secondDice)){
            ThreeDices nnt=new ThreeDices(firstDice,num,thirdDice);
            list.add(nnt);
            if(isGoal(nnt))
                return list;
        }

        for(int num:rotate(thirdDice)){
            ThreeDices nnt=new ThreeDices(firstDice,secondDice,num);
            list.add(nnt);
            if(isGoal(nnt))
                return list;
        }


        return list;

    }

    private int[] rotate(int num){
        int[] niz=new int[4];
        int j=0;
        for(int i=1;i<7;i++){
            if(num!=i && num+i!=7)
                niz[j++]=i;
        }
        return niz;
    }


    @Override
    public boolean isGoal(Stanje stanje) {
        if(!(stanje instanceof ThreeDices))
            return false;
        ThreeDices td=(ThreeDices) stanje;

        for(int i=1;i<7;i++){
            if(td.firstDice==i && td.secondDice==i && td.thirdDice==i)
                return true;
        }

        return false;
    }

    @Override
    public int heuristic() {
        int num=50;
        int temp=0;
        for(int i=1;i<7;i++){
            temp=(int)Math.sqrt(Math.pow(firstDice-i,2)+Math.pow(secondDice-i,2)+Math.pow(thirdDice-i,2));
            if(temp<num)
                num=temp;
        }
        return num;
    }

    @Override
    public int heuristic(Stanje stanje) {
        return compareTo(stanje);
    }

    @Override
    public int compareTo(Stanje o) {
        if(!(o instanceof ThreeDices))
            return -1;
        ThreeDices td=(ThreeDices) o;
        return (int)Math.sqrt(Math.pow(firstDice-td.firstDice,2)+Math.pow(secondDice-td.secondDice,2)+Math.pow(thirdDice-td.thirdDice,2));
    }

    @Override
    public String toString() {
        return "<"+firstDice+","+secondDice+","+thirdDice+">";
    }
}
