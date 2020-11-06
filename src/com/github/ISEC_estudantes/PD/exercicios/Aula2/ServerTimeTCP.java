package com.github.ISEC_estudantes.PD.exercicios.Aula2;

import java.io.IOException;
import java.net.ServerSocket;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerTimeTCP {
    private final String GET_TIME = "TIME";
    private int serverPort;

    public ServerTimeTCP(int serverPort) {
        this.serverPort = serverPort;
        
    }

    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(serverPort);
            boolean stop = false;
            while (!stop) {
                var s = serverSocket.accept();
                // ao contrario do udp em que se recebe packotes individuais de dados, em TCP  tens um constante continuo de dados entao para se enviar utliza estes dois metodos:
                //getOutputStream()
                //e
                //getInputStream()
                var iS = s.getInputStream();
                var oS = s.getOutputStream();

                //cria se um array de bytes para receber a informação é o que eu preciso a
                var bufStr = new byte[256];

                //devolve o numero de bytes preenchidos
                int nBytes = iS.read(bufStr);
                //guarda a mensagem na tempStr
                String tempStr = new String(bufStr, 0, nBytes);

                //tratamento de logica
                if (tempStr.equals(GET_TIME)) {
                    System.out.println("Time request received from "+ s.getInetAddress().getHostAddress()+":"+s.getPort());
                    //quando se da um objecto do tipo date ele vai criar uma string com este formato
                    var sDF = new SimpleDateFormat("HH:mm:ss");
                    // aqui esta a dar se a data actual e e' formatado pelo sDF
                    tempStr = sDF.format(new Date());

                    //teste com timeout
                    //Thread.sleep(1000);

                    bufStr = tempStr.getBytes();
                    oS.write(bufStr);
                } else {
                    stop = true;
                }
                //fechar a socket tambem feicha os streams de input e output
                s.close();
            }
            //fecha-se tambem o server socket
            serverSocket.close();

            //tratamento de erros
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        ServerTimeTCP sSTP = new ServerTimeTCP(6969);
        sSTP.run();
    }


}
