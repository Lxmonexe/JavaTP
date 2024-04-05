import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebServer {
    private void test(Socket socket){
        BufferedWriter output;
        try {
            output = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            output.write("HTTP/1.1 200 OK\r\n");

            output.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
    public void run(int portNumber) {


        try {
            ServerSocket serverSocket = new ServerSocket(portNumber);

            while (true) {

                    Socket socket = serverSocket.accept();
                    RequestProcessor requestProcessor = new RequestProcessor(socket);
                    requestProcessor.process();
                    socket.close();
            }
        }catch(IOException e){
                throw new RuntimeException(e);

        }
    }
}
