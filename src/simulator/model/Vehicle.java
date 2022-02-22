package simulator.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.json.JSONObject;


public class Vehicle extends SimulatedObject{
	private List<Junction> itinerary;
	private int maxSpeed, currentSpeed, location, pollution, totalPollution, totalDistance;
	private VehicleStatus status;
	private Road road;

	Vehicle(String id, int maxSpeed, int pollution, List<Junction> itinerary) {
		super(id); 
		if (maxSpeed < 0 || pollution < 0 || pollution > 10 || itinerary.size() < 2)
			throw new IllegalArgumentException("Error: invalid arguments");
		
		this.maxSpeed = maxSpeed;
		this.pollution = pollution;
		this.currentSpeed = 0;
		this.location = 0;
		this.totalPollution = 0;
		this.totalDistance = 0;
		this.itinerary = Collections.unmodifiableList(new ArrayList<>(itinerary));
		this.status = VehicleStatus.PENDING;
		this.road = null;
		
	}

	@Override
	void advance(int time) {
		if(status.equals(VehicleStatus.TRAVELING)) {
			int previousLocation = location;
			//a)
			location = Math.min(location + currentSpeed, road.getLength());
			totalDistance += (location - previousLocation);
			//b)
			int c = (location - previousLocation) * pollution;
			totalPollution += c;
			road.addContamination(c);
			//c)
			if(location >= road.getLength()) {
				road.getDest().enter(this);;
				status = VehicleStatus.WAITING;
				currentSpeed = 0;
			}
			
		}
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
		if(s < 0) 
			throw new IllegalArgumentException("Error: invalid argument");
		
		currentSpeed = Math.min(maxSpeed, s);
		}
	
	
	void setCentaminationClass(int c) {
		if(pollution < 0 || pollution > 10)
			throw new IllegalArgumentException("Error: invalid argument");
		pollution = c;
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
