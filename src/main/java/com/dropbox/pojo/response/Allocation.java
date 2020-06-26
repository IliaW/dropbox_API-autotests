package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class Allocation {

   @SerializedName(".tag")
   public String tag;
   @SerializedName("allocated")
   public Long allocated;

   public Allocation() {
   }

   public Allocation(String tag, Long allocated) {
      super();
      this.tag = tag;
      this.allocated = allocated;
   }
}