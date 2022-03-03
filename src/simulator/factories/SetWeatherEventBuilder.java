package simulator.factories;

import org.json.JSONObject;

import simulator.model.Event;

public class SetWeatherEventBuilder extends Builder<Event> {

	SetWeatherEventBuilder() {
		super("set_weather");
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		int time = data.getInt("time");
		i
		return null;
	}

}
