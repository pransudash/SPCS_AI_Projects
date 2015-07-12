
public class ManhattanHeuristic implements AStarHeuristic {
	
	public int getCost(Board state, Board goalState) {
		
		int diffs = 0;
		int dx = 0;
		int dy = 0;
		
		for( int i = 0; i < Board.rows; i++ ) {
			for( int n = 0; n < Board.columns; n++ ) {
				for( int x = 0; x < Board.rows; x++ ) {
					for( int y = 0; y < Board.columns; y++ ) {
						if( state.tiles[i][n] == goalState.tiles[x][y] ) {
							dx = Math.abs(x - i);
							dy = Math.abs(y - n);
							diffs = dx + dy;
						}
					}
				}
			}
		}
		
		return diffs;
		
//		int sum = 0;
//		int currI = 0, currJ = 0;
//		
//		for(int i = 0; i < Board.rows; i++){
//			for(int j = 0; j < Board.columns; j++){
//				currI = 0;
//				currJ = 0;
//				
//				/*
//				 * This algorithm loops through the goal state's tiles for
//				 * each tile in the current initial state. It then finds the
//				 * Manhattan Distance Math.abs((y2 - y1)) + Math.abs((x2 - x1))
//				 * between the two tiles, and adds up the sum of all 
//				 * of the manhattan distances.
//				 */
//				while(state.tiles[i][j] != goalState.tiles[currI][currJ]
//						&& state.tiles[i][j] != -1 && state.tiles[i][j] != 0){
//					if(++currJ == Board.columns){
//						currJ = 0;
//						if(++currI == Board.rows){
//							return -1;
//						}
//					}
//				}
//				
//				sum += Math.abs((i - currI)) + Math.abs((j - currJ));
//			}
//		}
//
//		return sum;
	}
}

