package produccion;

//import jdk.internal.org.objectweb.asm.tree.InsnList;

import java.util.ArrayList;
import java.util.Map;

public interface Flight {
    String getId();
    boolean addPassenger(Passenger passenger);
    boolean removePassenger(Passenger passenger);

    Map<String, Passenger> getPassengersList();
}