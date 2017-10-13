/*
 * 
 * 
 */

public class Move implements Comparable<Move>{
    private int initialei;
    private int initialej;
    private int finalei;
    private int finalej;
    private int eneleveri;
    private int enleverj;
    private int heuristiqueValue = 0;


    public Move(int initialei, int initialej, int finalei, int finalej, int eneleveri, int enleverj) {

        this.initialei = initialei;
        this.initialej = initialej;
        this.finalei = finalei;
        this.finalej = finalej;
        this.eneleveri = eneleveri;
        this.enleverj = enleverj;
    }

    public int compareTo(Move other){
        if(heuristiqueValue < other.heuristiqueValue)
            return -1;
        else if(heuristiqueValue > other.heuristiqueValue)
            return 1;
        else
            return 0;
    }

    public int getInitialei() {
        return initialei;
    }

    public int getInitialej() {
        return initialej;
    }

    public int getFinalei() {
        return finalei;
    }

    public int getFinalej() {
        return finalej;
    }

    public int getEnleveri() {
        return eneleveri;
    }

    public int getEnleverj() {
        return enleverj;
    }

    @Override
    public String toString() {
        return "Deplacementij{" +
                "initialei=" + initialei +
                ", initialej=" + initialej +
                ", finalei=" + finalei +
                ", finalej=" + finalej +
                ", eneleveri=" + eneleveri +
                ", enleverj=" + enleverj +
                '}';
    }


    public void setHeuristiqueValue(int heuristiqueValue) {
        this.heuristiqueValue = heuristiqueValue;
    }
}
