package com.github.ISEC_estudantes.PD.exercicios.Aula5;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class DistributedPiWorkerComSerializacao implements Runnable {

    protected Socket s;
    protected ObjectInputStream in;
    protected ObjectOutputStream out;

    public DistributedPiWorkerComSerializacao(Socket s) {
        this.s = s;
//        in = null;
    }

    public double getMyResult(int myId, int nWorkers, long nIntervals) {
        double i,xi;
        double dX = 1.0/(double)nIntervals;
        double myResult = 0.0;
        if(nIntervals < 1|| nWorkers < 1 || myId < 1 || myId > nWorkers){
            return 0.0;
        }

        for (i = myId-1; i<nIntervals; i+=nWorkers){
            xi = dX*(i+0.5);
            myResult += (4.0/(1.0+xi*xi));
            ++nIntervals;
        }

        return myResult;

    }

    @Override
    public void run() {
        int myId;
        int nWorkers;
        long nIntervals;
        double myResult;
        RequestToWorker req;

        try {

            in = new ObjectInputStream(s.getInputStream()); //Cria um ObjectInputStream associado ao socket s
            out = new ObjectOutputStream(s.getOutputStream()); //Cria um ObjectOutputStream associado ao socket s

            try {

                req = (RequestToWorker) in.readObject();//Aguarda pela recepcao de um pedido enviado pelo master

                myId = req.getId();
                nWorkers = req.getnWorkers();
                nIntervals = req.getnIntervals();

            } catch (ClassNotFoundException e) {
                System.err.println("<" + Thread.currentThread().getName() + ">: " + e);
                return;
            }

            myResult = getMyResult(myId, nWorkers, nIntervals);

            //Envia myResult ao Master sob a forma de um Double serializado
            out.writeUnshared(myResult);//o unshared é usado para nao se guardar um buffer do objecto ao contrario do writeObject que guarda um buffer do objecto o que pode nao ser actualizado
            out.flush();//para esvaziar o buffer
            myResult *=2;
            out.writeObject(myResult);
            out.flush();
            System.out.format("<%s> %.10f\n", Thread.currentThread().getName(), myResult);

        } catch (IOException e) {
            System.out.println("<" + Thread.currentThread().getName() +
                    "> Erro ao aceder ao socket:\n\t" + e);
        } finally {
            try {
                if (s != null) s.close();
            } catch (IOException e) {
            }
        }

    }

    public static void main(String[] args) {
        ServerSocket s = null;
        Socket toMaster;
        int listeningPort;
        Thread t;
        int nCreatedThreads = 0;

        if (args.length != 1) {
            System.out.println("Sintaxe: java DistributedPiWorker <listening port>");
            return;
        }

        listeningPort = Integer.parseInt(args[0]);

        try {

            s = new ServerSocket(listeningPort);

            while (true) {
                toMaster = s.accept(); //Aceita um pedido de ligacao TCP de um master

                //Inicia uma thread destinada a tratar da comunicacao com o master
                nCreatedThreads++;
                t = new Thread(new DistributedPiWorkerComSerializacao(toMaster), "Thread_" + nCreatedThreads);
                /***/
            }

        } catch (IOException e) {
            System.out.println("<DistributedPiWorker> Erro ao aceder ao socket:\n\t" + e);
        } finally {
            if (s != null) {
                try {
                    s.close();
                } catch (IOException e) {

                }
            }
        }

    }
}
