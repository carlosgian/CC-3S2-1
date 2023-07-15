package produccion;

//import jdk.internal.org.objectweb.asm.tree.InsnList;

import java.util.ArrayList;

public interface Flight {
    String getId();
    boolean addPassenger(Passenger passenger);
    boolean removePassenger(Passenger passenger);

    ArrayList<Passenger> getPassengersList();
}