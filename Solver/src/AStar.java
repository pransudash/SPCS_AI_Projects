import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.PriorityQueue;
public class AStar {
	 
	private Board initialState;
	private Board goalState;
	private AStarHeuristic heuristic;

	public AStar(Board initial, Board goal, AStarHeuristic heur)
	{
		initialState = initial;
		goalState = goal;
		heuristic = heur;
	}

	public void search()
	{
      	/* Declare and initialize Frontier and Explored data structures */ 
		/* Put start node in Fringe list Frontier */
		PriorityQueue<Board> Frontier = new PriorityQueue<Board>();
		ArrayList<Board> Explored = new ArrayList<Board>();
		
		Frontier.add(initialState);
		
		while (!Frontier.isEmpty())
		{
			/* Remove from Frontier list the node n for which f(n) is minimum */
			/* Add n to Explored list*/
			Board n = Frontier.poll();
			Explored.add(n);
			
			if (n.equals(goalState))
			{
				/* Print the solution path and other required information */
				/* Trace the solution path from goal state to initial state using getParent() function*/
				ArrayList<Board> parents = new ArrayList<Board>();
				parents.add(n);
				int costFromInitial = 0;

				//Add the parent states to a buffer ArrayList
				while(n.getParent() != null){
					parents.add(n.getParent());
					n = n.getParent();
					costFromInitial++;
				}

				//Loop back through the parents in reverse order
				//which displays our effective path from initial->goal
				for(int i = parents.size()-1; i >= 0; i--){
					parents.get(i).print();
					System.out.println();
				}

				System.out.println(costFromInitial);
				System.out.println(Explored.size());

				return; //goal found! :) :) :)

			}

			ArrayList<Board> successors = n.getSuccessors( initialState );
			
			for (int i = 0; i < successors.size(); i++)
			{
				/* if n1 is not already in either Frontier or Explored
			       Compute h(n1), g(n1) = g(n)+c(n, n1), f(n1)=g(n1)+h(n1), place n1 in Frontier
			   	   if n1 is already in either Frontier or Explored
			       if g(n1) is lower for the newly generated n1
			       Replace existing n1 with newly generated g(n1), h(n1), set parent of n1 to n
			       if n1 is in Explored list
			       Move n1 from Explored to Frontier list */
				
				Board n1 = successors.get(i);				
				int gn1 = 0;
				Board save = n1;
				while(save != null){
					gn1++;
					save = save.getParent();
				}
				
				gn1 = n.gn + 1; ;
				int hn1 = heuristic.getCost(n1, goalState);
				int fn1 = gn1 + hn1;

				if(!Explored.contains(n1) && !Frontier.contains(n1)) {
					n1.fn = fn1;
					n1.gn = gn1;
					n1.setParent(n);

					Frontier.offer(n1);
				}

				else if(Frontier.contains(n1)) {
					Iterator<Board> iter = Frontier.iterator();
					Board tempBoard = n1;
					
					while(iter.hasNext()){
						tempBoard = iter.next();
						if(tempBoard.equals(n1)){
							n1 = tempBoard;
							break;
						}
					}
				}
				
				else if(Explored.contains(n1)) {
					Iterator<Board> itr = Explored.iterator();
					Board tempBoard = n1;

					while(itr.hasNext()) {
						tempBoard = itr.next();
						if(tempBoard.equals(n1)){
							n1 = tempBoard;
							break;
						}
					}
				}

				if(n1.gn > gn1) {
					Frontier.remove(n1);
					Explored.remove(n1);

					n1.fn = fn1;
					n1.gn = gn1;
					n1.setParent(n);

					if(Explored.contains(n1)) {
						Explored.remove(n1);
					}

					Frontier.add(n1);


				}
			}
		}
		System.out.println("No Solution");
	}

}
