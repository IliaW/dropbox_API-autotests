package com.dropbox.pojo.request;

import com.google.gson.annotations.SerializedName;

public class GetAccountRequest extends RequestPOJO {

   @SerializedName("account_id")
   public String accountId;

   public GetAccountRequest(String accountId) {
      this.accountId = accountId;
   }
}