package com.dropbox.httpclient;

import org.apache.http.Header;
import org.apache.http.message.BasicHeader;

public class ContentType {

   public static final Header APPLICATION_OCTET_STREAM =
           new BasicHeader("content-type", "application/octet-stream");
   public static final Header APPLICATION_JSON =
           new BasicHeader("content-type", "application/json");
}