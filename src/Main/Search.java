package Main;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.function.ToIntFunction;

public class Search {

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
//	private int Euclidean(int [][] initial)
//	{
//		
//	}
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
	private void solve (int[][] initial, ToIntFunction<int[][]> heuristic_function) {
		
		int[] dx = { 1, 0, -1, 0 };
		int[] dy = { 0, -1, 0, 1 };
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
		
		
		 HashMap<int[][], Integer> mp_g = new HashMap<int[][], Integer>();
		 HashMap<int[][], Integer> mp_h = new HashMap<int[][], Integer>();
		 
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> ( mp_g.get(a.state) + mp_h.get(a.state) ) - (mp_g.get(b.state) + mp_h.get(b.state)));
		int h = heuristic_function.applyAsInt(initial) ;
		Node node = new Node(0,h,initial,x,y,null);
		pq.add(node) ;
		mp_g.put(initial, 0);
		mp_h.put(initial, h);
		while(! pq.isEmpty())
		{
			Node parent = pq.poll() ;
			
			if(parent.h == 0)
			{
				System.out.println("Done");
				printPath(parent) ;
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
					if(mp_g.containsKey(matrix))
					{
						int newCost = h + parent.g ;
						int oldCost =mp_g.get(matrix) + mp_h.get(matrix) ;
						if(oldCost >= newCost)
							continue ;
						else 
						{
							mp_g.put(matrix,parent.g + 1);
							pq.remove(new Node(parent.g + 1,h,matrix,row,col,null));
							pq.add(new Node(parent.g + 1,h,matrix,row,col,parent));
						}
						
					}
					else 
					{
						mp_g.put(matrix,parent.g + 1);
						mp_h.put(matrix, h);
						Node newNode = new Node(parent.g + 1,h,matrix,row,col,parent);
						pq.add(newNode) ;
					}
				}
			}
		}
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		int[][] initial = { {4, 3, 2}, {6, 5, 0}, {7, 8, 1} };
		
		Search s = new Search() ;
		
			s.solve(initial, s.Manhattan) ;

	}

}
