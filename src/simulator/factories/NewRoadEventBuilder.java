package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.Weather;

public abstract class NewRoadEventBuilder extends Builder<Event>{
	int time, length, co2Limit, maxSpeed;
	String id, src, dest, weather;
	Weather weather2;
	NewRoadEventBuilder(String type) {
		super(type);
	}
	
	@Override
	protected Event createTheInstance(JSONObject data) {
		time = data.getInt("time");
		String id = data.getString("id");
		String src = data.getString("src");
		String dest = data.getString("dest");
		length = data.getInt("length");
		co2Limit = data.getInt("co2limit");
		maxSpeed = data.getInt("maxspeed");
		weather = data.getString("weather");
		weather2 = Weather.valueOf(weather.toUpperCase());
		return roadBuilder();
	}
	
	abstract Event roadBuilder();
	
}
