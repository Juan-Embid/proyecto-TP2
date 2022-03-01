package simulator.model;
import java.util.Collections;
import simulator.model.Vehicle.VehicleComparator;
import java.util.List;
import org.json.JSONObject;

abstract class Road extends SimulatedObject{
	Junction originCross, destinyCross;
	int length, maxVelocity, currentVelocity, pollutionAlert, totalPollution, speedLimit;
	Weather enviCondition;
	List<Vehicle> vehicles;

	Road(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id);
		if(maxVelocity < 0 || pollutionAlert < 0 || length < 0 || originCross == null || destinyCross == null || weather == null)
			throw new IllegalArgumentException("Argument(s) invalids");
		destinyCross = destJunc;
		originCross = srcJunc;
		maxVelocity = maxSpeed;
		speedLimit = maxSpeed;
		pollutionAlert = contLimit; 
		this.length = length;
		enviCondition = weather;
		originCross.addOutGoingRoad(this);
		destinyCross.addIncommingRoad(this);
		
	}

	@Override	
	public void advance(int time) {
		
		reduceTotalContamination();
		updateSpeedLimit();
		for (Vehicle vehicle : vehicles) {
			vehicle.setSpeed(calculateVehicleSpeed(vehicle));
			vehicle.advance(time);
		}
		//TODO ORDENAR LOS COCHES POR LOCALIZACION
		Collections.sort(vehicles, new VehicleComparator());
	}

	@Override
	public JSONObject report() {
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("id", _id);
		reportJSON.put("speedlimit", maxVelocity);
		reportJSON.put("weather", enviCondition);
		reportJSON.put("co2", totalPollution);
		reportJSON.put("vehicles", vehicles);

		return reportJSON;
	}
	
	public void enter(Vehicle v) {
		if(v.getLocation() != 0 || v.getSpeed() != 0)
			throw new IllegalArgumentException("Error: invalid Vehicle status");
	vehicles.add(v);
	}
	
	public void exit(Vehicle v) {
		for (Vehicle vehicle : vehicles) {
			if(v == vehicle)
				vehicles.remove(vehicle);
		}
	}
	
	 public void setWeather(Weather w) {
		if(w == null)
			throw new IllegalArgumentException("Error: invalid Vehicle status");
		enviCondition = w;	
	}
	
	public void addContamination(int c) {
		if(c < 0)
		throw new IllegalArgumentException("Error: invalid Vehicle status");
		totalPollution += c;
	}
	
	abstract void reduceTotalContamination();
	
	abstract  void updateSpeedLimit();
	
	abstract int calculateVehicleSpeed(Vehicle v);
	
	public int getLength() {
		return length;
	}
	
	public Junction getDest() {
		return destinyCross;
	}
	
	public Junction getSrc() {
		return originCross;
	}
	
	public Weather getWeather() {
		return enviCondition;
	}
	
	public int getPollutionAlert() {
		return pollutionAlert;
	}
	
	public int getMaxVelocity() {
		return maxVelocity;
	}
	
	public int getTotalCO2() {
		return totalPollution;
	}
	
	public int getSpeedLimit() {
		return speedLimit;
	}
	
	public List <Vehicle> getVehicles() {
		return Collections.unmodifiableList(vehicles);
	}

}
