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

	Junction(String id, LightSwitchStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
			//Observa que la constructora recibe las estrategias como parámetros. Debe comprobar que
		//lsStrategy y dqStrategy no son null, y que xCoor y yCoor no son negativos, y lanzar una
		//excepción en caso contrario
		
		super(id);
	}
	
	@Override
	void advance(int time) {
		
	}

	@Override
	public JSONObject report() {
		return null;
	}

	void addIncommingRoad(Road r) {
		
	}
	
	void addOutGoingRoad(Road r) {
		
	}
	
	void enter(Vehicle v) {
		
	}
	
	Road roadTo(Junction j) {
		
		return null;
	}
	
	/*void advance(time t) {
		
	}*/
	
}
