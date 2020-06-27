package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class DeleteRequest extends RequestPOJO {

   @SerializedName("path")
   public String path;

   public DeleteRequest() {
   }

   public DeleteRequest(String path) {
      this.path = path;
   }
}