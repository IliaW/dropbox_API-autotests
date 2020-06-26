package com.dropbox.tests;

import com.dropbox.Launcher;
import com.dropbox.data.AccessToken;
import com.dropbox.httpclient.PostRequest;
import com.dropbox.httpclient.Response;
import com.dropbox.pojo.request.GetAccountRequest;
import com.dropbox.pojo.response.GetAccountResponse;
import com.dropbox.pojo.response.GetCurrentAccountResponse;
import com.dropbox.pojo.response.GetSpaceUsageResponse;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class Users extends Launcher {

   private final String USERS_URL = "https://api.dropboxapi.com/2/users/";

   @Test
   public void getCurrentAccountWithValidAccessToken() throws IOException {
      Response response = new PostRequest()
              .url(USERS_URL + "get_current_account")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .execute();
      getCurrentAccountResponse = response.parseJsonToObject(GetCurrentAccountResponse.class);
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test
   public void getCurrentAccountWithInvalidAccessToken() throws IOException {
      Response response = new PostRequest()
              .url(USERS_URL + "get_current_account")
              .header("authorization", "Bearer 1ZNZgYBNSgAAAAAAAAAB2vbTtfyil0ljA8G8gdtki0JiNQZEnb_81-hDWz2aXIKB")
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_UNAUTHORIZED);
      assertThat(response.getJson()).contains("invalid_access_token");
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test(dependsOnMethods = "getCurrentAccountWithValidAccessToken")
   public void getAccountWithValidId() throws IOException {
      Response response = new PostRequest()
              .url(USERS_URL + "get_account")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header("content-type", "application/json")
              .jsonBody(new GetAccountRequest(getCurrentAccountResponse.accountId))
              .execute();
      getAccountResponse = response.parseJsonToObject(GetAccountResponse.class);
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test
   public void getAccountWithInvalidId() throws IOException {
      Response response = new PostRequest()
              .url(USERS_URL + "get_account")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header("content-type", "application/json")
              .jsonBody(new GetAccountRequest("dbid:BABBcTGtr-BjUruSIAh4Qe1udNgPxe-7PjQ"))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("no_account");
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test
   public void getSpaceUsage() throws IOException {
      Response response = new PostRequest()
              .url(USERS_URL + "get_space_usage")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .execute();
      getSpaceUsageResponse = response.parseJsonToObject(GetSpaceUsageResponse.class);
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(getSpaceUsageResponse.allocation.allocated).isEqualTo(2147483648L); //Space for Basic user
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);

   }
}