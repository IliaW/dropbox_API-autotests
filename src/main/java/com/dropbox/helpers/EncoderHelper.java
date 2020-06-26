package com.dropbox.helpers;

import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

public class EncoderHelper {

   public static String encodeFileToBase64(File file) {
      try {
         return Base64.getEncoder().encodeToString(Files.toByteArray(file));
      } catch (IOException e) {
         e.printStackTrace();
         return "";
      }
   }
}