package com.dropbox.tests;

import com.dropbox.Launcher;
import com.dropbox.data.AccessToken;
import com.dropbox.data.Image;
import com.dropbox.httpclient.PostRequest;
import com.dropbox.httpclient.Response;
import com.dropbox.pojo.request.SetProfilePhotoRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.dropbox.helpers.EncoderHelper.encodeFileToBase64;
import static org.assertj.core.api.Assertions.assertThat;

public class Account extends Launcher {

   private final String ACCOUNT_URL = "https://api.dropboxapi.com/2/account/";

   @Test
   public void setProfilePhotoByJPGFormat() throws IOException {
      Response response = new PostRequest()
              .url(ACCOUNT_URL + "set_profile_photo")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header("content-type", "application/json")
              .jsonBody(new SetProfilePhotoRequest(encodeFileToBase64(Image.accountPhotoJPG)))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(2000);
   }
}