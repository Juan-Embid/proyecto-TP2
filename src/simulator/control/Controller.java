package simulator.control;

import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;
import org.json.JSONTokener;

import simulator.factories.Factory;
import simulator.model.Event;
import simulator.model.TrafficSimulator;

public class Controller {
	
	private TrafficSimulator trafficsimulator;
	private Factory<Event> eventsfactory;
	
	public Controller(TrafficSimulator sim, Factory<Event> eventsFactory) {
	
	}
	
	public void loadEvents(InputStream in) {
		JSONObject jo = new JSONObject(new JSONTokener(in));
	}
	
	public void run(int n, OutputStream out) {
		
	}
	public void reset() {
		trafficsimulator.reset();
	}
	}
	
	

