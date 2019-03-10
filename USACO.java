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
    //copies the lake elevations into the lake array
    while(file.hasNextLine()){
      String line = file.nextLine();
      if (row < R){
        //split each line by space and copy into array of Strings
        String[] elev = line.split(" ");
        int col = 0;
        //loop through the array of elevations
        for(String item : elev){
          //fill the lake array
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
        //if lower than elevation, then subtract and add result to depth
        if (lake[i][j] < E){
          depth += (E - lake[i][j]);
        }
      }
    }
    return depth * 72 * 72;
  }
  //stomp helper
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
  //string representation of lake
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
  //string representation of land
  public static String toString(char[][] land){
    String output = "";
    for(int i = 0; i < land.length; i++){
      for (int j = 0; j < land[i].length; j++){
        output += land[i][j] + " ";
      }
      output += "\n";
    }
    return output;
  }
  //string representation of land
  public static int silver(String filename) throws FileNotFoundException{
    File f = new File(filename);
    Scanner file = new Scanner(f);
    int N,M,T,R1,C1,R2,C2;
    boolean[][] land;
    N = Integer.parseInt(file.next());
    M = Integer.parseInt(file.next());
    T = Integer.parseInt(file.next());
    land = new boolean[N][M];
    int[][] moves = new int[N][M];
    //copies the grass and trees into the land array
    for(int r = 0; r < N; r++){
      String word = file.next();
      for(int c = 0; c < M; c++){
        //fill the land array
        land[r][c] = (word.charAt(c) != '*');
      }
    }
    R1 = Integer.parseInt(file.next()) - 1;
    C1 = Integer.parseInt(file.next()) - 1;
    R2 = Integer.parseInt(file.next()) - 1;
    C2 = Integer.parseInt(file.next()) - 1;
    //System.out.println(toString(land));
    moves[R1][C1] = 1;
    int g = (R1+C1)%2;
    for (int t = 0; t < T; t++) {
			for (int r = 0; r < N; r++) {
				for (int c = (r+g)%2; c < M; c += 2) {
					if (land[r][c]) {
						if (r > 0) moves[r-1][c] += moves[r][c];
						if (c > 0) moves[r][c-1] += moves[r][c];
						if (r < N-1) moves[r+1][c] += moves[r][c];
						if (c < M-1) moves[r][c+1] += moves[r][c];
					}
					moves[r][c] = 0;
				}
			}
			g = (g+1)%2;
		}
		return moves[R2][C2];
  }
  //recursive helper method to find how to get to r2,c2 from r1,c1
  //pretty similar to maze problem
  public static void testBronze(){
    try{
      System.out.println(bronze("makelake.1.in"));
      System.out.println(bronze("makelake.2.in"));
      System.out.println(bronze("makelake.3.in"));
      System.out.println(bronze("makelake.4.in"));
      System.out.println(bronze("makelake.5.in"));
      System.out.println(342144 == bronze("makelake.1.in"));
      System.out.println(102762432 == bronze("makelake.2.in"));
      System.out.println(1058992704 == bronze("makelake.3.in"));
      System.out.println(753121152 == bronze("makelake.4.in"));
      System.out.println(1028282688 == bronze("makelake.5.in"));
    }
    catch (FileNotFoundException e){
      System.out.println("File not found");
    }
  }
  public static void testSilver(){
    try{
      System.out.println(1 == silver("ctravel.1.in"));
      System.out.println(74 == silver("ctravel.2.in"));
      System.out.println(6435 == silver("ctravel.3.in"));
      System.out.println(339246 == silver("ctravel.4.in"));
      System.out.println(0 == silver("ctravel.5.in"));
    }
    catch(FileNotFoundException e){
      System.out.println("File not found");
    }
  }
  public static void main(String[] args){
    testSilver();
  }
}
