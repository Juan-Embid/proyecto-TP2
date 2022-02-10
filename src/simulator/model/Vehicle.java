package simulator.model;
import java.util.List;
import org.json.JSONObject;

public class Vehicle extends SimulatedObject{
	private List<Junction> itinerario;
	private int maxSpeed, currentSpeed, localization = 0, pollution, totalPollution, totalDistance;
	private VehicleStatus status;
	private Road road;
	

	Vehicle(String id, int maxSpeed, int pollution, List<Junction> itinerary) {
		super(id); //TODO complete
		//comprobar maxSpeed positivo
		//contClass entre 0 y 10
		//itinerary al menos dos, sino hacer lista...
	}

	@Override
	void advance(int time) {
		//actualilza localizacion...
		//calcula contaminacion...
		//si localizacion actual...
	}

	@Override
	public JSONObject report() {
		/*{
		"type" : "new_vehicle",
		"data" : {
		"time" : 1,
		"id" : "v1",
		"maxspeed" : 100,
		"class" : 3,
		"itinerary" : ["j3", "j1", "j5", "j4"]
		}
		}*/
		return null;
	}
	
	void setSpeed(int s) {
		
	}
	
	void setCentaminationClass(int c) {
		
	}
	
	void moveToNextRoad() {
		
	}
	
	public int getLocation() {
		return 0;
	}

	public int getSpeed() {
		return 0;
	}
	
	public int getMaxSpeed() {
		return 0;
	}
	
	public int getContClass() {
		return 0;
	}
	
	public int getStatus() {
		return 0;
	}
	
	public int getTotalCO2() {
		return 0;
	}
	
	public int getItinerary() {
		return 0;
	}
	
	public int getRoad() {
		return 0;
	}
	
	//setter --> cuando la velocidad del vehiculo no es traveling la ponemos a 0
}
