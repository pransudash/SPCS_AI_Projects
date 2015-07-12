import java.util.ArrayList;

public class Board implements Comparable<Board> {
	
	public static int rows = 5;
	public static int columns = 3;
	private Board parent = null; /* only initial board's parent is null */
	public int[][] tiles;
	public int fn = 0;
	public int gn = 0;

	public Board(int[][] cells)                 //Populate states
	{
		tiles = new int[rows][columns];
		for (int i = 0 ;i<rows; i++)
			for (int j = 0; j<columns; j++)
			{
				tiles[i][j] = cells[i][j];
			}
	}
	
	public boolean equals(Object x)         	//Can be used for repeated state checking
	{
		Board another = (Board)x;
		if (columns != another.columns || rows != another.rows) return false;
		for (int i = 0; i<rows; i++)
			for (int j = 0; j<columns; j++)
				if (tiles[i][j] != another.tiles[i][j])
					return false;
		return true;
	}
	
	public ArrayList<Board> getSuccessors( Board board )     //Use cyclic ordering for expanding nodes - Right, Down, Left, Up
	{
		// TODO
		ArrayList<Board> successors = new ArrayList<Board>();
		
		Board right = new Board(this.tiles);
		Board down = new Board(this.tiles);
		Board left = new Board(this.tiles);
		Board up = new Board(this.tiles);
		
		for( int i = 0; i < Board.rows; i++ ) {
			for( int j = 0; j < Board.columns; j++ ) {
				if( right.tiles[i][j] == 0 ) {

					// Right
					if( j+1 < columns && right.tiles[i][j+1] != -1 ) {
						right.tiles[i][j] = right.tiles[i][j+1];
						right.tiles[i][j+1] = 0;
						successors.add(right);
						right.setParent(board);
					}
					
					// Down
					if( down.tiles[i][j] > -1 && i+1 < rows && down.tiles[i+1][j] != -1 ) {
						down.tiles[i][j] = down.tiles[i+1][j];
						down.tiles[i+1][j] = 0;
						successors.add(down);
						down.setParent(board);
					}
					
					// Left
					if( left.tiles[i][j] > -1 && j-1 >= 0 && left.tiles[i][j-1] != -1){
						left.tiles[i][j] = left.tiles[i][j-1];
						left.tiles[i][j-1] = 0;
						successors.add(left);
						left.setParent(board);
					}
					
					// Up
					if( up.tiles[i][j] > -1 && i-1 >= 0 && up.tiles[i-1][j] != -1){
						up.tiles[i][j] = up.tiles[i-1][j];
						up.tiles[i-1][j] = 0;
						successors.add(up);
						up.setParent(board);
					}		
					
					return successors;
				}
			}
		}
		return null;
	}
	
	public void print()
	{
		for (int i = 0; i < rows; i++)
		{
			for (int j = 0; j < columns; j++)
			{
				if (j > 0) System.out.print("\t");
				System.out.print(tiles[i][j]);
			}
			System.out.println();
		}
	}
	
	public void setParent(Board parentBoard)
	{
		parent = parentBoard;
	}
	
	public Board getParent()
	{
		return parent;
	}

	@Override
	public int compareTo(Board b) {
		if( this.fn < b.fn )
			return -1;
		else if( this.fn > b.fn )
			return 1;
		return 0;
	}
	
}

//class BoardComparator {
//	
//	public int compareTo( Board b ) {
//		
//		return 0;
//	}
//}
