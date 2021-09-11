/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.*;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;



public class game {
    public static void main(String args[] ) throws NoSuchAlgorithmException, InvalidKeyException, IllegalStateException, UnsupportedEncodingException 
    {
      String nec[] = args;
      int neclen = nec.length;
      Help help = new Help();
      Conditions conditions = new Conditions();
      HMACkey hmak = new HMACkey();
      boolean flag = true;
      int[][] coord = new int[nec.length+1][nec.length+1];
      int[] randomNumbers = conditions.getRandomNumbers(nec);
      int[][] coord_New = conditions.getCoordList(nec, coord, randomNumbers);
      while (flag == true) {
        if (neclen < 3 || neclen % 2 == 0) {
                System.out.println("Error. Wrong parameters. Please, input the correct numbers of words (>=3 and odd). For example: cat dog lion");
                flag = false;
            }
        else if (!getRepeatableWords(nec).isEmpty()){
            System.out.print("Error. Duplicate values. Please, input the correct words. For example: cat dog lion");
            flag = false;   
        }
        else {
                try {
                    String computerMove = getComputerMove(nec);
                    System.out.println(hmak.HMAC(computerMove));
                    getMenu(nec);
                    System.out.print("Enter your move: ");
                    String playerMove = getPlayerMove();
                    if (playerMove.equals("?") || playerMove.equals("0"))
                    {
                        if (playerMove.equals("?")){
                            help.help(nec, randomNumbers);
                            System.out.print("Enter your move: ");
                            playerMove = getPlayerMove();
                        }
                        if (playerMove.equals("0")) 
                        {
                            flag = false;
                        }
                    }
                    else {
                        
                        if (Integer.parseInt(playerMove) > neclen)
                        System.out.println("Error, wrong number");
                        else {
                            System.out.println("Your move: " + playerMove);
                            System.out.println("Computer move: " + computerMove);
                            if (coord_New[Integer.parseInt(playerMove)][Integer.parseInt(computerMove)] == 2)
                                System.out.println("Game is Tie !!");         
                            else {
                                if (coord_New[Integer.parseInt(playerMove)][Integer.parseInt(computerMove)] == 1)
                                    System.out.println("You win");
                                else if (coord_New[Integer.parseInt(playerMove)][Integer.parseInt(computerMove)] == 0)
                                    System.out.println("Computer Win");
                            }
                            System.out.println(hmak.generateKey());
                        }
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error, not a number");
                }
        }
        } 
    }
    

    public static void getMenu(String z[])
    {
        System.out.println("Available moves:  ");
        for (int i = 1; i <= z.length; i++)
        {
            System.out.println(String.valueOf(i) + " - " + z[i-1]);
        }
        System.out.println("0 - exit");
        System.out.println("? - help");
        System.out.println(); 
    }
    
    public static String getComputerMove(String z[])
    {
        String computermove;
        Random random = new Random();
        int input = random.nextInt(z.length)+1;
        computermove = String.valueOf(input);
        return computermove;    
    }
    
    /* Get Player's move using Scanner
       class */
    public static String getPlayerMove()
    {
        Scanner in = new Scanner(System.in);
        String input = in.next();
        String playermove = input.toUpperCase();
        return playermove;
    }
    
    
    public static Collection<Integer> getRepeatableWords(String[] z){
        Map<String, Integer> occurrences = new HashMap<String, Integer>();
        for ( String word : z ) {
           Integer oldCount = occurrences.get(word);
           if ( oldCount == null ) {
              oldCount = 0;
           }
           occurrences.put(word, oldCount + 1);
        }
        occurrences.values().removeIf((s) -> s.equals(1));
        return occurrences.values();
    }
}
