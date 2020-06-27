package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.IOException;

import static com.dropbox.helpers.EncoderHelper.encodeFileToBase64;

public class SetProfilePhotoRequest extends RequestPOJO {

   @SerializedName("photo")
   Photo photo;

   public SetProfilePhotoRequest(File photo) throws IOException {
      this.photo = new Photo(encodeFileToBase64(photo));
   }
}