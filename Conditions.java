/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;

/**
 *
 * @author Artem
 */
public class Conditions {
    public static int[][] getCoordList(String[] z, int[][] coordWIN, int[] RandomNum)
     {
        for (int x = 1; x <= z.length; x++)
        {
         for (int j = 1; j < z.length+1; j++){
                 if (x == j){
                     coordWIN[x][j] = 2;
                 }
                 else if (RandomNum[j+x-2] % 2 == 0){
                     coordWIN[x][j] = 1;
                 }
                 else if (RandomNum[j+x-2] % 2 != 0) {
                     coordWIN[x][j] = 0;
                 }
         } 
     }
        return coordWIN;
     }
    
        public static int[] getRandomNumbers(String[] z) {
        int rand = 0;
        int[] randNum = new int[z.length*z.length]; 
        for (int i = 0; i < z.length*z.length; i++) {
            Random random = new Random();
            rand = random.nextInt(z.length+20);
            randNum[i] = rand;
        }
            return randNum;
        }
}
