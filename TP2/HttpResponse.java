import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Paths;

public class HttpResponse {
    private OutputStream output;

    public HttpResponse(Socket socket) {
        try {
            output = socket.getOutputStream();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
    public void ok() {
        try {
            output.write("HTTP/1.1 200 OK\n".getBytes());
            output.flush();

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    public void notFound() {
        try {
            output.write("HTTP/1.1 404 Not Found\n".getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());

        }
    }

    public void sendContent(String contentType, String content) {
        try {
            output.write("HTTP/1.1 200 OK\n".getBytes());
            output.write(("Content-Type: " + contentType + "\n\n").getBytes());
            output.write(content.getBytes());
            output.flush();
            output.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    public void sendFile(String contentType, String filename) {
            try {
                ok();
                output.write(("Content-Type: " + contentType + "\n\n").getBytes());
                FileInputStream fileInputStream = new FileInputStream("D:\\TP2java\\src\\public\\"+filename);
                byte[] buffer = new byte[4096];
                int bytesRead = 0;
                do {
                    bytesRead = fileInputStream.read(buffer);
                    output.write(buffer, 0, bytesRead);
                } while ((bytesRead == 4096));
                output.flush();
                output.close();
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        }

}

