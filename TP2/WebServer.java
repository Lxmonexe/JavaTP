import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class WebServer {

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
