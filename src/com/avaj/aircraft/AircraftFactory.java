package com.avaj.aircraft;

import com.avaj.interfaces.Flyable;
import com.avaj.map.Coordinates;

public class AircraftFactory {

    public Flyable newAircraft(String type, String name, int longitude,
                               int latitude, int height){
        if (type.equalsIgnoreCase("jetplane"))
            return (new JetPlane(name, new Coordinates(longitude, latitude, height)));
        else if (type.equalsIgnoreCase("helicopter"))
            return (new Helicopter(name, new Coordinates(longitude, latitude, height)));
        if (type.equalsIgnoreCase("baloon"))
            return (new Baloon(name, new Coordinates(longitude, latitude, height)));
        else
            return null;
    }

}
