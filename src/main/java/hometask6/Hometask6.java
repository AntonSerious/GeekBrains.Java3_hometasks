package hometask6;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.logging.*;

public class Hometask6 {
    //private static final Logger logger = Logger.getLogger(Hometask6.class.getName());

    public static void main(String[] args) throws Exception {


        int[] arr0 = new int[]{2,3,4,5,6};
        System.out.println(onesAndFoursChecker(arr0));
    }

    public static int[] arrHandler(int[] a) throws Exception {
        int lastFourIndex = 0;
        for (int i = a.length-1; i >=0 ; i--) {
            if(a[i] == 4){
                lastFourIndex = i;
                break;
            }
        }
        if(lastFourIndex == 0){
            throw new RuntimeException("no four number in array exception");
        }
        int[] newArr = new int[a.length - lastFourIndex-1];
        for (int i = 0; i <= newArr.length-1; i++) {
            newArr[i] = a[lastFourIndex+i+1];
        }
        return newArr;
    }


    public static boolean onesAndFoursChecker(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            if(arr[i] == 1 || arr[i] == 4){
                return true;
            }
            return false;
        }
        return false;
    }
}

