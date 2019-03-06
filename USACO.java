import java.util.*;
import java.io.*;

public class USACO{
  public static int bronze(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner file = new Scanner(f);
    int R,C,E,N;
    int[][] lake;
    boolean firstLine = true;
    String[] g = new String[4];
    if (file.hasNextLine()){
      int cols = 0;
      String line = file.nextLine();
      //split the first line by space then copy which get copied into an array of String
      if (firstLine){
        g = line.split(" ");
        firstLine = false;
      }
    }
    R = Integer.parseInt(g[0]);
    C = Integer.parseInt(g[1]);
    E = Integer.parseInt(g[2]);
    N = Integer.parseInt(g[3]);
    lake = new int[R][C];
    file = new Scanner(f);
    //copies the the lake elevations into the lake array
      for (int r = 1; r < R - 1; r++){
        for (int i = 0; i < line.length(); i++){

        }
      }
    }
    return 1;
  }

  public static void main(String[] args){
    try{
      bronze("makelake.in ");
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
}
