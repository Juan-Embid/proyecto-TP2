package simulator.factories;

import org.json.JSONArray;
import org.json.JSONObject;

import simulator.model.DequeuingStrategy;
import simulator.model.Event;
import simulator.model.LightSwitchStrategy;
import simulator.model.NewJunctionEvent;

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
		String id = data.getString("id");
		JSONArray coor = data.getJSONArray("coor");
		LightSwitchStrategy lsstrat = lsFactory.createInstance(data.getJSONObject("ls_stategy"));
		DequeuingStrategy dqstrat = dqFactory.createInstance(data.getJSONObject("dq_stategy"));
		return new NewJunctionEvent(time, id, lsstrat, dqstrat, coor.getInt(0), coor.getInt(1));
	}

}
