package com.alexjprog.webapi;

import com.alexjprog.webapi.app.DropboxApp;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;

import java.io.File;

public class DropboxStepDefs {

    private static final String FILES = "src/test/resources/files/";
    private static final DropboxApp APP = new DropboxApp();
    private static HttpResponse<JsonNode> lastRequest;

    //UPLOAD
    @Given("I have a file {string}")
    public void exists(String name) {
        File file = new File(FILES+name);
        if(!file.exists()) throw new IllegalArgumentException("File " + name + " does not exist");
    }

    @When("I upload file {string} to Dropbox")
    public void uploadToDropbox(String name) {
        lastRequest = APP.uploadFile(new File(FILES+name));
    }

    @Then("I see file {string} successfully uploaded")
    public void isSuccessfullyUploaded(String name) {
        if(!APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess())
            throw new IllegalStateException("Failed to upload file "+name + " to Dropbox");
    }

    //GET_FILE_METADATA

    @Given("I have an uploaded file {string}")
    public void isUploadedBefore(String name) {
        File file = new File(FILES+name);
        if(APP.getLastResponse(file.getAbsolutePath()) == null || !APP.getLastResponse(file.getAbsolutePath()).isSuccess())
            throw new IllegalArgumentException("File " + file.getName() + "is not uploaded");
    }

    @When("I request metadata of file {string} by its id")
    public void requestFileMetadata(String name) {
        lastRequest = APP.getFileMetadata(new File(FILES+name));
    }

    @Then("I receive metadata for file {string}")
    public void isMetadataReceived(String name) {
        if(!APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess())
            throw new IllegalStateException("Failed to get metadata for file "+name);
    }

    @When("I ask to delete file {string}")
    public void deleteFile(String name) {
        lastRequest = APP.deleteFile(new File(FILES+name));
    }

    @Then("I see file {string} is successfully deleted")
    public void isDeletedSuccessfully(String name) {
        if(!APP.getLastResponse(new File(FILES+name).getAbsolutePath()).isSuccess())
            throw new IllegalStateException("Failed to delete file " + name);
    }

    @When("I ask for list of files and folders in {string}")
    public void requestForList(String path) {
        lastRequest = APP.getList(path);
    }

    @Then("I receive list of files and folders")
    public void isListReceived() {
        if(!lastRequest.isSuccess())
            throw new IllegalArgumentException("Given wrong path");
    }
}
