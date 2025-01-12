/*

Imagine you have a robot navigating around (up/down, left/right). 
There is a 2D grid with obstacles ‘x’. 
Given a start and end point, write a function to determine if any path exists between these two points.

E.g. start = S, finish = F

[x, S, 0, 0, 0]
[x, x, 0, 0, 0]
[0, x, 0, x, 0]
[0 ,0, 0, x, 0]
[0, 0, x, 0, F]

= True as there exists a path from S to F

E.g. start = S, finish = F

[x, 0, 0, S, 0]
[x, x, x, 0, 0]
[0, 0, x, x, x]
[F, x, 0, x, 0]
[0, 0, 0, 0, 0]

= False as there does not exist a path from S to F

*/

public class Main {
  public static void main(String[] args) {

    // char [][] board = new char[][] {
    //   {'x', 'S', 0, 0, 0},
    //   {'x', 'x', 0, 0, 0},
    //   {0, 'x', 0, 'x', 0},
    //   {0 ,0, 0, 'x', 0},
    //   {0, 0, 'x', 0, 'F'},
    // };

    char [][] board = new char[][] {
      {'x', 0, 0, 'S', 0},
      {'x', 'x', 'x', 0, 0},
      {0, 0, 'x', 'x', 'x'},
      {'F', 'x', 0, 'x', 0},
      {0, 0, 0, 0, 0}
    };

    System.out.println(explore(0, 1, board));
    
  }

  private static int WIDTH = 5;
  private static int HEIGHT = 5;

  // TODO make enum
  private static char VISITED = 'V';
  private static char OBSTACLE = 'x';
  private static char FINISH = 'F';


  private static boolean canVisit(int x, int y, char [][] board) {
    // if within the board AND no obstacles AND not visited before
    return x >= 0 && x < WIDTH && y >= 0 && y < HEIGHT 
      && board[x][y] != VISITED && board [x][y] != OBSTACLE;
  }

  private static boolean explore(int x, int y, char [][] board ) {

    if(board[x][y] == FINISH) {
      return true;
    }

    // mark current pos in the board
    board[x][y] = VISITED;

    // branch into all four directions
    // collect if any returned true
    
    if(canVisit(x, y-1, board)) {
      if(explore(x, y-1, board)) {
        return true;
      }
    }
    if(canVisit(x+1, y, board)) {
      if(explore(x+1, y, board)) {
        return true;
      }
    }
    if(canVisit(x, y+1, board)) {
      if(explore(x, y+1, board)) {
        return true;
      }
    }
    if(canVisit(x-1, y, board)) {
      if(explore(x-1, y, board)) {
        return true;
      }
    }

    // UNMARK current pos (?) - not needed.
    
    return false;
  }

}