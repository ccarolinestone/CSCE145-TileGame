import java.util.Scanner;
public class TileGame {
	
	
	public static char[][] placeCharacters(char[][] g, int pX, int pY, int eX, int eY, int kX, int kY, int dX, int dY, boolean hasKey) {
		//need to make a new copy of the grid
		char[][] gridCopy = new char[g.length][g[0].length];
		
		for (int i=0; i<gridCopy.length; i++) {
			for(int j=0; j<gridCopy[i].length; j++) {
				gridCopy[i][j] = g[i][j];
			}
		}
		
		
	//first [] is the rows (which is x), second [] is the columns (which is x)
		gridCopy[pY][pX] = 'p';
		gridCopy[eY][eX] = 'e';
		gridCopy[dY][dX]='d';
		
		if(!hasKey) {
			gridCopy[kY][kX]='k';
		}
		return gridCopy;
	}

	//this method will not depend on info in the class - can be static
	//the parameter that is passed in is the grid
	//this array (complex) is passed by reference (the identifier for the array points to the contents memory address, it modifies original array
	//this method alters the content of the original array because it has the memory address of the original array
	//primitives are passed by value 
	public static void generateWorld(char[][] g) {
		for (int i=0; i<g.length; i++) {
			for (int j=0; j<g[i].length;j++) {
				if (i==0 || j==0 || i==g.length-1 || j == g[i].length-1) {
				g[i][j] = '#';
				}
				else {
					g[i][j]='.';
				}
			}
		}
	}
	
	
	//this method will print out the array
	//do I need info outside of the method to use this method?
	//this method will need the array as the parameter
	public static void printWorld(char[][] g) {
		for (int i=0; i<g.length; i++) {
			for (int j=0; j<g[i].length;j++) {
				System.out.print(g[i][j]);
			}
			System.out.println();
		}
	}
	public static void main(String[] args) {
		//Plan: define x by x grid (square) [this is our playing field]
		//my title list: #: wall, .= empty, p: player, etc.
		//Controls: WASD
		int playerX = 5;
		int playerY = 5;
		int gridSize = 10;
		
		int nextPlayerX = playerX;
		int nextPlayerY = playerY;
		
		int enemyX=3;
		int enemyY=3;
		
		int nextEnemyX=enemyX;
		int nextEnemyY=enemyY;
		
		int keyX=1;
		int keyY =1;
		
		boolean hasKey=false;
		
		int doorX=8;
		int doorY=8;
		
		boolean enemyIsMovingLeft = false;
		
		char[][] grid = new char[gridSize][gridSize];
		Scanner keyboard = new Scanner(System.in);
		
		//will need to generate a grid multiple times, makes sense to make a generate grid method

		generateWorld(grid);
		
		
		boolean isPlaying = true;
		
		while(isPlaying) {
			
			nextPlayerX = playerX;
			nextPlayerY = playerY;
			
			char[][] newGrid = placeCharacters(grid, playerX, playerY, enemyX, enemyY, keyX, keyY, doorX, doorY, hasKey);
			
			//need method to put player on the grid
			printWorld(newGrid);
			
			System.out.print("Input: ");
			String input = keyboard.nextLine();
			
			if (input.equals("w")) {
				//move player up
				nextPlayerY--;
			}
			
			else if (input.equals("a")) {
				//move player left
				nextPlayerX--;
			}
			
			else if(input.equals("s")) {
				//movie player down
				nextPlayerY++;
			}
			
			else if(input.equals("d")) {
				//move player right
				nextPlayerX++;
			}
			
			if(grid[nextPlayerY][nextPlayerX] != '#') {
				playerX =nextPlayerX;
				playerY=nextPlayerY;
			}
			
			if(newGrid[playerY][playerX] == 'k') {
				hasKey =true; 
			}
			
			
			if(newGrid[playerY][playerX] == 'd' && hasKey) {
				System.out.println("You win!");
				//isPlaying=false;
				break;
			}
			
			if(enemyIsMovingLeft) {
				nextEnemyX--;
			}
			else {
				nextEnemyX++;
			}
			
			if(newGrid[nextEnemyY][nextEnemyX] != '#') {
				enemyX=nextEnemyX;
				enemyY=nextEnemyY;
			}
			
			else {
				//switching the boolean so you don't have to write condition
				enemyIsMovingLeft= !enemyIsMovingLeft;
			}
			if (newGrid[nextPlayerY][nextPlayerX]=='e') {
				isPlaying= false;
			}
			
			//condition for exit here...
			//isPlaying=false;
			
			if(!isPlaying) {
			System.out.println("Game Over");
			}
		}
	}

}
