package com.dropbox.tests;

import com.dropbox.data.AccessToken;
import com.dropbox.data.Document;
import com.dropbox.data.Video;
import com.dropbox.httpclient.ContentType;
import com.dropbox.httpclient.PostRequest;
import com.dropbox.httpclient.Response;
import com.dropbox.pojo.request.CreateFolderRequest;
import com.dropbox.pojo.request.DeleteRequest;
import com.dropbox.pojo.request.UploadRequest;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.io.IOException;

import static com.dropbox.helpers.RandomHelper.getRandomNumber;
import static org.assertj.core.api.Assertions.assertThat;

public class Files {

   private final String CONTENT_FILES_URL = "https://content.dropboxapi.com/2/files/";
   private final String API_FILES_URL = "https://api.dropboxapi.com/2/files/";


   @Test
   public void uploadPDFFile() throws IOException {
      Response response = new PostRequest()
              .url(CONTENT_FILES_URL + "upload")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header("dropbox-api-arg", new UploadRequest("/How Google Tests Software.pdf"))
              .header(ContentType.APPLICATION_OCTET_STREAM)
              .fileBody(Document.HOW_GOOGLE_TESTS_SOFTWARE_PDF)
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(10000);
   }

   @Test
   public void uploadMP4File() throws IOException {
      Response response = new PostRequest()
              .url(CONTENT_FILES_URL + "upload")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header("dropbox-api-arg", new UploadRequest("/Surprised Kitty.mp4"))
              .header(ContentType.APPLICATION_OCTET_STREAM)
              .fileBody(Video.SURPRISED_KITTY_MP4)
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(10000);
   }

   @Test
   public void createFolder() throws IOException {
      Response response = new PostRequest()
              .url(API_FILES_URL + "create_folder_v2")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new CreateFolderRequest("/Folder " + getRandomNumber()))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test
   public void createFolderWithNotAllowedSymbols() throws IOException {
      Response response = new PostRequest()
              .url(API_FILES_URL + "create_folder_v2")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new CreateFolderRequest("/Folder: ? * | "))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("malformed_path");
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test(dependsOnMethods = "uploadPDFFile")
   public void deleteExistedFile() throws IOException {
      Response response = new PostRequest()
              .url(API_FILES_URL + "delete_v2")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new DeleteRequest("/How Google Tests Software.pdf"))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_OK);
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }

   @Test
   public void deleteNotExistedFile() throws IOException {
      Response response = new PostRequest()
              .url(API_FILES_URL + "delete_v2")
              .header("authorization", "Bearer " + AccessToken.BASIC_USER)
              .header(ContentType.APPLICATION_JSON)
              .jsonBody(new DeleteRequest("/Not existed file.pdf"))
              .execute();
      assertThat(response.getStatusCode()).isEqualTo(HttpStatus.SC_CONFLICT);
      assertThat(response.getJson()).contains("not_found");
      assertThat(response.getTimeExecutionByMs()).isLessThan(1000);
   }
}