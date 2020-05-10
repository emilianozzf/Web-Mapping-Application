import java.util.Comparator;

/**
 * Comparator for comparing Flight objects according to their number of passengers.
 */
public class NumOfPassengersComparator implements Comparator<Flight> {

  @Override
  public int compare(Flight o1, Flight o2) {
    return o1.passengers - o2.passengers;
  }
}
