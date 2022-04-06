package cz.spsmb.http;

import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Application {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);

        Socket client = serverSocket.accept();
        Scanner scanner = new Scanner(client.getInputStream());
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
        scanner.nextLine();
        /*while (scanner.hasNextLine()) {
            System.out.println(scanner.nextLine());
        }*/

        HttpResponseBuilder httpResponseBuilder = new HttpResponseBuilder();
        httpResponseBuilder.setBody("Test body");
        httpResponseBuilder.setStatusCode(200);
        httpResponseBuilder.getHeaderParam()
                .put(HttpResponseBuilder.HEADER_CONTENT_TYPE, httpResponseBuilder.MIME_TYPE_HTML);


        OutputStream clientOutput = client.getOutputStream();

        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(clientOutput);
        String response = httpResponseBuilder.build();
        outputStreamWriter.write(response);
        outputStreamWriter.flush();

        System.out.println(response);

    }

}
