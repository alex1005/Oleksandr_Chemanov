package com.alexjprog.webapi.app;

import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class DropboxApp {
    private final String oauth_key = "wZZQsVT3UFoAAAAAAAAAAVHYxjLQUSCa56IleSYG3G6JZF0ueKrSnqjt4YOmkHfC";

    private Map<String, HttpResponse<JsonNode>> lastResponses = new HashMap<>();

    public HttpResponse<JsonNode> uploadFile(File file) {
        return lastResponses.put(file.getAbsolutePath(), Unirest.post("https://content.dropboxapi.com/2/files/upload")
                .header("Dropbox-API-Arg", "{\"path\": \"/" + file.getName() + "\",\"mode\": \"add\",\"autorename\": true,\"mute\": false,\"strict_conflict\": false}")
                .header("Content-Type", "application/octet-stream")
                .header("Authorization", "Bearer "+oauth_key)
                .body(file.getAbsolutePath()).asJson());
    }

    public HttpResponse<JsonNode> getFileMetadata(File file) {
        return lastResponses.put(file.getAbsolutePath(), Unirest.post("https://api.dropboxapi.com/2/sharing/get_file_metadata")
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+oauth_key)
                .body("{\r\n    \"file\": \""+ getFileId(file) + "\",\r\n    \"actions\": []\r\n}")
                .asJson());
    }

    public HttpResponse<JsonNode> deleteFile(File file) {
        return lastResponses.put(file.getAbsolutePath(), Unirest.post("https://api.dropboxapi.com/2/files/delete_v2")
                .header("Authorization", "Bearer "+oauth_key)
                .header("Content-Type", "application/json")
                .body("{\r\n    \"path\": \"/" + file.getName() + "\"\r\n}")
                .asJson());
    }

    public HttpResponse<JsonNode> getList(String path) {
        return Unirest.post("https://api.dropboxapi.com/2/files/list_folder")
                .header("Authorization", "Bearer "+oauth_key)
                .header("Content-Type", "application/json")
                .body("{\r\n    \"path\": \"" + path +"\",\r\n    \"recursive\": false,\r\n    \"include_media_info\": false,\r\n    \"include_deleted\": false,\r\n    \"include_has_explicit_shared_members\": false,\r\n    \"include_mounted_folders\": true,\r\n    \"include_non_downloadable_files\": true\r\n}")
                .asJson();
    }

    private String getFileId(File file) {
        return lastResponses.get(file.getAbsolutePath()).getBody().getObject().getString("id");
    }

    public HttpResponse<JsonNode> getLastResponse(String file) {
        return lastResponses.get(file);
    }
}
