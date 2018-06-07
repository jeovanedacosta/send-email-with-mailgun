package br.com.goclip.mailStrategy;

import com.github.racc.tscg.TypesafeConfig;
import com.google.inject.Inject;
import okhttp3.*;

import java.io.IOException;

public class APIStrategy implements Sender {
    public final String key;
    public final String url;
    public final String userName;
    public final OkHttpClient client;

    @Inject
    public APIStrategy(@TypesafeConfig("mailgun.key") String key,
                       @TypesafeConfig("mailgun.url") String url,
                       @TypesafeConfig("mailgun.username") String userName) {

        this.key = key;
        this.url = url;
        this.userName = userName;
        this.client = new OkHttpClient();
    }

    @Override
    public Response send(MultipartBody responseBody) {
        try {
            Request request = new Request.Builder()
                    .url(url)
                    .addHeader("Authorization", Credentials.basic(userName, key))
                    .post(responseBody)
                    .build();
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
