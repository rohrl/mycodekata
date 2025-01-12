package scrollbook;
/*
Please write complete compilable code.
Your class should be named Solution
Read input from standard input (STDIN) and print output to standard output(STDOUT).
For more details, please check https://www.interviewstreet.com/recruit/challenges/faq/view#stdio
*/

import java.util.*;
import java.lang.*;

// TODO
public class Solution  {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
        int k = sc.nextInt();
        
        Solution sol = new Solution(n,k);
        
    }
    
    final int K, N;
    Deque<Integer> [] pegs;    
    
    public Solution(int n, int k) {
     	N = n;
        K = k;
        pegs = new Deque [K];
        for(int i = 0; i < k; i++)
            pegs[i]= new LinkedList<Integer>();
    }
    
    boolean move(int from, int to) {
        if(pegs[from].isEmpty() ||
           (pegs[to].peek() != null && pegs[from].peek() > pegs[to].peek()))
            return false;
        
        pegs[to].push(pegs[from].pop());
          
        return true;

    }
    
    

}