package simulator.factories;

import org.json.JSONObject;

import simulator.model.LightSwitchStrategy;
import simulator.model.MostCrowdedStrategy;

public class MostCrowdedStrategyBuilder extends Builder<LightSwitchStrategy> {

	public MostCrowdedStrategyBuilder() {
		super("most_crowded_lss");
	}

	@Override
	protected LightSwitchStrategy createTheInstance(JSONObject data) {
		return new MostCrowdedStrategy(data.has("timeslot") ? data.getInt("timeslot") : 1 );
	}

}
