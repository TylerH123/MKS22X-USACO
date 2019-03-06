import java.util.*;
import java.io.*;

public class USACO{
  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner file = new Scanner(f);
    int R,C,E,N,R_s,C_s,D_s;
    int[][] lake;
    boolean firstLine = true;
    String[] g = new String[4];
    if (file.hasNextLine()){
      //split the first line by space then copy which get copied into an array of String
      if (firstLine){
        g = file.nextLine().split(" ");
        firstLine = false;
      }
    }
    R = Integer.parseInt(g[0]);
    C = Integer.parseInt(g[1]);
    E = Integer.parseInt(g[2]);
    N = Integer.parseInt(g[3]);
    lake = new int[R][C];
    int row = 0;
    //copies the the lake elevations into the lake array
    while(file.hasNextLine()){
      String line = file.nextLine();
      if (row < R){
        String[] elev = line.split(" ");
        int col = 0;
        for(String item : elev){
          lake[row][col] = Integer.parseInt(item);
          col++;
        }
        row++;
      }
      else {
        String[] dir = line.split(" ");
        R_s = Integer.parseInt(dir[0]) - 1;
        C_s = Integer.parseInt(dir[1]) - 1;
        D_s = Integer.parseInt(dir[2]);
        stomp(lake,R_s,C_s,D_s);
      }
    }
    int depth = 0;
    //calculating aggregated depth
    for (int i = 0; i < lake.length; i++){
      for (int j = 0; j < lake[i].length; j++){
        //if lower than elevation, then subtract
        if (lake[i][j] < E){
          depth += (E - lake[i][j]);
        }
      }
    }
    return depth * 72 * 72;
  }

  public static void stomp(int[][] lake, int r, int c, int d){
    int biggest = 0;
    int r_b = 0;
    int c_b = 0;
    //finding highest elevation
    for (int row = 0; row < 3; row++){
      for(int col = 0; col < 3; col++){
        if (lake[r+row][c+col] > biggest){
          biggest = lake[r+row][c+col];
          r_b = row+r;
          c_b = col+c;
        }
      }
    }
    //stomp out the highest elevation by the amount
    biggest -= d;
    lake[r_b][c_b] -= d;
    //compare the other elevations to the stomped elevation
    for (int row = 0; row < 3; row++){
      for(int col = 0; col < 3; col++){
        //if higher, then stomp to the same elevation
        if (lake[r+row][c+col] > biggest){
          lake[r+row][c+col] = biggest;
        }
      }
    }
  }
  public static String toString(int[][] lake){
    String output = "";
    for(int i = 0; i < lake.length; i++){
      for (int j = 0; j < lake[i].length; j++){
        output += lake[i][j] + " ";
      }
      output += "\n";
    }
    return output;
  }
  public static void main(String[] args){
    try{
      System.out.println(bronze("makelake.in "));
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
}
