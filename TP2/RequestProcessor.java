import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;


public class RequestProcessor {

    private HttpContext httpContext;
    void process(){
        HttpRequest request = httpContext.getRequest();
        HttpResponse response = httpContext.getResponse();
        System.out.println(request.getMethod() + " " + request.getUrl());
        if (request.getMethod().equals("GET")) {
            if (request.getUrl().equals("/")) {
                response.sendFile("text/html", "index.html");

            } else if (request.getUrl().startsWith("/") && request.getUrl().length() > 1) {
                File file = new File("D:\\JavaTP\\TP2\\public\\" + request.getUrl());
                if (file.exists()) {
                    try {
                        String contentType = Files.probeContentType(file.toPath());

                        response.sendFile(contentType, request.getUrl());
                    } catch (IOException e) {
                        response.notFound();
                    }
                } else {
                    response.notFound();
                }
            }
        } else {
            response.notFound();
        }
        httpContext.close();
    }

    public RequestProcessor(Socket socket){
        this.httpContext = new HttpContext(socket);
    }

}

