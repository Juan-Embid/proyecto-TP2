package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewInterCityRoadEvent;
import simulator.model.Weather;

public class NewInterCityRoadEventBuilder extends Builder<Event> {

	NewInterCityRoadEventBuilder() {
		super("new_inter_city_road");
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
		Weather weath = null;
		 switch (weather) {
		case "STORM":
			weath = Weather.STORM;
			break;
		case "RAINY":
			weath = Weather.RAINY;
			break;
		case "WINDY":
			weath = Weather.WINDY;
			break;
		case "CLOUDY":
			weath = Weather.CLOUDY;
			break;
		case "SUNNY":
			weath = Weather.SUNNY;
			break;
		}
		return new NewInterCityRoadEvent(time, id, src, dest, length, co2Limit, maxSpeed, weath);
	}

}