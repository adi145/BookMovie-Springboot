package com.spapr.bookshow.Payload;

import com.spapr.bookshow.model.FileUpload;

public class UploadFileResponse {
    private String id;
    private String fileName;
    private String fileDownloadUri;
    private String fileType;
    private long size;
    private FileUpload fileUpload;

    public UploadFileResponse(String fileName, String fileDownloadUri, String fileType, long size, String id,FileUpload fileUpload) {
        this.fileName = fileName;
        this.fileDownloadUri = fileDownloadUri;
        this.fileType = fileType;
        this.size = size;
        this.id = id;
        this.fileUpload =fileUpload;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileDownloadUri() {
        return fileDownloadUri;
    }

    public void setFileDownloadUri(String fileDownloadUri) {
        this.fileDownloadUri = fileDownloadUri;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public FileUpload getFileUpload() {
        return fileUpload;
    }

    public void setFileUpload(FileUpload fileUpload) {
        this.fileUpload = fileUpload;
    }
}
