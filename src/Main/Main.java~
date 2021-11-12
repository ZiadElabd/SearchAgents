package Main;

import java.util.Scanner;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("   8-Puzzle   ");
        System.out.println("enter the state: ");
        int state[][] = new int[3][3] ;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                state[i][j] = scan.nextInt();
            }
        }
        System.out.println("choose the search: ");
        System.out.println("1- DFS");
        System.out.println("2- BFS");
        System.out.println("3- A*");
        int choice = scan.nextInt();
        if (choice == 1){
            new Thread(null, new DfsSearch(state), "", 1 << 30).start();
        }else if (choice == 2){
            BfsSearch b = new BfsSearch();
            int[] array = Stream.of(state).flatMapToInt(IntStream::of).toArray();
            String consoleOutput = b.search(array);
            System.out.println(consoleOutput);
        }else if (choice == 3){
        	System.out.println("1- Manhattan");
            System.out.println("2- Euclidean");
            choice = scan.nextInt() ;
            Search sh = new Search();
            if(choice == 1){
            	sh.solve(state, sh.Manhattan);
            }else if(choice == 2)
            {
            	sh.solve(state, sh.Euclidean);
            }
            
        }
        
    }
}
