import java.util.ArrayList;
import java.util.List;

public class PuzzleBox {
	private int [][] puzzle;
	
	public enum direction{
		UP, DOWN, LEFT, RIGHT
	}
	
	public PuzzleBox(int[][] puzzle) {
		this.puzzle = puzzle;
	}
	
	public void move(int x, int y, direction mouvement) {
		switch (mouvement) {
		case UP:
			puzzle[x - 1][y] = 2;
			puzzle[x - 2][y] = 1;
			break;
		case DOWN:
			puzzle[x + 1][y] = 2;
			puzzle[x + 2][y] = 1;	
			break;
		case LEFT:
			puzzle[x][y - 1] = 2;
			puzzle[x][y - 2] = 1;
			break;
		case RIGHT:
			puzzle[x][y + 1] = 2;
			puzzle[x][y + 2] = 1;
			break;
		}
		puzzle[x][y] = 2;
	}
	
	public void undoMouvement(int currentX, int currentY, direction lastMouvement) {
		switch (lastMouvement) {
		case UP:
			puzzle[currentX + 1][currentY] = 1;
			puzzle[currentX + 2][currentY] = 2;
			break;
		case DOWN:
			puzzle[currentX - 1][currentY] = 1;
			puzzle[currentX - 2][currentY] = 2;	
			break;
		case LEFT:
			puzzle[currentX][currentY + 1] = 1;
			puzzle[currentX][currentY + 2] = 2;
			break;
		case RIGHT:
			puzzle[currentX][currentY - 1] = 1;
			puzzle[currentX][currentY - 2] = 2;
			break;
		}
		puzzle[currentX][currentY] = 1;
	}
	
	public List<direction> legalMoves(int x, int y) {
		if(puzzle[x][y] != 1) {
			return null;
		}
		List<direction> legalMoves = new ArrayList<direction>();
		if(isMoveLegal(x - 2, y, direction.UP)) {
			legalMoves.add(direction.UP);
		}
		if(isMoveLegal(x + 2, y, direction.DOWN)) {
			legalMoves.add(direction.DOWN);
		}
		if(isMoveLegal(x, y - 2, direction.LEFT)) {
			legalMoves.add(direction.LEFT);
		}
		if(isMoveLegal(x + 2, y, direction.RIGHT)) {
			legalMoves.add(direction.RIGHT);
		}
		return legalMoves;
	}
	
	private boolean isMoveLegal(int finalX, int finalY, direction mouvement){
		if(finalX < 0 || finalY < 0 || finalX > 6 || finalY > 6) {
			return false;
		}
		int tempTige = 0;
		switch (mouvement) {
		case UP:
			tempTige = puzzle[finalX + 1][finalY];
			break;
		case DOWN:
			tempTige = puzzle[finalX - 1][finalY];			
			break;
		case LEFT:
			tempTige = puzzle[finalX][finalY + 1];
			break;
		case RIGHT:
			tempTige = puzzle[finalX][finalY - 1];
			break;
		}
		if(tempTige == 1 && puzzle[finalX][finalY] == 2) {
			return true;
		}
		return false;
	}
}
