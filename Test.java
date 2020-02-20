import Lex.Lexi;
import Lex.Symbol;
import Lex.Token;
import Parser.*;

public class Test
{
   public static void main(String [] args)
   {
      String file = "C:/Users/Tim/Desktop/Compiler_Project/Compiler/test/pgm1.txt";
      Lexi lex = new Lexi(file);
      //FileProcessor<Symbol> fps = new FileProcessor<>("SymbolTable.txt", lex.getSymTab());
      //FileProcessor<Token> fpt = new FileProcessor<>("TokenList.txt", lex.getTokenList());
      lex.print();
   }
}