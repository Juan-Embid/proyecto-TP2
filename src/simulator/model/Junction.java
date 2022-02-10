package simulator.model;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;

public class Junction extends SimulatedObject{
	private List<Road> roadInList;
	private Map<Junction, Road> roadOutList;
	private List<List<Vehicle>> queue;
	private int greenLight, lastLightChange, x, y;
	private LightSwitchStrategy lightChangeStrategy;
	private DequeuingStrategy extractElements;

	Junction(String id) {
		super(id);
	}

	@Override
	void advance(int time) {
		
	}

	@Override
	public JSONObject report() {
		return null;
	}

}
