package textprocessing;

import java.util.Random;

/***************************************************************
*
*  Compilation:  javac Brtue.java
*  Execution:    java Brute pattern text
*
*  Reads in two strings, the pattern and the input text, and
*  searches for the pattern in the input text using brute force.
*
*  % java Brute abracadabra abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:               abracadabra          
*
*  % java Brute rab abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:         rab                         
* 
*  % java Brute rabrabracad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern:                        rabrabracad

*
*  % java Brute bcara abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad 
*  pattern:                                   bcara
* 
*  % java Brute abacad abacadabrabracabracadabrabrabracad
*  text:    abacadabrabracabracadabrabrabracad
*  pattern: abacad
*
***************************************************************/

public class Q1_BruteForceMatch {

  /***************************************************************************
   *  String versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(String pat, String txt) {
       int M = pat.length();
       int N = txt.length();
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (txt.charAt(i) == pat.charAt(j)) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   }


  /***************************************************************************
   *  char[] array versions
   ***************************************************************************/

   // return offset of first match or N if no match
   public static int search1(char[] pattern, char[] text) {
       int M = pattern.length;
       int N = text.length;

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (text[i+j] != pattern[j])
                   break;
           }
           if (j == M) return i;            // found at offset i
       }
       return N;                            // not found
   }

   // return offset of first match or N if no match
   public static int search2(char[] pattern, char[] text) { 
       int M = pattern.length;
       int N = text.length;
       int i, j;
       for (i = 0, j = 0; i < N && j < M; i++) {
           if (text[i] == pattern[j]) j++;
           else { i -= j; j = 0;  }
       }
       if (j == M) return i - M;    // found
       else        return N;        // not found
   } 


   // test client
   public static void main(String[] args) {
     
	   Random rand = new Random();
       
       int length = 10000;
       char str;
     	     		
       long totaltime = 0;
       long t1= System.currentTimeMillis();
       double times = 100;
       
       for (int repeat = 1; repeat <= 100; repeat++){
    	   
    	   // get a random string of length 10000   
    	   String string = "";
    	   for( int j = 0; j < length; j++ ){
    		   str = (char) (rand.nextInt(26) + 'a');
    	   string = string + str;
       }   
    	   
    	   String pat = "disk";
    	   
    	   char[] pattern = pat.toCharArray();
           char[] txt = string.toCharArray();
       
           int offset1b = search1(pattern, txt);
           
           // print results
           StdOut.println("text T:    " + string);
       
           // from brute force search method 1b
           if(offset1b != txt.length){
        	   StdOut.print("pattern P: ");
        	   for (int i = 0; i < offset1b; i++)
        		  // StdOut.print(" ");
               StdOut.println(pat + " at pos " + offset1b);
            }else if(offset1b == txt.length){ 
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
    	   
    	   // get a random string of length 10000   
    	   String string = "";
    	   for( int j = 0; j < length; j++ ){
    		   str = (char) (rand.nextInt(26) + 'a');
    	   string = string + str;
       }   
    	   
     	   String pat = "ab";
    	   
    	   char[] pattern = pat.toCharArray();
           char[] txt = string.toCharArray();
       
           int offset1b = search1(pattern, txt);
           
           // print results
           StdOut.println("text T:    " + string);
       
           // from brute force search method 1b
           if(offset1b != txt.length){
        	   StdOut.print("pattern P: ");
        	   for (int i = 0; i < offset1b; i++)
        		  // StdOut.print(" ");
               StdOut.println(pat + " at pos " + offset1b);
            }else if(offset1b == txt.length){ 
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
	   
    	   // get a random string of length 10000   
	      String string = "";
	      for( int j = 0; j < length; j++ ){
	    	  str = (char) (rand.nextInt(26) + 'a');
	          string = string + str;
          }
	      
	      String pat = "aa";
	   
	      char[] pattern = pat.toCharArray();
          char[] txt = string.toCharArray();
   
          int offset1b = search1(pattern, txt);
       
           // print results
           StdOut.println("text T:    " + string);
   
          // from brute force search method 1b
          if(offset1b != txt.length){
        	  StdOut.print("pattern P: ");
        	  for (int i = 0; i < offset1b; i++)
        		 // StdOut.print(" ");
              StdOut.println(pat + " at pos " + offset1b);
           }else if(offset1b == txt.length){ 
        	   StdOut.println(pat + " is not found ");
            }
        } 
       
       long t6= System.currentTimeMillis();
       totaltime2 += t6 - t5;
       double time2 = totaltime2 / times;
       System.out.println("The average CPU time is:" + time2 );
     
  }
}
   
 
