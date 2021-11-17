package com.surjo.sheetloader;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.sheets.v4.Sheets;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class SheetsService {

    @Value("${com.surjo.application-name}")
    private String applicationName;

    public Sheets getSheets() throws IOException, GeneralSecurityException {
        Credential credential = GoogleAuthorizeUtil.authorize();
        return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(),
                JacksonFactory.getDefaultInstance(), credential).setApplicationName(applicationName).build();

//        NetHttpTransport transport = new NetHttpTransport.Builder().build();
//        JacksonFactory jsonFactory = JacksonFactory.getDefaultInstance();
//        HttpRequestInitializer httpRequestInitializer = request -> {
//            request.setInterceptor(intercepted -> intercepted.getUrl().set("key", "AIzaSyDx6BuPAwCdgFzjuRuO27XOKgsUUQlkZVk"));
//        };
//
//        return new Sheets.Builder(transport, jsonFactory, httpRequestInitializer)
//                .setApplicationName(applicationName)
//                .build();
    }

}
