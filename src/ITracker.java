import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Vector;

public interface ITracker extends Remote {

    public List<IGame> getGameList() throws RemoteException;

    public List<IGame> join(IGame game) throws RemoteException;
}
