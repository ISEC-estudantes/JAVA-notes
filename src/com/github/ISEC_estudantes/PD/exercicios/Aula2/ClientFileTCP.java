package com.github.ISEC_estudantes.PD.exercicios.Aula2;

import java.io.*;
import java.net.*;

public class ClientFileTCP {

    public static final int MAX_SIZE = 4000;
    public static final int TIMEOUT = 5; //segundos
    public static final String NOT_EXIST_ERROR = "NOT_EXIST_ERROR";
    public static final int CHUNK_SIZE = 16384;

    public static void main(String[] args)  {
        File localDirectory;
        String fileName, localFilePath = null;
        InetAddress serverAddr;

        int serverPort;
        FileOutputStream localFileOutputStream = null;
        //int contador = 0;

        if (args.length != 4) {
            System.out.println("Sintaxe: java ClientFileTCP serverAddress serverTcpPort fileToGet");
            return;
        }

        fileName = args[2].trim();
        //localDirectory = new File(args[3].trim());
        localDirectory = (new File("").getAbsoluteFile());
        System.out.println("localDirectory:" + localDirectory);
        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        if (!localDirectory.canWrite()) {
            System.out.println("Sem permissoes de escrita na directoria " + localDirectory);
            return;
        }

        try {

            try {

                localFilePath = localDirectory.getCanonicalPath() + File.separator + fileName;
                localFileOutputStream = new FileOutputStream(localFilePath);

                System.out.println("Ficheiro " + localFilePath + " criado.");

            } catch (IOException e) {

                if (localFilePath == null) {
                    System.out.println("Ocorreu a excepcao {" + e + "} ao obter o caminho canonico para o ficheiro local!");
                } else {
                    System.out.println("Ocorreu a excepcao {" + e + "} ao tentar criar o ficheiro " + localFilePath + "!");
                }

                return;
            }

            try {
                //inicializacoes
                serverAddr = InetAddress.getByName(args[0]);
                try {
                    serverPort = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    System.out.println("O porto do servidor deve ser um inteiro positivo:\n\t" + e);
                    return;
                }
                System.out.println("serverAddr, serverPort:" + serverAddr + "," + serverPort);
                var s = new Socket(serverAddr, serverPort);
                var iS = s.getInputStream();
                var oS = s.getOutputStream();

                // send file name
                oS.write(fileName.getBytes());

                //get file size

                byte[] bufStr = new byte[CHUNK_SIZE];
                byte[] contents = new byte[100];
                int nBytes = iS.read(bufStr);
                String response = new String(bufStr, 0, nBytes);
                if (response.equals(NOT_EXIST_ERROR)) {
                    oS.close();
                    throw new FileNotFoundException("The file asked does not exist.");
                }
                System.out.println("fileSize:" + response);
                var fileSize = Double.parseDouble(response);

                // inicializacao fileoutputstream para o fullpath do output do ficheiro
                var bos = new BufferedOutputStream(new FileOutputStream(localDirectory + File.separator + fileName));

                //Numero de bytes lidos em uma call do read()
                int bytesRead;
                double progresso = 0;

                while ((bytesRead = iS.read(contents)) != -1) {
                    bos.write(contents, 0, bytesRead);
                    System.out.println(((progresso += bytesRead) * 100) / fileSize + "% completed");
                }
                bos.flush();
                s.close();
                System.out.println("Transferencia concluida.");

            } catch (UnknownHostException e) {
                System.out.println("Destino desconhecido:\n\t" + e);
            } catch (NumberFormatException e) {
                System.out.println("Problemas a converter um número:\n\t" + e);
            } catch (SocketTimeoutException e) {
                System.out.println("Nao foi recebida qualquer bloco adicional, podendo a transferencia estar incompleta:\n\t" + e);
            } catch (SocketException e) {
                System.out.println("Ocorreu um erro ao nivel do socket UDP:\n\t" + e);
            } catch (IOException e) {
                System.out.println("Ocorreu um erro no acesso ao socket ou ao ficheiro local " + localFilePath + ":\n\t" + e);
            }
        } finally {

            if (localFileOutputStream != null) {
                try {
                    localFileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
