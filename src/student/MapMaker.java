package student;

import model.Baron;
import model.RailroadBaronsException;
import model.RailroadMap;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class MapMaker implements model.MapMaker {
    private HashMap<Integer, model.Station> stations = new HashMap<>();
    private ArrayList<Route> routes = new ArrayList<>();

    /**
     * Loads a {@linkplain RailroadMap map} using the data in the given
     * {@linkplain InputStream input stream}.
     *
     * @param in The {@link InputStream} used to read the {@link RailroadMap
     *           map} data.
     * @return The {@link RailroadMap map} read from the given
     * {@link InputStream}.
     * @throws RailroadBaronsException If there are any problems reading the
     *                                 data from the {@link InputStream}.
     */
    @Override
    public RailroadMap readMap(InputStream in) throws RailroadBaronsException {
        BufferedReader file = new BufferedReader(new InputStreamReader(in));
        try {
            String line = file.readLine();
            Boolean isRoutes = false;
            while ( line != null) {
                String[] cut = line.split(" ");
                if (!line.equals("##ROUTES##") && !isRoutes) {
                    int id = Integer.parseInt(cut[0]);
                    int row = Integer.parseInt(cut[1]);
                    int col = Integer.parseInt(cut[2]);
                    String name = "";
                    for (int i = 3; i < cut.length; i++) {
                        name = name + cut[i];
                        if (i != (cut.length - 1)) {
                            name = name + " ";
                        }
                    }
                    stations.put(id, new Station(name, row, col));
                }
                else if (line.equals("##ROUTES##")){
                    isRoutes = true;
                }
                else{
                    Baron owner;
                    if (cut[2].equals("UNCLAIMED")){
                        owner = Baron.UNCLAIMED;
                    }
                    else if (cut[2].equals("RED")){
                        owner = Baron.RED;
                    }
                    else if (cut[2].equals("GREEN")){
                        owner = Baron.GREEN;
                    }
                    else if (cut[2].equals("YELLOW")){
                        owner = Baron.YELLOW;
                    }
                    else {
                        owner = Baron.BLUE;
                    }
                    routes.add(new Route(Integer.parseInt(cut[0]), Integer.parseInt(cut[1]), owner, this.stations));
                }
                line = file.readLine();
            }
            model.RailroadMap map = new student.RailroadMap(20, 25);
            for (model.Route route : this.routes){
                map.routeClaimed(route);
            }
            return map;
        }
        catch(IOException e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Writes the specified {@linkplain RailroadMap map} in the Railroad
     * Barons map file format to the given {@linkplain OutputStream output
     * stream}. The written map should include an accurate record of any
     * routes that have been claimed, and by which {@linkplain Baron}.
     *
     * @param map The {@link RailroadMap map} to write out to the
     *            {@link OutputStream}.
     * @param out The {@link OutputStream} to which the
     *            {@link RailroadMap map} data should be written.
     * @throws RailroadBaronsException If there are any problems writing the
     *                                 data to the {@link OutputStream}.
     */
    @Override
    public void writeMap(RailroadMap map, OutputStream out) throws RailroadBaronsException {
       // map.getRoutes()
    }
}
