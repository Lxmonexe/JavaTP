import java.net.Socket;

public class HttpContext {
    private Socket socket;

    public HttpContext(Socket socket) {
        this.socket = socket;
    }

    public HttpRequest getRequest() {
        return new HttpRequest(socket);
    }

    public HttpResponse getResponse() {
        return new HttpResponse(socket);
    }

    public void close() {
        try {
            socket.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

