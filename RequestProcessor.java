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
        if (request.getMethod().equals("GET")) {
            if (request.getUrl().equals("/")) {
                response.sendFile("text/html", "index.html");

            } else if (request.getUrl().startsWith("/") && request.getUrl().length() > 1) {
                File file = new File("D:\\TP2java\\src\\public\\" + request.getUrl());
                if (file.exists()) {
                    try {
                        String contentType = Files.probeContentType(file.toPath());
                        if (request.getUrl().endsWith(".png")){
                            contentType = "image/png";
                        }
                        if(request.getUrl().endsWith(".jpg")){
                            contentType = "image/jpg";
                        }
                        if(request.getUrl().endsWith(".svg")){
                            contentType = "image/svg+xml";
                        }
                        if(request.getUrl().endsWith(".webp")){
                            contentType = "image/webp";
                        }
                        if(request.getUrl().endsWith(".mp4")){
                            contentType = "video/mp4";
                        }

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
