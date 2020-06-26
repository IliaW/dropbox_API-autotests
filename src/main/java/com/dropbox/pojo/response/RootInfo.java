package com.dropbox.pojo.response;

import com.google.gson.annotations.SerializedName;

public class RootInfo {

   @SerializedName(".tag")
   public String tag;
   @SerializedName("root_namespace_id")
   public String rootNamespaceId;
   @SerializedName("home_namespace_id")
   public String homeNamespaceId;

   public RootInfo(String tag, String rootNamespaceId, String homeNamespaceId) {
      this.tag = tag;
      this.rootNamespaceId = rootNamespaceId;
      this.homeNamespaceId = homeNamespaceId;
   }

}
