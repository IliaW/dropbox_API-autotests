package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class CreateFolderRequest extends RequestPOJO {

   @SerializedName("path")
   public String path;
   @SerializedName("autorename")
   public Boolean autoRename;

   public CreateFolderRequest() {
   }

   public CreateFolderRequest(String path) {
      this.path = path;
      this.autoRename = false;
   }

   public CreateFolderRequest(String path, Boolean autoRename) {
      this.path = path;
      this.autoRename = autoRename;
   }
}