package Parser;

import Lex.Token;
import java.io.*;
import java.util.*;
//import Parser.MyStk;

public class Parse
{
   private MyStk<Token> stk;
   private Scanner file;
   private Token [] tok;
   
   public Parse(int size, String name) throws FileNotFoundException
   {
      file = new Scanner(new File(name));
      tok = new Token[size];
      stk = new MyStk<>();
      readTokens();
   }
   
   private void readTokens()
   {
      while (file.hasNext())
      {
         
      }
   }
}