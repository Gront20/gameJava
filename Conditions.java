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
    public static int[][] getCoordList(String[] z, int[][] coordWIN, int repeat)
     {
        for (int x = 1; x <= z.length; x++)
        {
         for (int j = 1; j < z.length+1; j++){
                 if (x == j){
                     coordWIN[x][j] = 2;
                 }
                 else if (j > x + repeat || ((x > j) && (x <= j + repeat))|| (x >=repeat && j > x + repeat)){
                     coordWIN[x][j] = 0;
                 }
                 else {
                     coordWIN[x][j] = 1;
                 }
         } 
     }
        return coordWIN;
     }
    
        public static int[] getRandomNumbers(String[] z) {
        int[] rand = new int[z.length*z.length];
        for (int i = 0; i < z.length*z.length; i++) {
            rand[i] = i;
        }
            return rand;
        }
}
