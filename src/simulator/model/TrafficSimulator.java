package simulator.model;

import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;

public class TrafficSimulator {
	private RoadMap map;
	private List<Event> eventList;
	private int time;
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
		for (Road road : map.getRoads())
			road.advance(time);
	}
	
	public void reset() {
		map = new RoadMap(); //TODO revisar si hay que llamar a esta funcion map.reset();
		eventList.clear();
		time = 0;
	}
	
	public JSONObject report() {
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("time", time);
		reportJSON.put("state", map.report());

		return reportJSON;
	}
	
}