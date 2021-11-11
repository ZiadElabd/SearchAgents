package BFS;

import java.util.*;

public class bfs {
    public static void main(String[] args) {

      int[] intial=new int[]{ 4,3,2,6,5,0,7,8,1};
        for (String s:search(intial)) {
         System.out.println(s);
        };
    }
    private static String toString(int[] state){
        String result=state[0]+"";
        for (int i=1;i<9;i++) {
            result+=","+state[i];
        }
        return result;
    }
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
    public static LinkedList<String> search(int[] intialState){
        String goal=Arrays.toString(new int[]{0,1,2,3,4,5,6,7,8});
        String state=Arrays.toString(intialState);
        Queue<LinkedList<String>> paths=new LinkedList<>();
        Queue<String> frontier=new LinkedList();
        TreeSet<String> explored=new TreeSet<>();
        frontier.add(state);
          paths.add(new LinkedList<>());
          paths.peek().add(state);
        int count=0;
        while (!frontier.isEmpty()){
            System.out.println(count++);
            LinkedList<String> path=paths.poll();
            state=frontier.poll();
            explored.add(state);
            if(state.equals(goal)){
                System.out.println(count);
                return path;
            }
            List<String> neigbour=getchildState(state);
            for (String s:neigbour) {
                if ((!frontier.contains(s))||(!explored.contains(s))) {
                    frontier.add(s);
                   LinkedList new_path= new LinkedList();
                    new_path.addAll(path);
                    new_path.add(s);
                    paths.add(new_path);
                }
            }

        }

        return null;
    }
    private static List<String> getchildState(String State){
        List<String> result=new ArrayList<>();
        int[] arr=new int[9];
        int postion=toArr(arr,State);
        int x=postion/3;int y=postion%3;
        int[] xMoves=new int[]{-1,0,1,0};
        int[] yMoves=new int[]{0,1,0,-1};
        for (int i = 0; i < 4; i++) {
            int[] tempArr=Arrays.copyOf(arr,9);
            int xd=x+xMoves[i];
            int yd=y+yMoves[i];
            if((xd<0||xd>=3||yd<0||yd>=3)) continue;
            int newPostion=(xd*3)+yd;
            int temp=arr[postion];
            tempArr[postion]=tempArr[newPostion];
            tempArr[newPostion]=temp;
            result.add(Arrays.toString(tempArr));
        }
        return result;
    }


}
