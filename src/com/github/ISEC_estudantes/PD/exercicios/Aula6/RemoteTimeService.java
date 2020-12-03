import jdk.jshell.execution.RemoteExecutionControl;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.ServerNotActiveException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
//TODO: completar a aula 6/rmi
public class RemoteTimeService extends java.rmi.server.RemoteServer implements RemoteTimeInterface {
    @Override
    public Hora getHora() throws RemoteException {
        Calendar now = Calendar.getInstance();
        try {
            this.getClientHost();
        } catch (ServerNotActiveException e) {
            e.printStackTrace();
        }
        return new Hora(now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE), now.get(Calendar.SECOND));
    }
    public RemoteTimeService()throws RemoteException {}

    public static void main(String[] args) {
        try {
            RemoteTimeService timeService = new RemoteTimeService();
            Naming.bind("rmi://localhost/timeserver", timeService);
        } catch (AlreadyBoundException | RemoteException | MalformedURLException e) {
            // Area
            e.printStackTrace();
        }


    }
}
