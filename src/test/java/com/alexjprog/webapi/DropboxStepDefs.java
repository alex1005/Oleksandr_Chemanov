package com.alexjprog.webapi;

import com.alexjprog.webapi.app.DropboxApp;
import io.cucumber.java.bs.A;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import org.junit.Assert;

import java.io.File;

public class DropboxStepDefs {

    private static final String FILES = "src/test/resources/files/";
    private static final DropboxApp APP = new DropboxApp();
    private static HttpResponse<JsonNode> lastRequest;

    //UPLOAD
    @Given("I have a file {string}")
    public void exists(String name) {
        File file = new File(FILES+name);
        Assert.assertTrue("EXISTS: File " + name + " does not exist", file.exists());
    }

    @When("I upload file {string} to Dropbox")
    public void uploadToDropbox(String name) {
        lastRequest = APP.uploadFile(new File(FILES+name));
    }

    @Then("I see file {string} successfully uploaded")
    public void isSuccessfullyUploaded(String name) {
        Assert.assertTrue("UPLOAD FILE: Failed to upload file "+name + " to Dropbox",
                APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess());
    }

    //GET_FILE_METADATA

    @Given("I have an uploaded file {string}")
    public void isUploadedBefore(String name) {
        File file = new File(FILES+name);
        Assert.assertFalse("IS FILE UPLOADED: File " + file.getName() + "is not uploaded", APP.getLastResponse(file.getAbsolutePath()) == null
                || !APP.getLastResponse(file.getAbsolutePath()).isSuccess());
    }

    @When("I request metadata of file {string} by its id")
    public void requestFileMetadata(String name) {
        lastRequest = APP.getFileMetadata(new File(FILES+name));
    }

    @Then("I receive metadata for file {string}")
    public void isMetadataReceived(String name) {
        Assert.assertTrue("RECEIVE FILE METADATA: Failed to get metadata for file "+name,
                APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess());
    }

    @When("I ask to delete file {string}")
    public void deleteFile(String name) {
        lastRequest = APP.deleteFile(new File(FILES+name));
    }

    @Then("I see file {string} is successfully deleted")
    public void isDeletedSuccessfully(String name) {
        Assert.assertTrue("DELETE FILE: Failed to delete file " + name,
                APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess());
    }

    @When("I ask for list of files and folders in {string}")
    public void requestForList(String path) {
        lastRequest = APP.getList(path);
    }

    @Then("I receive list of files and folders")
    public void isListReceived() {
        Assert.assertTrue("RECEIVE LIST: Given wrong path", lastRequest.isSuccess());
    }
}
