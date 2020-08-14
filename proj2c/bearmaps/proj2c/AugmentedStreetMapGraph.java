package bearmaps.proj2c;

import bearmaps.hw4.streetmap.Node;
import bearmaps.hw4.streetmap.StreetMapGraph;
import bearmaps.proj2ab.KDTree;
import bearmaps.proj2ab.Point;

import java.util.*;

/**
 * An augmented graph that is more powerful that a standard StreetMapGraph. Specifically, it
 * supports the following additional operations:
 *
 * @author Alan Yao, Josh Hug, ________
 */
public class AugmentedStreetMapGraph extends StreetMapGraph {

  private MyTrieSet trie;
  private Map<String, List<Node>> nameToNode;

  public AugmentedStreetMapGraph(String dbPath) {
    super(dbPath);
    trie = new MyTrieSet();
    nameToNode = new HashMap<>();
    List<Node> nodes = this.getNodes();
    for (Node node : nodes) {
      if (node.name() == null) {
        continue;
      }
      String cleanedName = cleanString(node.name());
      trie.add(cleanedName);
      if (!nameToNode.containsKey(cleanedName)) {
        List<Node> newList = new ArrayList<>();
        newList.add(node);
        nameToNode.put(cleanedName, newList);
      } else {
        nameToNode.get(cleanedName).add(node);
      }

    }
  }


  /**
   * For Project Part II. Returns the vertex closest to the given longitude and latitude.
   *
   * @param lon The target longitude.
   * @param lat The target latitude.
   * @return The id of the node in the graph closest to the target.
   */
  public long closest(double lon, double lat) {
    List<Node> nodes = getNodes();
    List<Point> points = new ArrayList<>();
    Map<Point, Node> mapping = new HashMap<>();
    for (Node node : nodes) {
      if (neighbors(node.id()).isEmpty()) {
        continue;
      }
      Point newPoint = new Point(lon(node.id()), lat(node.id()));
      points.add(newPoint);
      mapping.put(newPoint, node);
    }

    KDTree kdTree = new KDTree(points);
    Point nearestPoint = kdTree.nearest(lon, lat);
    Node nearestNode = mapping.get(nearestPoint);
    return nearestNode.id();
  }


  /**
   * For Project Part III (gold points) In linear time, collect all the names of OSM locations that
   * prefix-match the query string.
   *
   * @param prefix Prefix string to be searched for. Could be any case, with our without
   *               punctuation.
   * @return A <code>List</code> of the full names of locations whose cleaned name matches the
   * cleaned <code>prefix</code>.
   */
  public List<String> getLocationsByPrefix(String prefix) {
    List<String> matchedNames = trie.keysWithPrefix(prefix);
    for (int i = 0; i < matchedNames.size(); i++) {
      for (Node n: nameToNode.get(matchedNames.get(i))) {
        matchedNames.set(i, n.name());
      }
    }
    return matchedNames;
  }

  /**
   * For Project Part III (gold points) Collect all locations that match a cleaned
   * <code>locationName</code>, and return information about each node that matches.
   *
   * @param locationName A full name of a location searched for.
   * @return A list of locations whose cleaned name matches the cleaned <code>locationName</code>,
   * and each location is a map of parameters for the Json response as specified: <br> "lat" ->
   * Number, The latitude of the node. <br> "lon" -> Number, The longitude of the node. <br> "name"
   * -> String, The actual name of the node. <br> "id" -> Number, The id of the node. <br>
   */
  public List<Map<String, Object>> getLocations(String locationName) {
    List<Map<String, Object>> locations = new ArrayList<>();
    String cleanName = cleanString(locationName);
    if (nameToNode.containsKey(cleanName)) {
      for (Node n: nameToNode.get(cleanName)) {
        Map<String, Object> locationInfo = new HashMap<>();
        locationInfo.put("lon", n.lon());
        locationInfo.put("lat", n.lat());
        locationInfo.put("name", n.name());
        locationInfo.put("id", n.id());
        locations.add(locationInfo);
      }
    }
    return locations;
  }


  /**
   * Useful for Part III. Do not modify. Helper to process strings into their "cleaned" form,
   * ignoring punctuation and capitalization.
   *
   * @param s Input string.
   * @return Cleaned string.
   */
  private static String cleanString(String s) {
    return s.replaceAll("[^a-zA-Z ]", "").toLowerCase();
  }

}
