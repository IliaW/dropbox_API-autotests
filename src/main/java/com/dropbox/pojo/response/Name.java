package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class Name {

   @SerializedName("given_name")
   public String givenName;
   @SerializedName("surname")
   public String surname;
   @SerializedName("familiar_name")
   public String familiarName;
   @SerializedName("display_name")
   public String displayName;
   @SerializedName("abbreviated_name")

   public String abbreviatedName;

   public Name(String givenName, String surname, String familiarName, String displayName, String abbreviatedName) {
      this.givenName = givenName;
      this.surname = surname;
      this.familiarName = familiarName;
      this.displayName = displayName;
      this.abbreviatedName = abbreviatedName;
   }
}
