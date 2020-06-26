package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class Photo {

   @SerializedName(".tag")
   String tag;
   @SerializedName("base64_data")
   String base64Data;

   public Photo(String accountPhoto) {
      this.tag = "base64_data";
      this.base64Data = accountPhoto;
   }
}