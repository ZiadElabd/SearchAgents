package Main;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class Search {

	int[] dx = { 1, 0, -1, 0 };
	int[] dy = { 0, -1, 0, 1 };

	private boolean isSolvable(int[][] initial)
	{
		Integer[] arr = new Integer[9];
		int k=0 ;
		for(int i=0 ;i<3 ;i++)
			for(int j=0 ;j<3 ;j++)
				arr[k++] = initial[i][j];
		
		int cnt=0 ;
		for(int i=0 ;i<9 ; i++)
			for(int j=i+1 ;j<9 ;j++)
				if(arr[i]!=0 && arr[j]!=0 && arr[i]>arr[j])
					cnt++ ;
		return cnt%2==0 ;
	}
	public ToIntFunction<int[][]> Euclidean =  initial ->{
		int goalX , goalY ;
		int h=0 ;
		for(int i=0 ;i<3 ;i++)
		{
			for(int j=0 ;j<3 ;j++)
			{
				int tile = initial[i][j] ;
				if(tile == 0)
					continue ;
				goalX = tile / 3 ;
				goalY = tile % 3 ;

				h += Math.sqrt((goalX-i) * (goalX-i) + (goalY-j) * (goalY-j));
			}
		}
		return h ;
	};

	public ToIntFunction<int[][]> Manhattan =  initial ->{
		int goalX , goalY ;
		int h=0 ;
		for(int i=0 ;i<3 ;i++)
		{
			for(int j=0 ;j<3 ;j++)
			{
				int tile = initial[i][j] ;
				if(tile == 0)
					continue ;
				goalX = tile / 3 ;
				goalY = tile % 3 ;

				h += Math.abs(goalX-i) + Math.abs(goalY-j) ;
			}
		}
		return h ;
	};
	
	private boolean valid(int i ,int j)
	{
		return (i>=0 && i<3 && j>=0 && j<3) ;
	}
	
	private static void printPath(Node node)
	{
		if(node.Parent != null)
			printPath(node.Parent) ;
		for(int i=0 ;i<3 ;i++)
		{
			for(int j=0 ;j<3 ;j++)
			{
				System.out.print(node.state[i][j]);
			}
		}
		System.out.println();
	}
	public void solve (int[][] initial, ToIntFunction<int[][]> heuristic_function) {
		
		if(!this.isSolvable(initial))
		{
			System.out.println("Un Solvable") ;
			return ;
		}
		
		long startTime = System.currentTimeMillis() ;
		//to get coordinates of 0 tile 
		int x=0,y=0 ;
		for(int i=0 ;i<3 ;i++)
			for(int j=0 ;j<3 ;j++)
				if(initial[i][j] == 0)
				{
					x=i;
					y=j ;
					break ;
				}
		 int Depth = 0 ,NodesExpanded = 0 ;
		PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> (a.g + a.h) - (b.g + b.h));
		int h = heuristic_function.applyAsInt(initial) ;
		Node node = new Node(0,h,initial,x,y,null);
		pq.add(node) ;
		while(! pq.isEmpty())
		{
			Node parent = pq.poll() ;
			NodesExpanded++ ;
			Depth = Math.max(parent.g, Depth) ;
			if(parent.h == 0)
			{
				finish(parent, startTime, NodesExpanded, Depth);
				return ;
			}
			for(int i=0 ;i<4 ;i++)
			{
				int row = parent.x + dx[i] ;
				int col = parent.y + dy[i] ;
				
				if(valid(row,col))
				{
					int[][] matrix = new int[3][3] ;
					
					for(int k=0 ;k<3 ;k++)
						for(int t=0 ;t<3 ;t++)
							matrix[k][t] = parent.state[k][t] ;

					//swap
					matrix[parent.x][parent.y] = matrix[row][col] ;
					matrix[row][col] = 0 ;
					
					h = heuristic_function.applyAsInt(matrix) ;
							
					Node newNode = new Node(parent.g + 1,h,matrix,row,col,parent);
					pq.add(newNode) ;
				}
			}
		}
	}

	private final void finish(Node parent, long startTime, int nodesExpanded, int Depth){
		System.out.println("The Path ");
		printPath(parent) ;
		System.out.println("################################################");
		System.out.println("Total steps to get to the goal = " + parent.g + " step");
		System.out.println("################################################");
		System.out.println("Time taken : " + (System.currentTimeMillis() - startTime) + "ms");
		System.out.println("################################################");
		System.out.println("Nodes Expanded : " + nodesExpanded);
		System.out.println("################################################");
		System.out.println("Search depth : " + Depth);
		System.out.println("################################################");
	}

}
