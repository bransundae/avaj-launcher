package com.avaj.aircraft;

import com.avaj.Main;
import com.avaj.aircontrol.WeatherTower;
import com.avaj.interfaces.Flyable;
import com.avaj.map.Coordinates;

import java.util.HashMap;

public class JetPlane extends Aircraft implements Flyable {

    private WeatherTower weatherTower;

    JetPlane(String name, Coordinates coordinates){
        super(name, "JetPlane" ,coordinates);
    }

    public void updateConditions(){
        String weather = weatherTower.getWeather(this.getCoordinates());
        HashMap<String, String> messages = new HashMap<>();
        messages.put("SUN", "It's sunny : Latitude +10 and Height +2");
        messages.put("RAIN", "It's rainy : Latitude +5");
        messages.put("FOG", "It's foggy : Latitude +1");
        messages.put("SNOW", "It's snowy : Height -7");
        messages.put("LANDING", "I'm landing at coordinates ");

        if (weather.equals("SUN")) {
            this.mCoordinates.mutateLatitude(10);
            this.mCoordinates.mutateHeight(2);
        }
        else if (weather.equals("RAIN")) {
            this.mCoordinates.mutateLatitude(5);
        }
        else if (weather.equals("FOG")){
            this.mCoordinates.mutateLatitude(1);
        }
        else {
            this.mCoordinates.mutateHeight(-7);
        }

        Main.writer.println("JetPlane#" + this.mName + "(" + this.mId + "): "
                + messages.get(weather) + " : new coordinates " + this.mCoordinates);

        if (this.getCoordinates().getHeight() == 0){

            Main.writer.println("JetPlane#" + this.mName + "(" + this.mId + "): "
                    + messages.get("LANDING") + this.mCoordinates);
            this.weatherTower.unregister(this);
        }


    }

    @Override
    public void registerTower(WeatherTower weatherTower) {
        this.weatherTower = weatherTower;
        this.weatherTower.register(this);
    }

}
