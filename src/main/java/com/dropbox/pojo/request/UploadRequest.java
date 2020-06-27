package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class UploadRequest extends RequestPOJO {

   @SerializedName("path")
   public String path;
   @SerializedName("mode")
   public String mode;
   @SerializedName("autorename")
   public Boolean autorename;
   @SerializedName("mute")
   public Boolean mute;
   @SerializedName("strict_conflict")
   public Boolean strictConflict;

   public UploadRequest() {
   }

   public UploadRequest(String dropboxPath) {
      super();
      this.path = dropboxPath;
      this.mode = "add";
      this.autorename = true;
      this.mute = false;
      this.strictConflict = false;
   }

   public UploadRequest(String path, String mode, Boolean autorename, Boolean mute, Boolean strictConflict) {
      super();
      this.path = path;
      this.mode = mode;
      this.autorename = autorename;
      this.mute = mute;
      this.strictConflict = strictConflict;
   }
}