package simulator.factories;

import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchStrategy;

public class NewJunctionEventBuilder extends Builder<Event> {

	Factory<LightSwitchStrategy> lsFactory;
    Factory<DequeuingStrategy> dqFactory;

	NewJunctionEventBuilder(String type, Factory<LightSwitchStrategy> lsFactory, Factory<DequeuingStrategy> dqFactory) {
		super("new_junction");
		this.lsFactory = lsFactory;
		this.dqFactory = dqFactory;
	}

	@Override
	protected Event createTheInstance(JSONObject data) {
		int time = data.getInt("time");
		
		return null;
	}

}
