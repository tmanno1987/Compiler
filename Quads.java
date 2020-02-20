package Parser;

import java.io.*;
import java.util.*;

public class Quads
{
   private String pos1;
   private String pos2;
   private String pos3;
   private String pos4;
   
   public Quads(String s1, String s2, String s3, String s4)
   {
      pos1 = s1;
      pos2 = s2;
      pos3 = s3;
      pos4 = s4;
   }
   
   public String toString()
   {
      return pos1 + " " + pos2 + " " + pos3 + " " + pos4;
   }
}