
import java.io.BufferedReader;
import java.io.IOException;

import java.io.InputStreamReader;


import java.net.Socket;
public class HttpRequest {
    private String method;
    private String url;
    private void readClientRequest(Socket socket) {

        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        String requestLine = String.valueOf(0);
        try {
            if (reader != null) {
                requestLine = reader.readLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        String httpMethod = String.valueOf(0);
        if (requestLine != null) {
            httpMethod = requestLine.split(" ")[0];
        }
        String urlRequest = String.valueOf(0);
        if (requestLine != null) {
            urlRequest = requestLine.split(" ")[1];
        }
        method = httpMethod;
        url = urlRequest;

    }
    public HttpRequest(Socket socket) {
        readClientRequest(socket);
    }
    public String getMethod() {
        return method;
    }
    public String getUrl() {
        return url;
    }
}
