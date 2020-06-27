package com.dropbox.helpers;

public class RandomHelper {

   public static int getRandomNumber() {
      return 100 + (int) (Math.random() * 1000);
   }
}