package simulator.model;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Comparator;
import org.json.JSONObject;


public class Vehicle extends SimulatedObject{
	private List<Junction> itinerary;
	private int maxSpeed, currentSpeed, location, pollution, totalPollution, totalDistance, index;
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
		this.index = 0;
		
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
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("id", _id);
		reportJSON.put("speed", currentSpeed);
		reportJSON.put("distance", totalDistance);
		reportJSON.put("co2", totalPollution);
		reportJSON.put("class", pollution);
		reportJSON.put("status", status);
		
		if (status != VehicleStatus.PENDING && status != VehicleStatus.ARRIVED) {
			reportJSON.put("road", road.getId());
			reportJSON.put("location", location);
		}
		return reportJSON;
	}
	
	void setSpeed(int s) {
		if(s < 0) 
			throw new IllegalArgumentException("Error: invalid argument");
		currentSpeed = Math.min(maxSpeed, s);
	}
	
	void setContClass(int c) {
		if(pollution < 0 || pollution > 10)
			throw new IllegalArgumentException("Error: invalid argument");
		pollution = c;
	}
	
	void moveToNextRoad() {
		
		if (status != VehicleStatus.PENDING && status != VehicleStatus.WAITING)
			throw new IllegalArgumentException("Error: invalid Vehicle status");
		//si acaba de empezar
		if(road != null)
		road.exit(this);
		
		currentSpeed = 0;
		location = 0;
		//si ha acabado
		if(road == null) {
			status = VehicleStatus.TRAVELING;
			road = itinerary.get(index).roadTo(itinerary.get(index+1));
			road.enter(this);
		}else if(itinerary.size() -1 == index) {
			status = VehicleStatus.ARRIVED;
			road = null;
		} //Si est� pending
		else if(status.equals(VehicleStatus.PENDING)) {
			road = itinerary.get(index + 1).roadTo(itinerary.get(index));
		}else { //si no est� pending
			road = road.getDest().roadTo(road.getDest());
		}
		
		index++;
	}
	
	private void setStatus() {
		if (status != VehicleStatus.TRAVELING)
			currentSpeed = 0;
	}
	
	public int getLocation() {
		return location;
	}

	public int getSpeed() {
		return currentSpeed;
	}
	
	public int getMaxSpeed() {
		return maxSpeed;
	}
	
	public int getContClass() {
		return pollution;
	}
	
	public VehicleStatus getStatus() {
		return status;
	}
	
	public int getTotalCO2() {
		return totalPollution;
	}
	
	public List<Junction> getItinerary() {
		return itinerary;
	}
	
	public Road getRoad() {
		return road;
	}
public static class VehicleComparator implements Comparator<Vehicle> {

	@Override
	public int compare(Vehicle o1, Vehicle o2) {
		return o2.getLocation() - o1.getLocation();
	}
		
	}
}
