
import java.io.IOException;

/**
 *
 * @author Jose'
 */
public interface GetRemoteFileInterface extends java.rmi.Remote
{
    public byte [] getFileChunk(String fileName, long offset) throws java.rmi.RemoteException, IOException;
    public long getFileSize(String fileName) throws java.rmi.RemoteException, IOException;
    
}
