import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Flows {
    private HashSet<Integer> nodes;				                // set of vertices of the graph
    private HashMap<Integer, HashMap<Integer,Float>> adjacency; // sparse weighted adjacency matrix of the graph
    private HashMap<Integer, String> vertexNames;               // required for outputting solutions
    public HashMap<Integer, String> vertexRemarks;              // remarks of certain vertexes, if any (last column of the table)
    public final Integer S = 0;                                 // main source of the flow network
    public Integer Z;                                           // sink of the flow network

    public Flows (String filename) {
        nodes = new HashSet<Integer>();
        adjacency = new HashMap<Integer, HashMap<Integer,Float>>();
        vertexNames = new HashMap<Integer, String>();
        vertexRemarks = new HashMap<Integer, String>();
        HashMap<String, Integer> seenIds = new HashMap<String, Integer>();	   // only required during construction

        /**reading the file*/
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            List<String[]> rowList = new ArrayList<String[]>();
            while ((line = reader.readLine()) != null) {
                String[] lineItems = line.split(",");
                rowList.add(lineItems);
            }
            reader.close();

            /**setting up the initial Flow-Network with given data*/
            for (int i = 1; i < rowList.get(0).length - 1; i++) {     //set the vertexes in flow network
                if (!seenIds.containsKey(rowList.get(0)[i])) {        //make sure each node name appears only once
                    seenIds.put(rowList.get(0)[i], i);
                    addVertex(i, rowList.get(0)[i]);
                } else {
                    System.err.println("Vertex name '" + rowList.get(0)[i] + "' repeated.");
                }
            }
            if (seenIds.get("Z") != null) {                                    //save the integer value in "nodes" of sink "Z"
                Z = seenIds.get("Z");
            } else {
                System.err.println("Z not found.");                            //Program cannot be run without sink
                System.exit(0);
            }
            for (int i = 1; i < rowList.size(); i++) {
                if (!rowList.get(i)[0].equals("Z")) {                          //no path starting from sink Z to another node is allowed
                    for (int j = 1; j < rowList.get(i).length; j++) {
                        if (rowList.get(i)[0].equals(getVertexName(i))) {      //check if vertex name in the first column correspond to that in the first row
                            if (j == rowList.get(i).length - 1) {              //save the remarks on the last column, if any
                                if (!rowList.get(i)[j].equals("%"))
                                    vertexRemarks.put(i, rowList.get(i)[j]);
                            } else if (!rowList.get(i)[j].equals("0")) {       //add given edges (weight != 0) in flow network
                                addEdge(i, j, Float.valueOf(rowList.get(i)[j]));
                            }
                        } else {
                            System.err.println("Vertex name in row " + i + 1 + " incorrect. Should be: '" + getVertexName(i) + "'.");
                        }
                    }
                }
            }
        }
        catch(Exception e){
            System.out.println("Could not locate input file '"+filename+"'.");
            System.exit(0);
        }

        /**Modifying the Flow-Network (1): Adding source S */
        addVertex(S, "Source");                     //set 0 as source-vertex
        for (Integer i : nodes) {                         //add path from S to all storages (vertex names starting with 'L')
            if (getVertexName(i).charAt(0) == 'L') {
                addEdge(S, i, Float.MAX_VALUE);
            }
        }

        /**Modifying the Flow-Network (2): Accommodating vertexes with capacities */
//        System.out.println("vertexRemarks: " + vertexRemarks);
        for (Integer i : vertexRemarks.keySet()) {
            if (vertexRemarks.get(i).startsWith("Kapazitaet: ")) {
                Float tempWeight = Float.valueOf(vertexRemarks.get(i).substring(12));
                String tempName = getVertexName(i) + "'";
                Integer n = size();
                addVertex(n, tempName);
//                System.out.println(getVertexName(i) + ": " + getVertexNames(getNeighbours(i)));
                for (Integer j : getNeighbours(i)) {
                    addEdge(n, j, capacity(i, j));
                }
//                System.out.println(getVertexName(n) + ": " + getVertexNames(getNeighbours(n)));
                adjacency.get(i).clear();
//                System.out.println(getVertexName(i) + ": " + getVertexNames(getNeighbours(i)));
                addEdge(i, n, tempWeight);
//                System.out.println(getVertexName(i) + ": " + getVertexNames(getNeighbours(i)));
            }
        }

    }

//    public void addVertex(Integer v) {
//        nodes.add(v);
//        vertexNames.put(v,v.toString());
//        adjacency.put(v, new HashMap<Integer,Float>());
//    }

    public void addVertex(Integer v, String name) {
        nodes.add(v);
        vertexNames.put(v,name);
        adjacency.put(v, new HashMap<Integer,Float>());
    }

    public void addEdge(Integer v, Integer w, Float l) {
        if (v == w) return;		// No loops!
        adjacency.get(v).put(w,l);
    }

    public int size () {
        return nodes.size();
    }

    /** Returns whether the given vertex ID belongs to the graph. */
    public boolean contains (Integer v) {
        return nodes.contains(v);
    }

    public int degree (Integer v) {
        return adjacency.get(v).size();
    }

    /** Returns whether vertices v and w are adjacent. */
    public boolean adjacent (Integer v, Integer w) {
        return adjacency.get(v).containsKey(w);
    }

    public HashSet<Integer> getVertices () {
        return nodes;
    }

    public int getEdgeCount () {
//        System.out.println("--edge count--");
        int edges = 0;
        for (int v : nodes) {
            edges += adjacency.get(v).size();
//            System.out.println(v + ": " + adjacency.get(v));
        }
//        System.out.println("--------------");
        return edges;
    }


    public Set<Integer> getNeighbours (Integer v) {
        return adjacency.get(v).keySet();
    }


    public float capacity(Integer v, Integer w) {
        if (adjacency.get(v).containsKey(w)){
            return adjacency.get(v).get(w);
        }
        else return 0;
    }

    public void setCapacity (Integer v, Integer w, Float c) {
        if (adjacency.get(v).containsKey(w)){
            adjacency.get(v).put(w,c);
        }
    }


    public void deleteVertex (Integer vertex) {          //HashMap<Integer, HashMap<Integer,Float>> adjacency
        System.out.println("Deleting L2...");
        System.out.println(adjacency.get(vertex));
//        for (int neighbor : adjacency.get(vertex).keySet())
//            adjacency.get(neighbor).remove(vertex);
        adjacency.get(vertex).clear();
        System.out.println(adjacency.get(vertex));
//        nodes.remove(vertex);
//        vertexNames.remove(vertex);
        System.out.println("..............");
    }

    public void deleteEdge (Integer u, Integer v){
        adjacency.get(u).remove(v);
    }


    public String getVertexName (Integer i) {
        return vertexNames.get(i);
    }

    public Set<String> getVertexNames (Set<Integer> integers) {
        Set<String> result = new HashSet<>();
        for (Integer i : integers) {
            result.add(getVertexName(i) + "(" + i + ")");
        }
        return result;
    }

//    public void printNetwork(){
//        System.out.println(s.toString());
//        System.out.println(t.toString());
//        for (Integer v : this.nodes){
//            for (Integer u: this.getNeighbors(v)){
//                System.out.println(v.toString()+" "+u.toString()+" "+Float.toString(this.capacity(v,u)));
//            }
//        }
//    }
}