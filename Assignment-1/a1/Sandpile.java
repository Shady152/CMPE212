package a1;
import a1.Index2;
import java.lang.Math;

public class Sandpile {

	/**
	 * Print a 2-dimensional array of cells using least 3 spaces for each value.
	 * Values for each row of the array appear on a single line, and each row
	 * appears on its own line.
	 * 
	 * @param cells a two-dimensional array
	 * @throws IllegalArgumentException if the specified array has a dimension equal
	 *                                  to zero
	 */
	public static void printCells(int[][] cells) {
		int rows = cells.length;
		if (rows <= 0) {
			throw new IllegalArgumentException("rows <= 0");
		}

		int cols = cells[0].length;
		if (cols <= 0) {
			throw new IllegalArgumentException("cols <= 0");
		}
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int val = cells[r][c];
				System.out.printf("%3d", val);
			}
			System.out.println();
		}
	}

	// TRANSLATE THE REMAINING C FUNCTIONS INTO JAVA METHODS HERE
	
	public static void init(int grains, int rows, int cols, int cells[][]) 
	{
		if (rows <= 0) 
			throw new IllegalArgumentException("rows <= 0");
		if (cols <= 0) 
			throw new IllegalArgumentException("cols <= 0");
		if (grains < 0)
			throw new IllegalArgumentException("grains < 0");
		
		for (int r = 0; r < rows; r++) 
		{
			for (int c = 0; c < cols; c++) 
				cells[r][c] = 0;
		}
		cells[rows / 2][cols / 2] = grains;
	}//init()

	public static Index2 firstToTopple(int rows, int cols, int cells[][])
	{
		if (rows <= 0) 
			throw new IllegalArgumentException("rows <= 0");
		if (cols <= 0) 
			throw new IllegalArgumentException("cols <= 0");
		
	    Index2 idx = new Index2(0, 0);
	    // search for a cell 
		for (int r = 0; r < rows; r++) 
		{
			idx.row = r;
			for (int c = 0; c < cols; c++) 
			{
				idx.col = c;
				int val = cells[r][c];
				if (val >= 4) 
					return idx;
			}
		}
	    idx.row = -1;
	    idx.col = -1;
		return idx;
	}//firstToTopple()
	
	public static void topple(Index2 i, int rows, int cols, int cells[][]) 
	{
		if (rows <= 0) 
			throw new IllegalArgumentException("rows <= 0");
		if (cols <= 0) 
			throw new IllegalArgumentException("cols <= 0");
		
	    int grains = cells[i.row][i.col]; 
	    
	    if (grains < 4)
			throw new IllegalArgumentException("grains < 4");
	    
	    // remove 4 grains from the cell
		cells[i.row][i.col] -= 4;
		// removed grains go into neighboring cells
		//
		// move one grain north (if possible)
		if (i.row > 0) 
			cells[i.row - 1][i.col]++;
		// move one grain east right (if possible)
		if (i.col < cols - 1)
			cells[i.row][i.col + 1]++;
		// move one grain south (if possible)
		if (i.row < rows - 1) 
			cells[i.row + 1][i.col]++;
		// move one grain west (if possible)
		if (i.col > 0) 
			cells[i.row][i.col - 1]++;
	}//topple()

	public static int degree(Index2 i, int[][] cells)
	{
		//Here we do the exception handling ---##
		if (i.row < 0) //Check that the we have a valid row index
			throw new IllegalArgumentException("Invalid row index, i.row < 0");
		if (i.col < 0) //Check that the we have a valid column index
			throw new IllegalArgumentException("Invalid column index, i.col < 0");
		int rows = cells.length; //Get the row size
		if (rows <= 0) //Check that the we have a valid row size
			throw new IllegalArgumentException("Invalid size, rows <= 0");
		int cols = cells[0].length; //Get the column size
		if (cols <= 0) //Check that the we have a valid column size
			throw new IllegalArgumentException("Invalid size, cols <= 0");
		//-------------------------------------##
		
		//This if statement passes the cells that are on the edges and corners
		if (i.row == 0 || i.row == cells.length - 1 || i.col == 0 || i.col == cells.length - 1)
		{	
			//This if statement returns the degree for the cells that are in the corners
			if ((i.row == 0 && (i.col == 0 || i.col == cells.length - 1)) ||  (i.row == cells.length - 1 && (i.col == 0 || i.col == cells.length - 1)))
				return 2;
			//This if statement return the degree for the non-corner edge cells
			if (i.row != 0 || i.row != cells.length - 1 || i.col != 0 || i.col != cells.length - 1)
				return 3;
		}
		else //This returns the degree for the inner non-edge and non-corner cells
			return 4;
		return -1; //This is in the case the the function fails since there can't be a degree of -1
	}//degree()
	
	/**
	 * Creates a 15x15 sandpile simulation starting with 2 to-the-power 8 grains of
	 * sand on the center cell. The starting configuration of cells is printed to
	 * standard output and then the simulation is run until all cells reach a stable
	 * state (have fewer than 4 grains of sand). The stable configuration of cells
	 * is printed to standard output.
	 * 
	 * <p>
	 * Finally, an image of the stable configuration is shown.
	 * 
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		int[][] cells = new int[15][15];
		// FINISH TRANSLATING THE main FUNCTION HERE
		final int ROWS = cells.length;
		final int COLS = ROWS;
		init((int) Math.pow(2, 8), ROWS, COLS, cells);
	    System.out.println("Original cells");
	    printCells(cells);
	    Index2 i = firstToTopple(ROWS, COLS, cells);
	    while (i.row >= 0) 
	    {
	        topple(i, ROWS, COLS, cells);
	        i = firstToTopple(ROWS, COLS, cells);
	    }
	    System.out.println("\nFinal cells\n");
	    printCells(cells);
		// THE NEXT TWO LINES SHOULD BE THE LAST LINES OF THE METHOD 
		// show an image of the stable configuration
		SandpileViewer.draw(cells);
		
		Index2 a = new Index2(1,2);
		Index2 b = new Index2(2,3);
		System.out.println(a.equals(b));
		System.out.println(a.eqs(b));
	}
}

