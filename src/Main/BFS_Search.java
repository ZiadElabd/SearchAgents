package Main;

import java.util.*;

public class BFS_Search {

    private static int toArr(int[] arr,String state){
        int blockPostion=0;
        int j=0;
        for (int i = 1; i < state.length(); i+=3) {
            arr[j]=state.charAt(i)-'0';
            if(arr[j]==0) blockPostion=j;
            j++;
        }
        return blockPostion;
    }
    public static String search(int[] intialState){
        Long start=System.currentTimeMillis();
        String goal=Arrays.toString(new int[]{0,1,2,3,4,5,6,7,8});
        String state=Arrays.toString(intialState);
        Queue<LinkedList<String>> paths=new LinkedList<>();
        Queue<String> frontier=new LinkedList();
        HashSet<String> explored=new HashSet<>();
        frontier.add(state);
        paths.add(new LinkedList<>());
        paths.peek().add("Base-->"+state);
        int count=0;
        while (!frontier.isEmpty()){
            count++;
            LinkedList<String> path=paths.poll();
            state=frontier.poll();
            explored.add(state);
            if(state.equals(goal)){
                System.out.println("Nodes expanded :"+count);
                System.out.println("cost of path :"+path.size());
                System.out.println("Search depth :"+path.size());
                System.out.println("Running time :"+(System.currentTimeMillis()-start)+"ms");
                System.out.println("Path to reach goal");
                for (String s:path) {
                    System.out.println(s);
                }
                return "Succes to reach goal";
            }
            List<List<String>> neighbour=getchildState(state);
            for (List<String> s:neighbour) {
                if ((!explored.contains(s.get(0)))) {
                    frontier.add(s.get(0));
                    explored.add(s.get(0));
                    LinkedList new_path= new LinkedList();
                    new_path.addAll(path);
                    new_path.add(s.get(1)+"-->"+s.get(0));
                    paths.add(new_path);
                }
            }

        }
        return "There is no solution for this puzzle !!";
    }
    private static List<List<String>> getchildState(String State){
        List<List<String>> result=new ArrayList<>();
        int[] arr=new int[9];
        int postion=toArr(arr,State);
        int x=postion/3;int y=postion%3;
        int[] xMoves=new int[]{-1,0,1,0};
        int[] yMoves=new int[]{0,1,0,-1};
        String[] moves=new String[]{"UP","RIGHT","DOWN","LEFT"};
        for (int i = 0; i < 4; i++) {
            int[] tempArr=Arrays.copyOf(arr,9);
            int xd=x+xMoves[i];
            int yd=y+yMoves[i];
            if((xd<0||xd>=3||yd<0||yd>=3)) continue;
            int newPostion=(xd*3)+yd;
            int temp=arr[postion];
            tempArr[postion]=tempArr[newPostion];
            tempArr[newPostion]=temp;
            result.add(Arrays.asList(Arrays.toString(tempArr),moves[i]));
        }
        return result;
    }


}