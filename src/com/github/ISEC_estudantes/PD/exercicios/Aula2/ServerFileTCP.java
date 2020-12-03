package com.github.ISEC_estudantes.PD.exercicios.Aula2;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;


/**
 *
 */
public class ServerFileTCP {
    public static final int MAX_SIZE = 4000;
    public static final String NOT_EXIST_ERROR = "NOT_EXIST_ERROR";
    public static final int CHUNK_SIZE = 16384;
    static public void main(String[] args) {
        File localDirectory;
        String requestedFileName;
        var bufStr = new byte[MAX_SIZE];
        int listeningPort;
        int nBytes;
        if (args.length != 2) {
            System.out.println("Sintaxe: java ServerFileTCP listeningPort localRootDirectory");
            return;
        }
        localDirectory = new File(args[1].trim());
        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + "não existe!");
            return;
        }
        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }
        if (!localDirectory.canRead()) {
            System.out.println("Sem permissão de leitura na directoria " + localDirectory + "!");
            return;
        }

        try {
            listeningPort = Integer.parseInt(args[0]);
            if (listeningPort <= 0)
                throw new NumberFormatException("Porto TCP de escuta indicado <= 0 (" + listeningPort + ")");
            var serverSocket = new ServerSocket(listeningPort);
            String requestedCanonicalFilePath;
            while (true) {
                var s = serverSocket.accept();
                var iS = s.getInputStream();
                var oS = s.getOutputStream();
                nBytes = iS.read(bufStr);

                //file verification
                requestedFileName = new String(bufStr, 0, nBytes);
                System.out.println("Recebido pedido para \"" + requestedFileName + "\" de " + s.getInetAddress().getHostAddress() + ":" + s.getPort());
                requestedCanonicalFilePath = new File(localDirectory + File.separator + requestedFileName).getCanonicalPath();

                if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath())) {
                    System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                    System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                    oS.write(NOT_EXIST_ERROR.getBytes());
                    s.close();
                    continue;
                }
                //gives the size of the file
                var fileLength = (new File(requestedCanonicalFilePath)).length();
                // envia o tamanho para o client
                oS.write(String.valueOf(fileLength).getBytes());

                //oS.write(requestedCanonicalFilePath.getBytes().length);


                //creating the buffer
                FileInputStream fis = new FileInputStream(requestedCanonicalFilePath);
                BufferedInputStream bis = new BufferedInputStream(fis);

                //read file contents into contents array
                byte[] contents;
                long current = 0;

                while (current != fileLength) {
                    int size = CHUNK_SIZE;
                    if (fileLength - current >= size) {
                        current += size;
                    } else {
                        size = (int) (fileLength - current);
                        current = fileLength;
                    }
                    contents = new byte[size];
                    bis.read(contents, 0, size);
                    oS.write(contents);
                    System.out.println("A enviar ficheiro ... " + (current * 100) / fileLength + "% completo!");
                }
                oS.flush();
                //File transfer done

                System.out.println("transferencia concluida");
                s.close();
            }
        } catch (IOException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }
    }
}


