import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Tracker implements ITracker{

    private List<IGame> gameList;

    public Tracker() {
        this.gameList = new ArrayList<IGame>();
    }

    @Override
    public List<IGame> getGameList() throws RemoteException {
        return this.gameList;
    }

    @Override
    public synchronized List<IGame> join(IGame game) throws RemoteException {
        this.gameList.add(game);
        return this.gameList;
    }

    public static void main(String[] args) {
        ITracker stub = null;
        Registry registry = null;

        try{
            Tracker tracker = new Tracker();
            stub = (ITracker) UnicastRemoteObject.exportObject(tracker,0);
            registry = LocateRegistry.createRegistry(1099);
            registry.bind("Tracker",stub);
            System.out.println("Tracker ready");
        }catch (Exception e){
            System.out.println("Tracker exception");
            e.printStackTrace();
        }
    }
}
