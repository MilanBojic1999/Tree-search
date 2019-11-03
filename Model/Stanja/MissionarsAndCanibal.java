package Model.Stanja;

import java.util.ArrayList;
import java.util.List;

public class MissionarsAndCanibal implements Stanje {

    private int misionariL;
    private int ljudozderiL;
    private boolean camasL;

    public MissionarsAndCanibal(int misionariL, int ljudozderiL,
                                boolean camasL) {
        this.misionariL = misionariL;
        this.ljudozderiL = ljudozderiL;
        this.camasL = camasL;
    }

    @Override
    public int getMaxCombination() {
        return 32;
    }

    @Override
    public List<Stanje> generisiStanja() {
        List<Stanje> list=new ArrayList<>();
        int misD=3-misionariL;
        int ljuD=3-ljudozderiL;

        if(camasL){
            for(int m=0; m<=misionariL;m++){
                for(int l=0;l<=ljudozderiL && m+l<=2;l++){
                    if(m+l>0){
                        if((m>=l || m==0) && (misionariL - m >= ljudozderiL-l || misionariL - m == 0) &&
                                (misD+m >=ljuD+l || misD+m==0)){
                            list.add(new MissionarsAndCanibal(misionariL-m,ljudozderiL-l,false));
                        }
                    }
                }
            }
        }else {
            for(int m=0; m<=misD;m++) {
                for (int l = 0; l <= ljuD && m+l <= 2; l++) {
                    if( m+l >0){
                        if((m>=l || m==0) && (misionariL+m >= ljudozderiL+l || misionariL+m == 0) &&
                                (misD - m >=ljuD-l || misD-m ==0)){
                            list.add(new MissionarsAndCanibal(misionariL+m,ljudozderiL+l,true));
                        }
                    }
                }
            }
        }
        return list;
    }

    @Override
    public int heuristic(Stanje stanje) {
        return compareTo(stanje);
    }

    @Override
    public int heuristic() {
        return 0;
    }

    @Override
    public boolean isGoal(Stanje stanje) {
        return this.equals(stanje);
    }

    @Override
    public int hashCode() {
        return (misionariL*100+ljudozderiL*10+((camasL) ? 1 : -1));
    }

    @Override
    public boolean equals(Object obj) {
        if(obj==null)
            return false;
        if(!(obj instanceof MissionarsAndCanibal))
            return false;
        MissionarsAndCanibal mac=(MissionarsAndCanibal) obj;
        return (misionariL==mac.misionariL) && (ljudozderiL==mac.ljudozderiL) &&
                (camasL==mac.camasL);
    }

    @Override
    public int compareTo(Stanje o) {
        if(!(o instanceof MissionarsAndCanibal))
            return -1;
        MissionarsAndCanibal mac=(MissionarsAndCanibal) o;
        return Math.abs(this.ljudozderiL-mac.ljudozderiL)+Math.abs(this.misionariL-((MissionarsAndCanibal) o).misionariL)
                + ((camasL) ? 1 : (mac.camasL) ? 0 : -1);
    }

    @Override
    public String toString() {
        return "["+misionariL+ljudozderiL+((camasL) ? 1 : 0 )+"]";
    }
}
