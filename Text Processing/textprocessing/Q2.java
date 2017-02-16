package textprocessing;

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

public class Q2 {
   private final int R;       // the radix
   private int[][] dfa;       // the KMP automoton

   private char[] pattern;    // either the character array for the pattern
   private String pat;        // or the pattern string

   // create the DFA from a String
   public Q2(String pat) {
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
   public Q2(char[] pattern, int R) {
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
      
	   In in = new In("Protein.txt");
	   
	   String pat1 = "protein";
       String txt = in.readAll();
	
      
       Q2 kmp1 = new Q2(pat1);
       int offset1 = kmp1.search(txt);


       // print results
       StdOut.println("text:    " + txt);

       StdOut.print("pattern: ");
     
      // for (int i = 0; i < offset1; i++)
         //  StdOut.print(" ");
       StdOut.println(pat1 + " at pos " + offset1);
       
       String pat2 = "complex";
       Q2 kmp2 = new Q2(pat2);
       int offset2 = kmp2.search(txt);
       StdOut.print("pattern: ");
       
       //for (int i = 0; i < offset2; i++)
         // StdOut.print(" ");
       StdOut.println(pat2 + " at pos " + offset2);
       
       String pat3 = "PPI";
       Q2 kmp3 = new Q2(pat3);
       int offset3 = kmp3.search(txt);
       StdOut.print("pattern: ");
       
     //  for (int i = 0; i < offset3; i++)
         // StdOut.print(" ");
       StdOut.println(pat3 + " at pos " + offset3);
       
       String pat4 = "prediction";
       Q2 kmp4 = new Q2(pat4);
       int offset4 = kmp4.search(txt);
       StdOut.print("pattern: ");
       
       //for (int i = 0; i < offset4; i++)
         // StdOut.print(" ");
       StdOut.println(pat4 + " at pos " + offset4);
       
       String pat5 = "Interaction";
       Q2 kmp5 = new Q2(pat5);
       int offset5 = kmp5.search(txt);
       StdOut.print("pattern: ");
       
       //for (int i = 0; i < offset5; i++)
         // StdOut.print(" ");
       StdOut.println(pat5 + " at pos " + offset5);
       
       String pat6 = "In";
       Q2 kmp6 = new Q2(pat6);
       int offset6 = kmp6.search(txt);
       StdOut.print("pattern: ");
       
       //for (int i = 0; i < offset6; i++)
        //  StdOut.print(" ");
       StdOut.println(pat6 + " at pos " + offset6);
       
       
   }    
}
