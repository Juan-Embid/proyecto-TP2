package simulator.control;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	
	private TrafficSimulator trafficSimulator;
	private Factory<Event> eventsFactory;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) {
	if(sim == null || eventsFactory == null)
		throw new IllegalArgumentException("ERROR; Invalid Arguments");
	trafficSimulator = sim;
	this.eventsFactory = eventsFactory;
	}
	
	public void loadEvents(InputStream in) throws JSONException, IOException {
		JSONObject jo = new JSONObject(new JSONTokener(in));
		JSONArray e = jo.getJSONArray("events");
		for (int i = 0; i < e.length(); i++) {
			trafficSimulator.addEvent(eventsFactory.createInstance(e.getJSONObject(i)));
		}
	}
	
	public void run(int n, OutputStream out) {
		
	}
	public void reset() {
		trafficSimulator.reset();
		}
	}