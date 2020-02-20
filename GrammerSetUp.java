package Parser;

import java.util.*;
import java.io.*;

public class GrammerSetUp
{
   private Scanner tntFile;
   private ArrayList<String> elem;
   private ArrayList<String> frst;
   private ArrayList<String> lst;
   private ArrayList<String> equ;
   private String [] names;
   private int len;
   private GramTok gt;
   private boolean [][] first;
   private boolean [][] firstPlus;
   private boolean [][] firstStar;
   private boolean [][] last;
   private boolean [][] lastPlus;
   private boolean [][] equals; // =
   private boolean [][] takes;  // >
   private boolean [][] yields; // <
   private int [] f;
   private int [] g;
   
   public GrammerSetUp(String fname1, String fname2) throws FileNotFoundException
   {
      tntFile = new Scanner(new File(fname1));
      gt = new GramTok(fname2);
      processTNTFile();
      len = names.length;
      elem = gt.getElem();
      frst = gt.getFirst();
      lst = gt.getLast();
      equ = gt.getEquals();
      first = setUp(elem,frst);
      firstPlus = warshall(first,false);
      firstStar = warshall(first,true);
      last = setUp(elem,lst);
      lastPlus = warshall(last,false);
      equals = setUp(equ);
      takes = multiplyArray(transpose(lastPlus), multiplyArray(equals, firstStar));
      yields = multiplyArray(equals, firstPlus);
      f = new int[len];
      g = new int[len];
      createFunction(addArray(equals,takes), addArray(equals, transpose(yields)));
      tntFile.close();
   }
   
   private void createFunction(boolean [][] a, boolean [][] b)
   {
      int z = a.length;
      int n = z * 2;
      int sum = 0;
      boolean [][] temp = new boolean[n][n];
      
      // 0  a
      // b  0
      for (int r = 0; r < z; r++)
      {
         for (int c = 0; c < z; c++)
         {
            temp[r][c] = false;
            temp[r+z][c] = a[r][c];
            temp[r][c+z] = b[r][c];
            temp[r+z][c+z] = false;
         }
      }
      // temp* created
      temp = warshall(temp,true);
      
      for (int r = 0; r < n; r++)
      {
         for (int c = 0; c < n; c++)
         {
            if (temp[r][c])
            {
               sum++;
            }
         }
         if (r < z)
         {
            f[r] = sum;
         }
         else
         {
            g[r-z] = sum;
         }
         sum = 0;
      }
   }
   
   private boolean [][] addArray(boolean [][] a, boolean [][] b)
   {
      int n = a.length;
      boolean [][] temp = new boolean[n][n];
      
      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            temp[i][j] = a[i][j] || b[i][j];
         }
      }
      return temp;
   }
   
   private boolean [][] multiplyArray(boolean [][] a, boolean [][] b)
   {
      // < := (a) && (b)
      int n = a.length;
      boolean [][] temp = new boolean[n][n];
      
      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            for (int k = 0; k < n; k++)
            {
               temp[i][j] = a[i][k] && b[k][j];
            }
         }
      }
      return temp;
   }
   
   public void processTNTFile()
   {
      int num = 0;
      int size = tntFile.nextInt();
      names = new String[size];
      
      while (tntFile.hasNext())
      {
         names[num++] = tntFile.next();
      }
   }
   
   // get transitive closure for boolean array
   public boolean [][] warshall(boolean [][] a, boolean flag)
   {
      int n = a.length;
      for (int i = 0; i < n; i++)
      {
         for (int j = 0; j < n; j++)
         {
            if (a[i][j])
            {
               for (int k = 0; k < n; k++)
               {
                  a[i][k] = a[j][k] | a[i][k];
               }
            }
         }
      }
      // upgrade to * closure
      if (flag)
      {
         for (int i = 0; i < n; i++)
         {
            a[i][i] = true;
         }
      }
      return a;
   }
   
   private void print(boolean [][] a)
   {
      for (int i = 0; i < a.length; i++)
      {
         for (int j = 0; j < a[i].length; j++)
         {
            if ( j % a.length == (a.length - 1))
            {
               System.out.println(a[i][j]);
            }
            else
            {
               System.out.print(a[i][j] + " ");
            }
         }
      }
   }
   
   private boolean [][] transpose(boolean [][] a)
   {
      boolean [][] temp = new boolean[a.length][a.length];
      
      for (int c = 0; c < a.length; c++)
      {
         for (int r = 0; r < a[c].length; r++)
         {
            temp[c][r] = a[r][c];
         }
      }
      
      return temp;
   }
   
   private int findLoc(String s)
   {
      for (int i = 0; i < names.length; i++)
      {
         if (s.equals(names[i]))
         {
            return i;
         }
      }
      return 0;
   }
   
   private boolean [][] setUp(ArrayList<String> a)
   {
      int n = names.length;
      boolean [][] temp = new boolean[n][n];
      int one = 0;
      int two = 0;
      for (int i = 0; i < n; i++)
      {
         one = findLoc(a.get(i));
         two = findLoc(a.get(i+1));
         
         temp[one][two] = true;
      }
      
      return temp;
   }
   
   private boolean [][] setUp(ArrayList<String> a, ArrayList<String> b)
   {
      int n = names.length;
      boolean [][] temp = new boolean[n][n];
      int one = 0;
      int two = 0;
      for (int i = 0; i < a.size(); i++)
      {
         one = findLoc(a.get(i));
         two = findLoc(b.get(i));
         
         temp[one][two] = true;
      }
      
      return temp;
   }
   
   public void printArray()
   {
      for (int i = 0; i < names.length; i++)
      {
         System.out.println(names[i]);
      }
   }
   
   public int [] getF()
   {
      return f;
   }
   
   public int [] getG()
   {
      return g;
   }
}

class TestTemp
{
   public static void main(String [] args) throws FileNotFoundException
   {
      String n1 = "C:/Users/Tim/Desktop/Compiler_Project/Compiler/grammer.txt";
      String n2 = "C:/Users/Tim/Desktop/Compiler_Project/Compiler/test/tnt.txt";
      GrammerSetUp g = new GrammerSetUp(n2,n1);
      //g.printArray();
   }
}