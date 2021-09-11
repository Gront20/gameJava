/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.*;
import java.lang.String;

public class Help { 
    public static String help(String z[], int repeat)
    {
        String header = "";
        String[] sts = {"Draw", "WIN", "LOSE"};
        String prob = "|";
        System.out.println("");
        if (getMaxLen(z) <= "  \\User".length()) 
         {
             header += ("  \\User" + String.format("%1s", prob) + getFormatInputs(z, prob).replaceAll("[a-zA-Z0-9]", " ") +" \n"); 
             header += ("PC \\" + String.format("%4s", prob) + getFormatInputs(z, prob) +" \n");
         }
        else {
            header += ("  \\User" + String.format("%" + String.valueOf(getMaxLen(z) - "  \\User".length()) + "s", prob) + getFormatInputs(z, prob).replaceAll("[a-zA-Z0-9]", " ") +" \n"); 
            header += ("PC \\" + String.format("%" + String.valueOf(getMaxLen(z)+3 - "  \\User".length()) + "s", prob) + getFormatInputs(z, prob) +" \n");
        }
        header += (getFormatDash(z) + prob.replace("|", "+") + getFormatInputs(z,prob.replace("|", "+")).replaceAll("[a-zA-Z0-9 ]", "-") + " \n");
        System.out.print(header);
            for (int x = 0; x <= z.length-1; x++)
            {
             header = z[x];
             if (getMaxLen(z) <= "  \\User".length()) 
             {
                  header += String.format("%2s", prob);
             }
             else header += String.format("%" + String.valueOf(getMaxLen(z)-z[x].length()) + "s", prob);
             for (int j = 0; j < z.length; j++){
                     if (x == j){
                         header += getFormatConditions(z, sts, j)[0];
                         header += prob;
                     }
                     else if (j > x + repeat || ((x > j) && (x <= j + repeat))|| (x >=repeat && j > x + repeat)) { 
                         header += getFormatConditions(z, sts,j)[2];
                         header += prob;
                     }
                     else {
                         header += getFormatConditions(z, sts,j)[1];
                         header += prob;
                     } 

             }
         System.out.println(header);
         System.out.println(getFormatDash(z) + prob.replace("|", "+") + getFormatInputs(z,prob.replace("|", "+")).replaceAll("[a-zA-Z0-9 ]", "-"));
        }
        return header;
     }

     public static int getMaxLen(String[] z) 
     {
         int maxlen = 0;
         for (int i = 0; i < z.length; i++)
         {
             int length = z[i].length()+1;
             if (length > maxlen)
                 maxlen = length;
         }
         return maxlen;
     }


     public static String[] getFormatConditions(String[] z, String[] x, int init){
        String str = " ";
        ArrayList<String> ar = new ArrayList<String>();
            for (int j = 0; j < x.length; j++){
                int lenz = z[init].length();
                int lenx = x[j].length();
                if (x[j].equals("WIN")) {
                    if ((lenz - lenx) % 2 == 0) {
                            ar.add(str.repeat((lenz-lenx) / 2) + x[j] + str.repeat((lenz-lenx) / 2));
                    } else if (((lenz - lenx) >= 1) & (lenz - lenx) % 2 != 0 )
                    {
                            ar.add(str.repeat(((lenz-lenx) / 2)) + x[j] + str.repeat(((lenz-lenx) / 2)+1));
                    } else ar.add(x[j]);
                }
                else if (x[j].equals("LOSE")){
                    if ((lenz - lenx) % 2 == 0) 
                        ar.add(str.repeat((lenz-lenx) / 2) + x[j] + str.repeat((lenz-lenx) / 2));
                    else if ((lenz - lenx) % 2 != 0 )
                        ar.add(str.repeat(((lenz-lenx) / 2) + 1 ) + x[j] + str.repeat((lenz-lenx) / 2)); }
                else if (x[j].equals("Draw")){
                    if ((lenz - lenx) % 2 == 0 & (lenz - lenx) > 1) 
                        ar.add(str.repeat((lenz-lenx) / 2) + x[j] + str.repeat((lenz-lenx) / 2));
                    else if ((lenz - lenx) % 2 != 0 )
                        ar.add(str.repeat(((lenz-lenx) / 2) + 1 ) + x[j] + str.repeat((lenz-lenx) / 2)); 
                    else if ((lenz-lenx) <= 0)
                        ar.add(x[j]);
                    }   
            }
            String[] Stringar = ar.toArray(new String[x.length]);
     return Stringar;
    }
    
    public static String getFormatInputs(String[] z, String prob)
    {
        String str = "";
        String str1 = " ";
        for (int i = 0; i < z.length; i++) {
            if ((z[i].length() < 6) && (z[i].length() % 2 == 0)) 
                z[i] = str1.repeat((6 - z[i].length()) / 2) + z[i] + str1.repeat((6 - z[i].length()) / 2);
            else if ((z[i].length() < 6) && (z[i].length() % 2 != 0))
                z[i] = str1.repeat((6 - z[i].length()) / 2) + z[i] + str1.repeat((6 - z[i].length()) / 2 +  1);
            str = str + z[i] + prob;
        }
        return str;
    }
    
    public static String getFormatDash(String[] z) 
    {
        String str = "-";
        String str1 = "";
        int lenght = 0; 
        if (getMaxLen(z) <= "  \\User".length())
            lenght = getMaxLen(z);
        else lenght = getMaxLen(z)-1;
        for (int i = 0; i < lenght; i++)
        {
            str1 = str1 + str;
        }
        return str1;
    }
}