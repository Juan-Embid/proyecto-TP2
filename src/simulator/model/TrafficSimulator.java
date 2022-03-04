package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;


public class TrafficSimulator {
	private RoadMap map;
	private List<Event> eventList;
	private int time;
	private List<Junction> junctions;
	
	public TrafficSimulator() {
		reset();
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
	
	public void advance() {
		time++;
		while(!eventList.isEmpty()) {
			if (time == eventList.get(0).getTime()) {
				eventList.get(0).execute(map);	
			eventList.remove(0);}
		}
		
		for (Junction junction : junctions)
			junction.advance(time);
		for (Road road : map.getRoads())
			road.advance(time);
	}
	
	

	public void reset() {
		map = new RoadMap();
		eventList = new SortedArrayList<>();
		junctions = new ArrayList<>();
		time = 0;
	}
	
	public JSONObject report() {
		JSONObject reportJSON = new JSONObject();
		
		reportJSON.put("time", time);
		reportJSON.put("state", map.report());

		return reportJSON;
	}
	
}