package textprocessing;

import java.util.Random;

/***************************************************************
 *  Compilation:  javac BoyerMoore.java
 *  Execution:    java BoyerMoore pattern text
 *
 *  Reads in two strings, the pattern and the input text, and
 *  searches for the pattern in the input text using the
 *  bad-character rule part of the Boyer-Moore algorithm.
 *  (does not implement the strong good suffix rule)
 *
 *  % java BoyerMoore abracadabra abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:               abracadabra
 *
 *  % java BoyerMoore rab abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:         rab
 *
 *  % java BoyerMoore bcara abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad 
 *  pattern:                                   bcara
 *
 *  % java BoyerMoore rabrabracad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern:                        rabrabracad
 *
 *  % java BoyerMoore abacad abacadabrabracabracadabrabrabracad
 *  text:    abacadabrabracabracadabrabrabracad
 *  pattern: abacad
 *
 ***************************************************************/

public class Q1_BoyerMoore {
    private final int R;     // the radix
    private int[] right;     // the bad-character skip array

    private char[] pattern;  // store the pattern as a character array
    private String pat;      // or as a string

    // pattern provided as a string
    public Q1_BoyerMoore(String pat) {
        this.R = 256;
        this.pat = pat;

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pat.length(); j++)
            right[pat.charAt(j)] = j;
    }

    // pattern provided as a character array
    public Q1_BoyerMoore(char[] pattern, int R) {
        this.R = R;
        this.pattern = new char[pattern.length];
        for (int j = 0; j < pattern.length; j++)
            this.pattern[j] = pattern[j];

        // position of rightmost occurrence of c in the pattern
        right = new int[R];
        for (int c = 0; c < R; c++)
            right[c] = -1;
        for (int j = 0; j < pattern.length; j++)
            right[pattern[j]] = j;
    }

    // return offset of first match; N if no match
    public int search(String txt) {
        int M = pat.length();
        int N = txt.length();
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pat.charAt(j) != txt.charAt(i+j)) {
                    skip = Math.max(1, j - right[txt.charAt(i+j)]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }


    // return offset of first match; N if no match
    public int search(char[] text) {
        int M = pattern.length;
        int N = text.length;
        int skip;
        for (int i = 0; i <= N - M; i += skip) {
            skip = 0;
            for (int j = M-1; j >= 0; j--) {
                if (pattern[j] != text[i+j]) {
                    skip = Math.max(1, j - right[text[i+j]]);
                    break;
                }
            }
            if (skip == 0) return i;    // found
        }
        return N;                       // not found
    }



    // test client
    public static void main(String[] args) {
  
    	// get a random string of length 10000   
    	Random rand = new Random();
    	
    	int length = 10000;
    	char str;
    	
    	long totaltime = 0;
        long t1= System.currentTimeMillis();
        double times = 100;
       
        for (int repeat = 1; repeat <= 100; repeat++){
        	String string = "";
            for( int j = 0; j < length; j++ ){
            	str = (char) (rand.nextInt(26) + 'a');
                string = string + str;
            }   
    	
        String pat = "disk";
        
        char[] pattern = pat.toCharArray();
        char[] text    = string.toCharArray();

        Q1_BoyerMoore boyermoore1 = new Q1_BoyerMoore(pattern, 256);
        int offset1 = boyermoore1.search(text);

        // print results
        StdOut.println("text:    " + string);

        if(offset1 != text.length){
     	   StdOut.print("pattern P: ");
     	   for (int i = 0; i < offset1; i++)
     		   StdOut.print(" ");
            StdOut.println(pat + " at pos " + offset1);
        }else if(offset1 == text.length){ 
         	StdOut.println(pat + " is not found ");
         }
        }
        
        long t2= System.currentTimeMillis();
        totaltime += t2 - t1;
        double time = totaltime / times;
        System.out.println("The average CPU time is:" + time );
      
        
        long totaltime1 = 0;
        long t3= System.currentTimeMillis();
        
        for (int repeat = 1; repeat <= 100; repeat++){
        	String string = "";
            for( int j = 0; j < length; j++ ){
            	str = (char) (rand.nextInt(26) + 'a');
                string = string + str;
            }   
    	
        String pat = "ab";
        
        char[] pattern = pat.toCharArray();
        char[] text    = string.toCharArray();

        Q1_BoyerMoore boyermoore1 = new Q1_BoyerMoore(pattern, 256);
        int offset1 = boyermoore1.search(text);

        // print results
        StdOut.println("text:    " + string);

        if(offset1 != text.length){
     	   StdOut.print("pattern P: ");
     	   for (int i = 0; i < offset1; i++)
     		 //  StdOut.print(" ");
            StdOut.println(pat + " at pos " + offset1);
        }else if(offset1 == text.length){ 
         	StdOut.println(pat + " is not found ");
         }
        }
        
        long t4= System.currentTimeMillis();
        totaltime1 += t4 - t3;
        double time1 = totaltime1 / times;
        System.out.println("The average CPU time is:" + time1 );
        
       
        long totaltime2 = 0;
        long t5= System.currentTimeMillis();
    
        for (int repeat = 1; repeat <= 100; repeat++){
        	String string = "";
            for( int j = 0; j < length; j++ ){
            	str = (char) (rand.nextInt(26) + 'a');
                string = string + str;
            }   
    	
        String pat = "aa";
        
        char[] pattern = pat.toCharArray();
        char[] text    = string.toCharArray();

        Q1_BoyerMoore boyermoore1 = new Q1_BoyerMoore(pattern, 256);
        int offset1 = boyermoore1.search(text);

        // print results
        StdOut.println("text:    " + string);

        if(offset1 != text.length){
     	   StdOut.print("pattern P: ");
     	   for (int i = 0; i < offset1; i++)
     		 //  StdOut.print(" ");
            StdOut.println(pat + " at pos " + offset1);
        }else if(offset1 == text.length){ 
         	StdOut.println(pat + " is not found ");
         }
        }
        
        long t6= System.currentTimeMillis();
        totaltime2 += t6 - t5;
        double time2 = totaltime2 / times;
        System.out.println("The average CPU time is:" + time2 );
      
      
        
    }
}       
 