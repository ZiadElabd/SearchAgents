package Main;

import java.util.Arrays;

public class Node implements Comparable<Node> {
	
	//cost and heuristic
	public int g , h  ;
	//coordinates of blank tile 
	public int x,y ;
	
	Node Parent ;
	
	public int[][] state = new int[3][3] ;
	
	public Node(int g, int h , int[][] state, int x, int y, Node p)
	{
		this.g = g ;
		this.h = h ;
		this.x = x ;
		this.y = y ;
		this.Parent = p ;
		this.state = state ;
	}

	@Override
	public int compareTo(Node o) {
		if (Arrays.equals(this.state, o.state))
			return 0;
		else if ((this.g + this.h) > (o.g + o.h))
			return 1;
		else
			return -1;
	}
}
