package org.mnessim;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;



class Fetcher {
    public static int httpGet(String urlStr) throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
            .uri(URI.create(urlStr))
            .GET()
            .build();

        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        System.out.println("Status Code: "+ response.statusCode());
        System.out.println("Body: " + response.body());

        return response.statusCode();
    }
}
