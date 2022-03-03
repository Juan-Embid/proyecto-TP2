package simulator.factories;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.Event;
import simulator.model.NewVehicleEvent;

public class NewVehicleEventBuilder extends Builder<Event> {

	public NewVehicleEventBuilder() {
		super("new_vehicle");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		List<String> temp = new ArrayList<>();
		int time = data.getInt("time");
		String id = data.getString("id");
		int maxspeed = data.getInt("maxspeed");
		int clas = data.getInt("class");
		JSONArray array = data.getJSONArray("itinerary");
		for(int i = 0; i < array.length(); i++)
			temp.add(array.getString(i));
		
		return new NewVehicleEvent(time, id, maxspeed, clas, temp);
	}

}
