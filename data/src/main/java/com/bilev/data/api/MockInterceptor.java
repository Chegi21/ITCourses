package com.bilev.data.api;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class MockInterceptor implements Interceptor {

    private final Context context;

    public MockInterceptor(Context context) {
        this.context = context.getApplicationContext();
    }

    @NonNull
    @Override
    public Response intercept(Chain chain) throws IOException {
        String url = chain.request().url().toString();
        String body = "";

        if (url.contains("courses.json")) {
            body = loadJsonFromAssets();
        }

        return new Response.Builder()
                .code(200)
                .message("OK")
                .request(chain.request())
                .protocol(Protocol.HTTP_1_1)
                .body(ResponseBody.create(body, MediaType.parse("application/json")))
                .addHeader("content-type", "application/json")
                .build();
    }

    private String loadJsonFromAssets() throws IOException {
        InputStream is = context.getAssets().open("courses.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        //noinspection ResultOfMethodCallIgnored
        is.read(buffer);
        is.close();
        return new String(buffer, StandardCharsets.UTF_8);
    }
}
