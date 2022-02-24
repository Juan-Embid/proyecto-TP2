package simulator.model;

import java.util.List;

import org.json.JSONObject;

public class TrafficSimulator {
	private RoadMap map;
	private List<Event> eventList;
	private int time;
	private List<Road> roads; //TODO creo que hay que anadirlo
	private List<Vehicle> vehicles; //TODO creo que hay que anadirlo
	private List<Junction> junctions;
	public void addEvent(Event e) {
		
	}
	
	public void advance() {
		time++;
		for (int i = 0; i < eventList.size(); i++) {
			if (time == eventList.get(i).getTime())
				eventList.get(i).execute(map);	
		}
		for (Junction junction : junctions)
			junction.advance(time);
		for (Road road : roads)
			road.advance(time);
	}
	
	public void reset() {
		for (Road road : roads) {
			vehicles = road.getVehicles();
			for (Vehicle vehicle : vehicles)
					road.exit(vehicle);
		}
		for (Event event : events)
			//vaciar lista de eventos
		time = 0;
	}
	
	public JSONObject report() {
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("time", time);
		reportJSON.put("state", map.report());

		return reportJSON;
	}
	
}
