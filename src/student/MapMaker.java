package student;

import model.RailroadBaronsException;
import model.RailroadMap;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class MapMaker implements model.MapMaker {
    @Override
    public RailroadMap readMap(InputStream in) throws RailroadBaronsException {
        BufferedReader file = new BufferedReader(new InputStreamReader(in));
        return null;
    }

    @Override
    public void writeMap(RailroadMap map, OutputStream out) throws RailroadBaronsException {

    }
}
