package com.example.jms_demo_2;

import com.example.jms_demo_2.controller.tcpSocket.ClientHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

@SpringBootApplication
@EnableJpaAuditing//利用jpa可以给MySQL列属性自动赋值,例如一些创建时间，修改时间
@ComponentScan(basePackages = "com.example.jms_demo_2.*")//?
public class JmsDemo2Application {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(JmsDemo2Application.class, args);
        ServerSocket serverSocket = new ServerSocket(1010); //服務器端點對象 建立好服務端socket
        Executor executor = Executors.newFixedThreadPool(2);
        while (true) {
            try {
                Socket socket = serverSocket.accept(); //監聽客服端 等待客戶端請求  //等服務端收到請求 創建一與之匹配Socket 兩Socket就可互相通信
                //建立一新Thread
                ClientHandler clientHandler = new ClientHandler(socket);
                executor.execute(clientHandler);

            } catch (IOException e) {
                System.out.println("Socket啟動有問題 !");
                System.out.println("IOException :" + e.toString());
            }

        }

    }
}

