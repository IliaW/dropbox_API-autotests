package com.dropbox.httpclient;

import com.google.gson.Gson;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class Response {

   private CloseableHttpResponse httpResponse;
   private String jsonResponse;
   private long timeExecution = Long.MAX_VALUE;


   public Response(CloseableHttpResponse httpResponse) {
      this.httpResponse = httpResponse;
   }

   public Response(CloseableHttpResponse httpResponse, long timeExecution) {
      this.httpResponse = httpResponse;
      this.timeExecution = timeExecution;
   }

   public int getStatusCode() {
      return httpResponse.getStatusLine().getStatusCode();
   }

   public String getJson() throws IOException {
      if (jsonResponse == null) {
         jsonResponse = EntityUtils.toString(httpResponse.getEntity());
      }
      return jsonResponse;
   }

   public <T> T parseJsonToObject(Class<T> classOf) throws IOException {
      return new Gson().fromJson(getJson(), classOf);
   }

   public long getTimeExecutionByMs() {
      return timeExecution;
   }
}