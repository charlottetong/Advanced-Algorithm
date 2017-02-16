package textprocessing;

import java.util.Random;

/***************************************************************
*
*  Compilation:  javac KMP.java
*  Execution:    java KMP pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using the
*  KMP algorithm.
*
*  % java KMP abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java KMP rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab
*
*  % java KMP bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
*
*  % java KMP rabrabracad abacadabrabracabracadabrabrabracad 
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad
*
*  % java KMP abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class Q1_KMP {
   private final int R;       // the radix
   private int[][] dfa;       // the KMP automoton

   private char[] pattern;    // either the character array for the pattern
   private String pat;        // or the pattern string

   // create the DFA from a String
   public Q1_KMP(String pat) {
       this.R = 256;
       this.pat = pat;

       // build DFA from pattern
       int M = pat.length();
       dfa = new int[R][M]; 
       dfa[pat.charAt(0)][0] = 1; 
       for (int X = 0, j = 1; j < M; j++) {
           for (int c = 0; c < R; c++) 
               dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
           dfa[pat.charAt(j)][j] = j+1;   // Set match case. 
           X = dfa[pat.charAt(j)][X];     // Update restart state. 
       } 
   } 

   // create the DFA from a character array over R-character alphabet
   public Q1_KMP(char[] pattern, int R) {
       this.R = R;
       this.pattern = new char[pattern.length];
       for (int j = 0; j < pattern.length; j++)
           this.pattern[j] = pattern[j];

       // build DFA from pattern
       int M = pattern.length;
       dfa = new int[R][M]; 
       dfa[pattern[0]][0] = 1; 
       for (int X = 0, j = 1; j < M; j++) {
           for (int c = 0; c < R; c++) 
               dfa[c][j] = dfa[c][X];     // Copy mismatch cases. 
           dfa[pattern[j]][j] = j+1;      // Set match case. 
           X = dfa[pattern[j]][X];        // Update restart state. 
       } 
   } 

   // return offset of first match; N if no match
   public int search(String txt) {

       // simulate operation of DFA on text
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           j = dfa[txt.charAt(i)][j];
       }
       if (j == M) return i - M;    // found
       return N;                    // not found
   }


   // return offset of first match; N if no match
   public int search(char[] text) {

       // simulate operation of DFA on text
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           j = dfa[text[i]][j];
       }
       if (j == M) return i - M;    // found
       return N;                    // not found
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
      
  /*     for (int repeat = 1; repeat <= 100; repeat++){
       	String string = "";
           for( int j = 0; j < length; j++ ){
           	str = (char) (rand.nextInt(26) + 'a');
               string = string + str;
           }   
           
	   String pat = "disk";
      
       char[] pattern = pat.toCharArray();
       char[] text    = string.toCharArray();

       Q1_KMP kmp1 = new Q1_KMP(pattern, 256);
       int offset1 = kmp1.search(text);

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

          Q1_KMP kmp1 = new Q1_KMP(pattern, 256);
          int offset1 = kmp1.search(text);

          // print results
          StdOut.println("text:    " + string);

          if(offset1 != text.length){
        	   StdOut.print("pattern P: ");
        	   for (int i = 0; i < offset1; i++)
        		  // StdOut.print(" ");
               StdOut.println(pat + " at pos " + offset1);
           }else if(offset1 == text.length){ 
            	StdOut.println(pat + " is not found ");
            }
           }
       
       long t4= System.currentTimeMillis();
       totaltime1 += t4 - t3;
       double time1 = totaltime1 / times;
       System.out.println("The average CPU time is:" + time1 );
       
 */      
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

         Q1_KMP kmp1 = new Q1_KMP(pattern, 256);
         int offset1 = kmp1.search(text);

         // print results
         StdOut.println("text:    " + string);

         if(offset1 != text.length){
       	   StdOut.print("pattern P: ");
       	   for (int i = 0; i < offset1; i++)
       		  // StdOut.print(" ");
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
