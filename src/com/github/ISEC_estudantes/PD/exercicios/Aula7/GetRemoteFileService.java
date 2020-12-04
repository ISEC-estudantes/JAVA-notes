package com.github.ISEC_estudantes.PD.exercicios.Aula7;

import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * @author Jose'
 */
public class GetRemoteFileService extends UnicastRemoteObject implements GetRemoteFileInterface {
    public static final String SERVICE_NAME = "servidor-ficheiros-pd";
    public static final int MAX_CHUNK_SIZE = 10000; //bytes

    protected File localDirectory;

    public GetRemoteFileService(File localDirectory) throws RemoteException {
        this.localDirectory = localDirectory;
    }

    public byte[] getFileChunk(String fileName, long offset) throws RemoteException {
        String requestedCanonicalFilePath = null;
        FileInputStream requestedFileInputStream = null;
        byte[] fileChunk = new byte[MAX_CHUNK_SIZE];
        int nbytes;

        fileName = fileName.trim();

        try {

            /*
             * Verifica se o ficheiro solicitado existe e encontra-se por baixo da localDirectory
             */
            requestedCanonicalFilePath = new File(localDirectory + File.separator + fileName).getCanonicalPath();

            if (!requestedCanonicalFilePath.startsWith(localDirectory.getCanonicalPath() + File.separator)) {
                System.out.println("Nao e' permitido aceder ao ficheiro " + requestedCanonicalFilePath + "!");
                System.out.println("A directoria de base nao corresponde a " + localDirectory.getCanonicalPath() + "!");
                return null;
            }

            /*
             * Abre o ficheiro solicitado para leitura.
             */
            requestedFileInputStream = new FileInputStream(requestedCanonicalFilePath);

            /*
             * Obtem um bloco de bytes do ficheiro colocando-os no array fileChunk e omitindo os primeiros offset bytes.
             */
            requestedFileInputStream.skip(offset);
            nbytes = requestedFileInputStream.read(fileChunk);

            if (nbytes == -1) {//EOF
                return null;
            }

            /*
             * Se fileChunk nao esta' totalmente preenchido (MAX_CHUNCK_SIZE), recorre-se
             * a um array auxiliar com tamanho correspondente ao numero de bytes efectivamente lidos.
             */

            if (nbytes < fileChunk.length) {

                /*
                 * Aloca aux
                 */

                byte[] aux = new byte[nbytes];

                /*
                 * Copia os bytes obitos do ficheiro de fileChunk para aux
                 */
                System.arraycopy(fileChunk, 0, aux, 0, nbytes);

                return aux;
            }

            return fileChunk;

        } catch (FileNotFoundException e) {   //Subclasse de IOException
            System.out.println("Ocorreu a excepcao {" + e + "} ao tentar abrir o ficheiro!");
        } catch (IOException e) {
            System.out.println("Ocorreu a excepcao de E/S: \n\t" + e);
        } finally {
            if (requestedFileInputStream != null) {
                try {
                    requestedFileInputStream.close();
                } catch (IOException ignored) {
                }
            }
        }

        return null;
    }

    /*
     * Lanca e regista um servico com interface remota do tipo GetRemoteFileInterface
     * sob o nome dado pelo atributo estatico SERVICE_NAME.
     */
    static public void main(String[] args) {
        File localDirectory;

        /*
         * Trata os argumentos da linha de comando
         */
        if (args.length != 1) {
            System.out.println("Sintaxe: java GetFileUdpServer localRootDirectory");
            return;
        }

        localDirectory = new File(args[0].trim());

        if (!localDirectory.exists()) {
            System.out.println("A directoria " + localDirectory + " nao existe!");
            return;
        }

        if (!localDirectory.isDirectory()) {
            System.out.println("O caminho " + localDirectory + " nao se refere a uma directoria!");
            return;
        }

        if (!localDirectory.canRead()) {
            System.out.println("Sem permissoes de leitura na directoria " + localDirectory + "!");
            return;
        }

        /*
         * Lanca o rmiregistry localmente no porto TCP por omissao (1099).
         */
        try {
            try {
                LocateRegistry.createRegistry(Registry.REGISTRY_PORT);
            } catch (RemoteException e) {
                System.out.println("Registry provavelmente ja' em execucao na maquina local!");
            }

            /*
             * Cria o servico
             */
            GetRemoteFileService timeService = new GetRemoteFileService(localDirectory);


                    System.out.println("Servico GetRemoteFile criado e em execucao (" + timeService.getRef().remoteToString() + "...");

            /*
             * Regista o servico no rmiregistry local para que os clientes possam localiza'-lo, ou seja,
             * obter a sua referencia remota (endereco IP, porto de escuta, etc.).
             */

            Naming.rebind(SERVICE_NAME, timeService);

            System.out.println("Servico " + SERVICE_NAME + " registado no registry...");

            /*
             * Para terminar um servico RMI do tipo UnicastRemoteObject:
             *
             *  UnicastRemoteObject.unexportObject(timeService, true);
             */

        } catch (RemoteException e) {
            System.out.println("Erro remoto - " + e);
            System.exit(1);
        } catch (Exception e) {
            System.out.println("Erro - " + e);
            System.exit(1);
        }
    }
}
