package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class AccountType {

   @SerializedName(".tag")
   public String tag;

   public AccountType(String tag) {
      this.tag = tag;
   }
}