package student;

import model.Baron;
import model.RailroadBaronsException;
import model.RailroadMap;
import java.io.*;
import java.util.*;

/**
 * This class is in charge of creating a map layout for the scene of the Railroad Baron Game.
 * @authors Gabriel Jusino, William Johnson
 * @emails gej9887, wcj7833
 */
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
            int easternmost = 0;
            int southernmost = 0;
            int northernmost = 100;
            int westernmost = 100;
            while ( line != null) {
                String[] cut = line.split(" ");
                if (!line.equals("##ROUTES##") && !isRoutes) {
                    int id = Integer.parseInt(cut[0]);
                    int row = Integer.parseInt(cut[1]);
                    if (row > southernmost){
                        southernmost = row;
                    }
                    if (row < northernmost){
                        northernmost = row;
                    }
                    int col = Integer.parseInt(cut[2]);
                    if (col > easternmost){
                        easternmost = col;
                    }
                    if (col < westernmost){
                        westernmost = col;
                    }
                    String name = "";
                    for (int i = 3; i < cut.length; i++) {
                        name = name + cut[i];
                        if (i != (cut.length - 1)) {
                            name = name + " ";
                        }
                    }
                    stations.put(id, new Station(name, row, col, id));
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
                    for (Integer key : this.stations.keySet()){
                        student.Station current = (student.Station)stations.get(key);
                        if (current.getRow() == northernmost & current.getCol() == easternmost){
                            current.setStationLoc(StationLocation.NORTHEASTERNMOST);
                        }
                        else if (current.getRow() == northernmost & current.getCol() == westernmost){
                            current.setStationLoc(StationLocation.NORTHWESTERNMOST);
                        }
                        else if (current.getRow() == southernmost & current.getCol() == easternmost){
                            current.setStationLoc(StationLocation.SOUTHEASTERNMOST);
                        }
                        else if (current.getRow() == southernmost & current.getCol() == westernmost){
                            current.setStationLoc(StationLocation.SOUTHWESTERNMOST);
                        }
                        else if (current.getRow() == northernmost){
                            current.setStationLoc(StationLocation.NORTHERNMOST);
                        }
                        else if (current.getRow() == southernmost){
                            current.setStationLoc(StationLocation.SOUTHERNMOST);
                        }
                        else if (current.getCol() == westernmost){
                            current.setStationLoc(StationLocation.WESTERNMOST);
                        }
                        else if (current.getCol() == easternmost){
                            current.setStationLoc(StationLocation.EASTERNMOST);
                        }
                        else {
                            current.setStationLoc(StationLocation.CENTERED);
                        }
                        if (Integer.parseInt(cut[0]) == key){
                            ((student.Station)stations.get(key)).addNeighbor(stations.get(Integer.parseInt(cut[1])));
                        }
                        else if (Integer.parseInt(cut[1]) == key){
                            ((student.Station)stations.get(key)).addNeighbor(stations.get(Integer.parseInt(cut[0])));
                        }
                    }
                }
                line = file.readLine();
            }
            model.RailroadMap map = new student.RailroadMap(southernmost+1, easternmost+1, this.stations);
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
       ArrayList<model.Route> routes = (ArrayList<model.Route>)map.getRoutes();
        HashMap<Integer, model.Station> Stations = ((student.RailroadMap)map).getStations();
       PrintWriter print = new PrintWriter(out);
       for (int i = 0; i < Stations.size(); i++){
           student.Station station = (student.Station)Stations.get(i);
           print.println(i + " " + station.getRow() + " " + station.getCol() + " " + station.getName());
       }
       print.println("##ROUTES##");
       for (model.Route route : routes){
           student.Station origin = (student.Station)route.getOrigin();
           student.Station destination = (student.Station)route.getDestination();
           print.println(origin.ID() + " " + destination.ID() + " " + route.getBaron());
       }
       print.close();
       print.flush();
    }
}
