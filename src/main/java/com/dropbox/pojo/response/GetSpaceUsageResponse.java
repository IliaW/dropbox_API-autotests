package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class GetSpaceUsageResponse {

   @SerializedName("used")
   public Integer used;
   @SerializedName("allocation")
   public Allocation allocation;

   public GetSpaceUsageResponse() {
   }

   public GetSpaceUsageResponse(Integer used, Allocation allocation) {
      super();
      this.used = used;
      this.allocation = allocation;
   }
}