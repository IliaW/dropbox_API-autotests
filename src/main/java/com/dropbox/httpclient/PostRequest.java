package com.dropbox.httpclient;

import com.dropbox.pojo.request.RequestPOJO;
import com.google.gson.Gson;
import org.apache.http.Header;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.client.LaxRedirectStrategy;

import java.io.IOException;
import java.net.URI;

public class PostRequest {
   private final CloseableHttpClient httpClient;
   private final HttpPost httpPost;
   private long timeExecution;

   public PostRequest() {
      int connectionTimeoutByMs = 4000;
      RequestConfig timeouts = RequestConfig.custom()
              .setConnectTimeout(connectionTimeoutByMs)
              .setConnectionRequestTimeout(connectionTimeoutByMs)
              .setSocketTimeout(connectionTimeoutByMs).build();
      httpClient = HttpClients.custom().
              setRedirectStrategy(new LaxRedirectStrategy()).
              setDefaultRequestConfig(timeouts).build();
      httpPost = new HttpPost();
   }

   public PostRequest url(String url) {
      httpPost.setURI(URI.create(url));
      return this;
   }

   public PostRequest header(Header header) {
      httpPost.addHeader(header);
      return this;
   }

   public PostRequest header(String name, String value) {
      httpPost.addHeader(name, value);
      return this;
   }

   public PostRequest jsonBody(String jsonBody) {
      httpPost.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
      return this;
   }

   public PostRequest jsonBody(RequestPOJO object) {
      httpPost.setEntity(new StringEntity(new Gson().toJson(object), ContentType.APPLICATION_JSON));
      return this;
   }

   public Response execute() throws IOException {
      long startExecution = System.currentTimeMillis();
      CloseableHttpResponse response = httpClient.execute(httpPost);
      timeExecution = System.currentTimeMillis() - startExecution;
      return new Response(response, timeExecution);
   }
}