package simulator.model;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import simulator.misc.SortedArrayList;


public class TrafficSimulator {
	private RoadMap map;
	private List<Event> eventList;
	private int time;
	
	public TrafficSimulator() {
		map = new RoadMap();
		eventList = new SortedArrayList<Event>();
		time = 0;
	}
	
	public void addEvent(Event e) {
		eventList.add(e);
	}
	
	public void advance() {
		time++;
		List<Event> aux = new ArrayList<>();
		
		for (Event e : eventList) {
			if (time == e.getTime()) {
				aux.add(e);
				e.execute(map);
			}
		}
		eventList.removeAll(aux);

		for (Junction junction : map.getJunctions())
			junction.advance(time);
		for (Road road : map.getRoads())
			road.advance(time);
	}
	
	public void reset() {
		map.reset();
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