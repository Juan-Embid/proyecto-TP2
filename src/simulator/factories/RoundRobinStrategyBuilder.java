package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchStrategy;
import simulator.model.RoundRobinStrategy;

public class RoundRobinStrategyBuilder extends Builder<LightSwitchStrategy> {

	public RoundRobinStrategyBuilder() {
		super("round_robin_lss");
	}

	@Override
	protected LightSwitchStrategy createTheInstance(JSONObject data) {
		return new RoundRobinStrategy(data.has("timeslot") ? data.getInt("timeslot") : 1);
	}

}
