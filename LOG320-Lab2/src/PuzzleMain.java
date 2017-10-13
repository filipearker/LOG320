
public class PuzzleMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GameEngine gE = new GameEngine("test.puzzle"); /*args[0]*/

	        System.out.print("Jeu initial :\n");
	        gE.printPuzzle();
	        System.out.print("\n");

	        double startTime = System.currentTimeMillis();
	        double elaspedTime = 0;

	        if(gE.resoudrePuzzle()){
	            elaspedTime = (System.currentTimeMillis() - startTime)/1000;
	        }else{
	            elaspedTime = (System.currentTimeMillis() - startTime)/1000;
	            System.out.print("\nJeu insolvable\n");
	        }

	        System.out.print("Nombre de noeuds parcourus: "+gE.getNodeCount()+"\n");
	        System.out.print("Temps d'execution : " + elaspedTime + " secondes\n");
	        System.out.print("Jeu final :\n");
	        gE.printPuzzle();

	        System.out.print("Solution :\n");

	        for(Move desplacement : gE.getDeplacementList()){
	            System.out.print("De ("+desplacement.getInitialej()+","+desplacement.getInitialei()+") ");
	            System.out.print("à ("+desplacement.getFinalej()+","+desplacement.getFinalei()+")\n");
	        }
	    
	}
	
}
