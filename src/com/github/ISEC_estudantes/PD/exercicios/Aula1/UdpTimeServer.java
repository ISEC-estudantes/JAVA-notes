package com.github.ISEC_estudantes.PD.exercicios.Aula1;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Calendar;
import java.util.GregorianCalendar;


public class UdpTimeServer {

    public static final int MAX_SIZE = 256;
    public static final String TIME_REQUEST = "TIME";

    public static void main(String[] args) {
        int listeningPort;
        DatagramSocket socket;
        DatagramPacket packet;
        String receivedMsg, timeMsg;
        Calendar calendar;
        if (args.length != 1) {
            System.out.println("Sintaxe: java UdpTimeSErver_2 listeningPort");
        }
        try {
            listeningPort = Integer.parseInt(args[0]);
            socket = new DatagramSocket(listeningPort);

            System.out.println("UDP TimeServer iniciado...");

            while (true) {
                packet = new DatagramPacket(new byte[MAX_SIZE], MAX_SIZE);
                socket.receive(packet);

                receivedMsg = new String(packet.getData(), 0, packet.getLength());

                System.out.println("Recebido\"" + receivedMsg + "\"de" + packet.getAddress().getHostAddress() + ":" + packet.getPort());

                if (!receivedMsg.equalsIgnoreCase(TIME_REQUEST)) {
                    continue;
                }

                calendar = GregorianCalendar.getInstance();
                timeMsg = calendar.get(GregorianCalendar.HOUR_OF_DAY) + ":" + calendar.get(GregorianCalendar.MINUTE) + ":" + calendar.get(GregorianCalendar.SECOND);
                packet.setData(timeMsg.getBytes());
                packet.setLength(timeMsg.length());
                socket.send(packet);
            }
        } catch (Exception e) {
            System.out.println("erro lol");
        }
    }
}
