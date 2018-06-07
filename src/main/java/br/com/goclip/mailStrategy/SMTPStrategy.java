package br.com.goclip.mailStrategy;

import okhttp3.MultipartBody;
import okhttp3.Response;

public class SMTPStrategy implements Sender {


    @Override
    public Response send(MultipartBody responseBody) {
        return null;
    }
}
