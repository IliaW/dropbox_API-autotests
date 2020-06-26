package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class SetProfilePhotoRequest extends RequestPOJO {

   @SerializedName("photo")
   Photo photo;

   public SetProfilePhotoRequest(String photoBase64) {
      this.photo = new Photo(photoBase64);
   }
}