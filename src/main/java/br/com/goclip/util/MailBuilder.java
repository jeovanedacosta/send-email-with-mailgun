package br.com.goclip.util;


import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

import java.io.File;

public class MailBuilder {
    private MultipartBody.Builder responseBody;

    public MailBuilder() {
        this.responseBody = new MultipartBody.Builder().setType(MultipartBody.FORM);
    }

    public MailBuilder from(String from) {
        responseBody.addFormDataPart("from", from);
        return this;
    }

    public MailBuilder to(String to) {
        responseBody.addFormDataPart("to", to);
        return this;
    }

    public MailBuilder cc(String cc) {
        responseBody.addFormDataPart("cc", cc);
        return this;
    }

    public MailBuilder bcc(String bcc) {
        responseBody.addFormDataPart("bcc", bcc);
        return this;
    }

    public MailBuilder subject(String subject) {
        responseBody.addFormDataPart("subject", subject);
        return this;
    }

    public MailBuilder text(String text) {
        responseBody.addFormDataPart("text", text);
        return this;
    }

    public MailBuilder html(String html) {
        responseBody.addFormDataPart("html", html);
        return this;
    }

    public MailBuilder attachment(String fileName, MediaType mediaType) {
        File file = new File(fileName);
        RequestBody requestBody = RequestBody.create(mediaType, file);
        responseBody.addFormDataPart( "attachment", fileName, requestBody);
        return this;
    }

    public MultipartBody build() {
        return responseBody.build();
    }
}
