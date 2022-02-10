package simulator.model;
import java.util.List;

public class Vehicle extends SimulatedObject{
	private List<Junction> itinerario;
	private 

	Vehicle(String id) {
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
