package com.dropbox.tests;

import com.dropbox.Launcher;
import com.dropbox.data.AccessToken;
import com.dropbox.data.Image;
import com.dropbox.httpclient.ContentType;
import com.dropbox.httpclient.PostRequest;
import com.dropbox.httpclient.Response;
import com.dropbox.pojo.request.SetProfilePhotoRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class Account extends Launcher {

   private final String ACCOUNT_URL = "https://api.dropboxapi.com/2/account/";

   @Test
   public void setProfilePhotoBySupportedFileType() throws IOException {
      Response response = new PostRequest()
              .url(ACCOUNT_URL + "set_profile_photo")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new SetProfilePhotoRequest(Image.ACCOUNT_PHOTO_JPG))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(2500);
   }

   @Test
   public void setProfilePhotoByUnsupportedFileType() throws IOException {
      Response response = new PostRequest()
              .url(ACCOUNT_URL + "set_profile_photo")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new SetProfilePhotoRequest(Image.ACCOUNT_PHOTO_PDF))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("file_type_error");
      assertThat(response.getTimeExecutionByMs()).isLessThan(2500);
   }

   @Test
   public void setProfilePhotoByUnsupportedFileSize() throws IOException {
      Response response = new PostRequest()
              .url(ACCOUNT_URL + "set_profile_photo")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new SetProfilePhotoRequest(Image.ACCOUNT_PHOTO_10MB))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("file_size_error");
      assertThat(response.getTimeExecutionByMs()).isLessThan(18000);
   }

   @Test
   public void setProfilePhotoByUnsupportedFileDimension() throws IOException {
      Response response = new PostRequest()
              .url(ACCOUNT_URL + "set_profile_photo")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new SetProfilePhotoRequest(Image.ACCOUNT_PHOTO_120x120px))
              .execute();
      System.out.println(response.getJson());
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("dimension_error");
      assertThat(response.getTimeExecutionByMs()).isLessThan(2500);
   }
}