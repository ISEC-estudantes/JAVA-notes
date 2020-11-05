package com.github.ISEC_estudantes.PD.exercicios.Aula3;

import java.io.IOException;
import java.net.Socket;

public class ClientTimeTCP {
    private String serverIp;
    private int serverPort;
    private final String GET_TIME = "TIME";

    ClientTimeTCP(String serverIp, int serverPort){
        this.serverIp = serverIp;
        this.serverPort = serverPort;
    }
    public void run(){
        try {
            //criacao da socket
            var s = new Socket(serverIp, serverPort);

            //teste com timeout
            //s.setSoTimeout(2000);

            //input e output streams
            var iS = s.getInputStream();
            var oS = s.getOutputStream();


            //escreve o output na socket
            oS.write(GET_TIME.getBytes());

            // cria um buffer
            byte[] bufStr = new byte[256];
            //lê o input stream
            int nBytes = iS.read(bufStr);
            //preenche a string com o input
            String tempStr = new String(bufStr, 0 , nBytes);
            //printa o que recebeu
            System.out.println("Server Time: "+ tempStr);
            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        var cTCP = new ClientTimeTCP("localhost", 6969);
        cTCP.run();
    }
}
