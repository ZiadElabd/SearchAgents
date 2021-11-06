package Main;
import java.util.PriorityQueue;

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
	private int Manhattan(int [][] initial) {
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
	}
	
	private boolean valid(int i ,int j)
	{
		return (i>=0 && i<3 && j>=0 && j<3) ;
	}
	
	private void solve (int[][] initial) {
		
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
		
		
		PriorityQueue<Node> pq = new PriorityQueue<Node>((a, b) -> (a.g + a.h) - (b.g + b.h));
		int h = this.Manhattan(initial) ;
		Node node = new Node(0,h,initial,x,y,null);
		pq.add(node) ;
		
		while(! pq.isEmpty())
		{
			Node parent = pq.poll() ;
			if(parent.h == 0)
			{
				System.out.println("Done");
				for(int i=0 ;i<3 ;i++)
					for(int j=0 ;j<3 ;j++)
						System.out.println(parent.state[i][j]);
				System.out.println(parent.g);
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
					
					h = this.Manhattan(matrix) ;
					Node newNode = new Node(parent.g + 1,h,matrix,row,col,parent);
					pq.add(newNode) ;
				}
			}
		}
		
		
		
	}
	
	
	
	public static void main(String[] args) {
		int[][] initial = { {7, 2, 4}, {5, 0, 6}, {8, 3, 1} };
		
		Search s = new Search() ;
		if(s.isSolvable(initial))
			s.solve(initial) ;
		else 
			System.out.println("This initial state can not be solved");

	}

}
