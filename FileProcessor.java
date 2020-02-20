import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.util.ArrayList;

public class FileProcessor<T>
{
   private BufferedWriter bw;
   private ArrayList<T> al;
   
   public FileProcessor(String name, ArrayList<T> als)
   {
      try
      {
         bw = new BufferedWriter(new FileWriter(name, true));
         al = als;
         print();
         closeFile();
      }
      catch (IOException ioe)
      {
         System.err.println("Error.. File Not Found!!");
      }
   }
   
   private void print()
   {
      final String SAVE = "\r\n";
      String data = "";
      try
      {
         for (T obj: al)
         {
            data = obj + SAVE;
            bw.write(data);
         }
         bw.close();
      }
      catch (IOException ioe)
      {
         System.err.println("Error.. File not Found!!");
      }
   }
   
   private void closeFile() throws IOException
   {
      bw.close();
   }
}