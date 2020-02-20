package Parser;

import java.util.*;
import java.io.*;

public class GramTok
{
   private Scanner file;
   private ArrayList<String> data;
   private ArrayList<String> elem;
   private ArrayList<String> first;
   private ArrayList<String> last;
   private ArrayList<String> equals;
   
   public GramTok(String name) throws FileNotFoundException
   {
      file = new Scanner(new File(name));
      data = new ArrayList<>();
      elem = new ArrayList<>();
      first = new ArrayList<>();
      last = new ArrayList<>();
      equals = new ArrayList<>();
      process();
   }
   
   private void process()
   {
      int count = 0;
      while (file.hasNext())
      {
         data.add(file.next());
      }
      for (int i = 0; i < data.size(); i++)
      {
         if (data.get(i).equals("::="))
         {
            elem.add(data.get(i-1));
            first.add(data.get(i+1));
            count = 0;
            if (i > 2)
            {
               last.add(data.get(i-2));
            }
            if (i + 3 < data.size())
            {
               count = i + 1;
               while (!data.get(count+2).equals("::="))
               {
                  // row = left col = right
                  equals.add(data.get(count));
                  equals.add(data.get(count+1));
                  count += 1;
               }
               count = 0;
            }
         }
      }
      last.add(data.get(data.size()-1));
   }
   
   public int size()
   {
      return elem.size();
   }
   
   public ArrayList<String> getElem()
   {
      return elem;
   }
   
   public ArrayList<String> getFirst()
   {
      return first;
   }
   
   public ArrayList<String> getLast()
   {
      return last;
   }
   
   public ArrayList<String> getEquals()
   {
      return equals;
   }
}