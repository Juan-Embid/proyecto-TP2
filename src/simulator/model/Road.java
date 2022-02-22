package simulator.model;
import java.util.List;
import org.json.JSONObject;

abstract class Road extends SimulatedObject{
	private Junction originCross, destinyCross;
	private int length, maxVelocity, currentVelocity, pollutionAlert, totalPollution;
	private Weather enviCondition;
	private List<Vehicle> vehicles;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id);
		//maxSpeed es positivo; contLimit es no negativo; length es positivo; srcJunc, destJunc y weather son distintos de null
	}

	@Override	
	void advance(int time) {
		//llama a reducetotalcontamination
		//llama a updatespeedlimit
		//recorre lista de vehiculos...
	}

	@Override
	public JSONObject report() {
		/*{
		"id" : "r3",
		"speedlimit" : 100,
		"weather" : "SUNNY",
		"co2" : 100,
		"vehicles" : ["v1","v2",...],
		}*/
		return null;
	}
	
	void enter(Vehicle v) {
		
	}
	
	void exit(Vehicle v) {
		
	}
	
	void setWeather(Weather w) {
		
	}
	
	void addContamination(int c) {
		totalPollution += c;
	}
	
	abstract void reduceTotalContamination();
	
	abstract  void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	int getLength() {
		return length;
	}
	
	Junction getDest() {
		return destinyCross;
	}
	
	void getSrc() {}
	
	void getWeather() {}
	
	void getContLimit() {}
	
	void getMaxSpeed() {}
	
	void getTotalCO2() {}
	
	void getSpeedLimit() {}
	
	void getVehicles() {
		//debe devolver una lista de solo lectura...
	}

}
