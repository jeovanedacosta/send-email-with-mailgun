package br.com.goclip.mailStrategy;

import okhttp3.MultipartBody;
import okhttp3.Response;

public interface Sender {

    Response send(MultipartBody responseBody);
}
