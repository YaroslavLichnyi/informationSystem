package information.system.Server.Controller;

import javafx.beans.Observable;

import java.util.Observer;

public interface Notificator  extends Observable  {
    void registerObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}
