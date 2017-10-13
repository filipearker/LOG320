import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;


public class GameEngine {


    private int[][] puzzleTable = new int[7][7];

    private int col = 0;
    private int row = 0;
    private int node  = 0;
    private int nombreRestant = 0;
    private LinkedList<Move> deplacementCorner = new LinkedList<>();
    private LinkedList<Move> deplacementList = new LinkedList<>();


    public GameEngine(String puzzlePath){

        getPuzzleFile(puzzlePath);
    }

    public LinkedList<Move> getDeplacementList() {
        return deplacementList;
    }

    private void getPuzzleFile(String puzzlePath){


        try {
            Scanner list = new Scanner(new FileReader(puzzlePath));
            char[] currentLine;
            while (list.hasNextLine())
            {
                currentLine = list.nextLine().toCharArray();
                col = 0;
                for(char carac : currentLine)
                {
                    puzzleTable[row][col] = Character.getNumericValue(carac);
                    if(puzzleTable[row][col] == 1)
                        nombreRestant++;
                    col++;
                }

                row ++;
            }

            list.close();


        System.out.print("te");
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e.toString());
        }

    }

    public int getNodeCount(){
        return node;
    }

    public void printPuzzle(){
        String strPuzzle = "";
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                strPuzzle += puzzleTable[i][j];
            }
            strPuzzle +="\n";
        }

        System.out.print(strPuzzle);
    }

    public List<Move> coupsValides(int i, int j){
        List<Move> list = new ArrayList<>();
        if(puzzleTable[i][j] != 1)
            return list;

        if(i+2 < row && puzzleTable[i+2][j] == 2 && puzzleTable[i+1][j] == 1 )
            list.add(new Move(i,j , i+2,j, i+1,j));

        if(i-2 >= 0 && i-2 < row && puzzleTable[i-2][j] == 2 && puzzleTable[i-1][j] == 1)
            list.add(new Move(i,j , i-2,j, i-1,j));

        if(j+2 < col && puzzleTable[i][j+2] == 2 && puzzleTable[i][j+1] == 1)
            list.add(new Move(i,j , i,j+2, i,j+1));

        if(j-2 >= 0 && j-2 < col && puzzleTable[i][j-2] == 2 && puzzleTable[i][j-1] == 1)
            list.add(new Move(i,j , i,j-2, i,j-1));

        return list;

    }



    public void deplacement(Move position){

        nombreRestant--;
        //la case initiale = 2 (vide)
        puzzleTable[position.getInitialei()][position.getInitialej()] = 2;

        //la case que l'on passe au dessus = 2 (vide)
        puzzleTable[position.getEnleveri()][position.getEnleverj()] = 2;

        //la case destination = 1 (non vide)
        puzzleTable[position.getFinalei()][position.getFinalej()] = 1;

        deplacementList.add(position);

    }

    public void annulerDeplacement(Move position){

        nombreRestant++;

        //la case initiale = 1 (non vide)
        puzzleTable[position.getInitialei()][position.getInitialej()] = 1;

        //la case que l'on passe au dessus = 1 (non vide)
        puzzleTable[position.getEnleveri()][position.getEnleverj()] = 1;

        //la case destination = 2 ( vide)
        puzzleTable[position.getFinalei()][position.getFinalej()] = 2;

        deplacementList.pollLast();

    }

    public boolean resoudrePuzzle(){

        node++;
        if(nombreRestant == 1)
            return true;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {

                List<Move> coups = coupsValides(i, j);
                for (Move position : coups) {
                    deplacement(position);
                    position.setHeuristiqueValue(heuristique());
                    annulerDeplacement(position);

                }

                Collections.sort(coups);

                for (Move position : coups) {

                    deplacement(position);

                    if(resoudrePuzzle()) {
                        return true;
                    }else {
                        annulerDeplacement(position);
                    }

                }
            }
        }

        return false;
    }

    /**
     * inspiré de Sylvain togni
     * http://c.developpez.com/defis/2-Solitaire/
     * @return
     */
    private int heuristique(){

        int s1 = 0;
        int si = 0;
        int sj = 0;
        int sij2 = 0;
        for (int i = 0; i < row; i++){
            for (int j = 0; j < col; j++){
                if(puzzleTable[i][j] == 1){
                    s1 += 1;
                    si += i;
                    sj += j;
                    sij2 += i*i + j*j;
                }
            }

        }
        return sij2*s1 - si*si - sj*sj;
    }

}
