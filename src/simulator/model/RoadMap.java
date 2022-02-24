package simulator.model;

import java.util.List;
import java.util.Map;

import org.json.JSONObject;

public class RoadMap {
	List<Junction> crossRoad;
	List<Road> roads;
	List<Vehicle> vehicles;
	Map<String,Junction> crossMap;
	Map<String,Road> roadMap;
	Map<String,Vehicle> vehiclesMap;
	
	void addJunction(Junction j) {
		
	}
	
	void addRoad(Road r){
		
	}
	void addVehicle(Vehicle v) {
		
	}
	
	public Junction getJunction(String id) {
		
		return null;
	}
	
	public Road getRoad(String id) {
		
		return null;
	}
	
	public Vehicle getVehicle(String id) {
		
		return null;
	}
	
	public List<Junction>getJunctions(){
		
		return null;
	}
	
	public List<Road>getRoads(){
		
		return null;
	}
	
	public List<Vehicle>getVehicles(){
		
		return null;
	}
	
	void reset() {
		
	}
	
	public JSONObject report() {
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("junctions", crossRoad);
		reportJSON.put("road", roads);
		reportJSON.put("vehicles", vehicles);

		return reportJSON;
	}
	
}
