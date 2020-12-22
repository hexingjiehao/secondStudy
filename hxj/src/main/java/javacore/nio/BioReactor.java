package javacore.nio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by xiongjie on 2018/11/17.
 */
public class BioReactor implements Runnable{
    @Override
    public void run() {
        System.out.println("Hello Server!!");

        try {
            ServerSocket server = null;
            try {
                server = new ServerSocket(4700);
            } catch (Exception e) {
                System.out.println("can not listen to:" + e);
            }

            Socket socket = null;
            try {
                socket = server.accept();
            } catch (Exception e) {
                System.out.println("Error." + e);
            }

            String line;
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("Client:" + is.readLine());

            PrintWriter os = new PrintWriter(socket.getOutputStream());
            line = "hello";
            os.println(line);
            os.flush();
            is.close(); // 关闭Socket输入流
            socket.close(); // 关闭Socket
            server.close(); // 关闭ServerSocket
        } catch (Exception e) {
            System.out.println("Error." + e);
        }

    }
}
