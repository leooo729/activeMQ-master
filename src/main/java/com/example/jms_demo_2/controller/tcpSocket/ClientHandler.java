package com.example.jms_demo_2.controller.tcpSocket;

import com.example.jms_demo_2.config.SpringUtil;
import com.example.jms_demo_2.service.TransferService;
import org.json.JSONException;

import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread  {

    private TransferService transferService= SpringUtil.getBean(TransferService.class);

    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() { //啟動Thread時會執行run
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            String requestStr = br.readLine();

            bw.write(transferService.getResponse(requestStr));

            bw.newLine();
            bw.flush();
            //-------------------------------------------------------
            socket.close();

        } catch (IOException e) {
            System.out.println("Socket啟動有問題 !");
            System.out.println("IOException :" + e.toString());
        } catch (JSONException | NullPointerException e) {
            try {
                OutputStream outputStream = socket.getOutputStream();
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(outputStream));

                System.out.println(e);
                bw.write("輸入Json格式資料有誤"+e);
                bw.newLine();
                bw.flush();

                bw.close();
                outputStream.close();

            } catch (IOException ex) {
                System.out.println("Socket啟動有問題 !");
                System.out.println("IOException :" + e.toString());
            }
        }catch (Exception e) {
            System.out.println("Exception :" +e.toString());
        }
    }
}
