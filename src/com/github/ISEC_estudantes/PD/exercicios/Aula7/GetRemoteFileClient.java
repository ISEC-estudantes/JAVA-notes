/*
 * Exemplo de utilizacao do servico com interface remota GetRemoteFileInterface.
 * Assume-se que o servico encontra-se registado sob o nome "servidor-ficheiros-pd".
 */
import java.io.*;
import java.rmi.*;

/**
 *
 * @author Jose'
 */
public class GetRemoteFileClient {

    public static void main(String[] args) {
            
        String objectUrl;        
        File localDirectory;
        String fileName;                
        
        String localFilePath;
        FileOutputStream localFileOutputStream;
                
        byte [] b;    
        long offset;
        
        /*
         * Trata os argumentos da linha de comando 
         */        
        
        if(args.length != 3){
            System.out.print("Deve passar na linha de comando: (1) a localizacao do registry onde se encontra registado ");
            System.ou.println("o servico com nome \"GetRemoteFile\"; (2) a directoria local ");
            System.out.println("onde pretende guardar o ficheiro obtido; e (3) o ficheiro pretendido!");
            return;
        }        

        objectUrl = //...
                
        localDirectory = //...
        fileName = //...
                
        if(/*...*/){
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }
        
        if(/*...*/){
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }
        if(/*...*/){
            System.out.println("Sem permissoes de escrita na directoria " + localDirectory);
            return;
        }                
        
        try{
            
            /*
             * Cria o ficheiro local
             */            
            localFilePath = new File(localDirectory.getPath()+File.separator+fileName).getCanonicalPath();
            localFileOutputStream = /*...*/
            
            System.out.println("Ficheiro " + localFilePath + " criado.");
            
            /*
             * Obtem a referencia remota para o servico com nome "servidor-ficheiros-pd"
             */
            GetRemoteFileInterface fileService = //...
            
            /*
             * Obtem e guarda localmente os varios blocos do ficheiro pretendido.
             * Para o efeito, invoca o metodo getFileChunk no servico remoto para obter cada bloco.
             */
            offset = 0;
            
            while(/*...*/){
                localFileOutputStream.write(b);
                offset += b.length;
            }
            
            localFileOutputStream.close();
            
            System.out.println("Transferencia do ficheiro " + fileName + " concluida.");
            
        }catch(RemoteException e){
            System.out.println("Erro remoto - " + e);
        }catch(NotBoundException e){
            System.out.println("Servico remoto desconhecido - " + e);
        }catch(IOException e){
            System.out.println("Erro E/S - " + e);
        }catch(Exception e){
            System.out.println("Erro - " + e);
        }               
    }
}
