package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class GetCurrentAccountResponse {

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
   @SerializedName("locale")
   public String locale;
   @SerializedName("referral_link")
   public String referralLink;
   @SerializedName("is_paired")
   public Boolean isPaired;
   @SerializedName("account_type")
   public AccountType accountType;
   @SerializedName("root_info")
   public RootInfo rootInfo;
   @SerializedName("profile_photo_url")
   public String profilePhotoUrl;
   @SerializedName("country")
   public String country;

   public GetCurrentAccountResponse() {
   }

   public GetCurrentAccountResponse(String accountId, Name name, String email, Boolean emailVerified, Boolean disabled,
                                    String locale, String referralLink, Boolean isPaired, AccountType accountType,
                                    RootInfo rootInfo, String profilePhotoUrl, String country) {
      this.accountId = accountId;
      this.name = name;
      this.email = email;
      this.emailVerified = emailVerified;
      this.disabled = disabled;
      this.locale = locale;
      this.referralLink = referralLink;
      this.isPaired = isPaired;
      this.accountType = accountType;
      this.rootInfo = rootInfo;
      this.profilePhotoUrl = profilePhotoUrl;
      this.country = country;
   }

}