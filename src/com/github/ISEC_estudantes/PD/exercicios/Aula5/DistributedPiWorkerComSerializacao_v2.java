package com.github.ISEC_estudantes.PD.exercicios.Aula5;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DistributedPiWorkerComSerializacao_v2 implements Runnable
{
    static final int TIMEOUT = 60000; //60 seconds
    static final int DB_UPDATE_DELAY = 30000; // 30 seconds

    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    //static final String DB_URL = "jdbc:mysql://localhost/pi_distrib_db";
    //static final String USER = "piworker";
    //static final String PASS = "w-pass-123";

    private final Socket s;
    private ObjectInputStream in;
    private ObjectOutputStream out;
    
    public DistributedPiWorkerComSerializacao_v2(Socket s)
    {
        this.s= s;
        in = null;
        out = null;
    }
    
    public double getMyResult(int myId, int nWorkers, long nIntervals)
    {
        long i;
        double dX, xi, myResult;
        
        if(nIntervals < 1 || nWorkers < 1 || myId <1 || myId > nWorkers){
            return 0.0;
        }
        
        dX = 1.0/nIntervals;
        myResult = 0;
        
        for (i = myId-1 ; i < nIntervals; i += nWorkers) {             
            xi = dX*(i + 0.5);
            myResult += (4.0/(1.0 + xi*xi));               
        }
        
        myResult *= dX;
        
        return myResult;
    }
    
    @Override
    public void run()
    {
        int myId;
        int nWorkers;
        long nIntervals;
        double myResult;
        RequestToWorker req;
        
        try{
            
            in = new ObjectInputStream(s.getInputStream());
            out = new ObjectOutputStream(s.getOutputStream());
                        
            try{
                
                req = (RequestToWorker)in.readObject();
                
                myId = req.getId();   
                nWorkers = req.getnWorkers();
                nIntervals = req.getnIntervals();
                
                System.out.println("<" + Thread.currentThread().getName() + 
                    "> New request received - myId: " + myId + " nWorkers: " + nWorkers + " nIntervals: " + nIntervals);
             }catch(ClassNotFoundException e){
                System.err.println("<" + Thread.currentThread().getName() + 
                    ">: " + e);             
                return;
            }  
            
            myResult = getMyResult(myId, nWorkers, nIntervals);
            
            out.writeObject(myResult);
            out.flush();
            
            System.out.format("<%s> %.10f\n", Thread.currentThread().getName(), myResult);
            
        }catch(IOException e){
            System.out.println("<" + Thread.currentThread().getName() + 
                    "> Erro ao aceder ao socket:\n\t" + e);
        }finally{
            try{
                if(s != null) s.close();
            }catch(IOException e){}            
        }
        
    }
    
    static class ManageMyEntry extends Thread {

        int listeningPort;
        String dbAddress;
        String dbName;
        String user;
        String pass;

        public ManageMyEntry(int listeningPort, String dbAddress, String dbName, String user, String pass) {
            this.listeningPort = listeningPort;
            this.dbAddress = dbAddress;
            this.dbName = dbName;
            this.user = user;
            this.pass = pass;
        }

        @Override
        public void run() {

            Connection conn = null;
            Statement stmt = null;            
            String createEntryQuery;
            String updateEntryQuery;

            try {
                Class.forName(JDBC_DRIVER);
                
                String dbUrl = "jdbc:mysql://" + dbAddress + "/" + dbName;
                conn = DriverManager.getConnection(dbUrl, user, pass);
                stmt = conn.createStatement();

                createEntryQuery = "INSERT INTO pi_workers VALUES (NULL,'"
                        + InetAddress.getLocalHost().getHostAddress() + "'," + listeningPort + ", NULL);";
                updateEntryQuery = "UPDATE pi_workers SET timestamp=NULL WHERE address LIKE '%"
                        + InetAddress.getLocalHost().getHostAddress() + "%' AND port=" + listeningPort + ";";

                if(stmt.executeUpdate(createEntryQuery)<1){
                    System.out.println("Entry insertion failed");
                }

                while (true) {
                    try {
                        Thread.sleep(DB_UPDATE_DELAY);
                        if(stmt.executeUpdate(updateEntryQuery)<1){
                            System.out.println("Entry update failed");                            
                        }
                    } catch (InterruptedException e) {}
                }
            } catch (ClassNotFoundException | UnknownHostException | SQLException e) {
                System.out.println("<DistributedPiWorker> Exception reported:\r\n\t..." + e);
            } finally {

                try {
                    if (stmt != null) {
                        stmt.close();
                    }
                } catch (SQLException e) {}

                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException e) {}

            }
        }
    }
       
    public static void main(String[] args) {
        ServerSocket s = null;
        Socket toClient;
        int listeningPort;
        
        Thread t, manageMyDbEntry;
        
        int nCreatedThreads = 0;
        
        if(args.length != 5){
            System.out.println("Sintaxe: java DistributedPiWorker <listening port>"
                    + "<SGBD address> <BD name> <usename> <password>");
            return;
        }                
        
        try{
            listeningPort = Integer.parseInt(args[0]);
            s = new ServerSocket(listeningPort);
                                    
            manageMyDbEntry = new ManageMyEntry(listeningPort, args[1], args[2], args[3], args[4]);
            manageMyDbEntry.setDaemon(true);
            manageMyDbEntry.start();
                        
            while(true){
                toClient = s.accept();
                
                toClient.setSoTimeout(TIMEOUT);
                
                nCreatedThreads++;
                t = new Thread(new DistributedPiWorkerComSerializacao(toClient), "Thread "+nCreatedThreads);
                t.start();                
            }            
            
        }catch(IOException | NumberFormatException e){
            System.out.println("<DistributedPiWorker> Exception reported:\r\n\t..." + e);
        }finally{
            if(s != null){
                try{
                    s.close();
                }catch(IOException e){}
            }
        }
                
    }
}
