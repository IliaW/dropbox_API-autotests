package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class GetAccountResponse {
   @SerializedName("account_id")
   public String accountId;
   @SerializedName("name")
   public Name name;
   @SerializedName("email")
   public String email;
   @SerializedName("email_verified")
   public Boolean emailVerified;
   @SerializedName("disabled")
   public Boolean disabled;
   @SerializedName("is_teammate")
   public Boolean isTeammate;
   @SerializedName("profile_photo_url")
   public String profilePhotoUrl;

   public GetAccountResponse() {
   }

   public GetAccountResponse(String accountId, Name name, String email, Boolean emailVerified, Boolean disabled, Boolean isTeammate, String profilePhotoUrl) {
      this.accountId = accountId;
      this.name = name;
      this.email = email;
      this.emailVerified = emailVerified;
      this.disabled = disabled;
      this.isTeammate = isTeammate;
      this.profilePhotoUrl = profilePhotoUrl;
   }
}