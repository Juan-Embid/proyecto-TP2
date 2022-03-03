package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public class NewInterCityRoadEventBuilder extends Builder<Event> {

	NewInterCityRoadEventBuilder(String type) {
		super(type);
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		int time = data.getInt("time");
		String id = data.getString("id");
		String src = data.getString("src");
		String dest = data.getString("dest");
		int length = data.getInt("length");
		int co2Limit = data.getInt("co2limit");
		int maxSpeed = data.getInt("maxspeed");
		String weather = data.getString("weather");
		return null;
	}

}
/*{
"type" : new_inter_city_road
"data" : {
	"time" : 1,
	"id" : "r1",
	"src" : "j1",
	"dest" : "j2",
	"length" : 10000,
	"co2limit" : 500
	"maxspeed" : 120,
	"weather" : "SUNNY"
}
}*/