import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;
import java.util.Scanner;

public class Game implements IGame, Serializable {


    private static ITracker tracker;
    private static List<IGame> GameList;
    public String playerID;

    public Game(String playerID) {
        this.playerID = playerID;
    }

    private synchronized void run(){

        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            input = scanner.nextLine();
            System.out.println(input);
        }
    }

    public static void main(String[] args) throws RemoteException {
        String playerID = args[0];
        Game game = new Game(playerID);
        try{
            Registry registry = LocateRegistry.getRegistry(null);
            tracker = (ITracker) registry.lookup("Tracker");
            tracker.join(game);
            GameList = tracker.getGameList();
            Game primary = (Game) GameList.get(0);
            primary.run();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
