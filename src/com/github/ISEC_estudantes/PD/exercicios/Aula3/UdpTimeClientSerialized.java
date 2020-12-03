package com.github.ISEC_estudantes.PD.exercicios.Aula3;

import java.io.*;
import java.net.*;
import java.util.Calendar;

public class UdpTimeClientSerialized {

    public static final int MAX_SIZE = 5;
    public static final String TIME_REQUEST = "TIME";
    public static final int TIMEOUT = 10; //segundos

    public static void main(String[] args) {
        InetAddress serverAddr;
        int serverPort =    -1;
        DatagramSocket socket = null;
        DatagramPacket packet;


        if (args.length != 2) {
            System.out.println("Sintaxe: java UdpTimeClient serverAddress serverUdpPort");
            return;
        }

        try {
            serverAddr = InetAddress.getByName(args[0]);
            serverPort = Integer.parseInt(args[1]);

            socket = new DatagramSocket();
            socket.setSoTimeout(TIMEOUT * 1000);

            ByteArrayOutputStream bout = new ByteArrayOutputStream();
            ObjectOutputStream oout = null;
            try {
                oout = new ObjectOutputStream(bout);
            } catch (IOException e) {
                System.out.println("line X");
                e.printStackTrace();
            }

            try {
                oout.writeObject(TIME_REQUEST);
            } catch (IOException e) {
                System.out.println("line42");
                e.printStackTrace();
            }
            try {
                oout.flush();
            } catch (IOException e) {
                System.out.println("line48");
                e.printStackTrace();
            }

            packet = new DatagramPacket(bout.toByteArray(), bout.size(), serverAddr,
                    serverPort);

            try {
                socket.send(packet);
            } catch (IOException e) {
                System.out.println("line58");
                e.printStackTrace();
            }

            packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                System.out.println("line66");
                e.printStackTrace();
            }

            var bin = new ByteArrayInputStream(packet.getData(), 0, packet.getLength());
            ObjectInputStream oin = null;
            try {
                oin = new ObjectInputStream(bin);
            } catch (IOException e) {
                System.out.println("line75");
                e.printStackTrace();
            }


            //packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
            //socket.receive(packet);

            Calendar response = null;
            try {
                response = (Calendar)oin.readObject();
            } catch (IOException e) {
                System.out.println("line87");
                e.printStackTrace();
            }
            System.out.println("Hora indicada pelo servidor: " + response);

        } catch (UnknownHostException e) {
            System.out.println("Destino desconhecido:\n\t" + e);
        } catch (NumberFormatException e) {
            System.out.println("O porto do servidor deve ser um inteiro positivo.");
        } catch (SocketException e) {
            System.out.println("Ocorreu um erro ao nivel do socket UDP:\n\t" + e);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

}

