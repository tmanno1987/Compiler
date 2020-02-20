package Lex;

public class Symbol
{
   // hidden data fields for Symbol class
   private String tok;
   private String cls;
   private String value;
   private String addr;
   private String seg;
   
   // default constructor for Symbol class
   public Symbol()
   {
      tok = null;
      cls = null;
      value = null;
      addr = null;
      seg = null;
   }
   
   // overloaded constructor for Symbol class
   public Symbol(String t, String c, String v, String a, String s)
   {
      tok = t;
      cls = c;
      value = v;
      addr = a;
      seg = s;
   }
   
   // mutator methods for Symbol class
   protected void setToken(String t)
   {
      tok = t;
   }
   
   protected void setCls(String c)
   {
      cls = c;
   }
   
   protected void setValue(String v)
   {
      value = v;
   }
   
   protected void setAddress(String a)
   {
      addr = a;
   }
   
   protected void setSegment(String s)
   {
      seg = s;
   }
   
   // accessor methods for Symbol class
   protected String getToken()
   {
      return tok;
   }
   
   protected String getCls()
   {
      return cls;
   }
   
   protected String getValue()
   {
      return value;
   }
   
   protected String getAddress()
   {
      return addr;
   }
   
   protected String getSegment()
   {
      return seg;
   }
   
   // to string method for printing purposes
   public String toString()
   {
      if (cls.equals("<numLit>"))
      {
         return tok + "\t\t" + cls + "\t" + value + "\t\t" + addr + "\t\t" + seg;
      }
      else
      {
         return tok + "\t\t" + cls + "\t\t" + value + "\t\t" + addr + "\t\t" + seg;
      }
   }
}