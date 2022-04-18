package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

@SuppressWarnings("serial")
public class EventsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private List<Event> events;
	private static final String columns[] = {"Time", "Desc."};
	
	EventsTableModel(Controller _ctrl){
		events = new ArrayList<>();
		_ctrl.addObserver(this);
	}
	
	public String getColumnName(int i) {
		return columns[i];
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}
	
	@Override
	public int getRowCount() {
		return events.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnsIndex) {
		Object r = null;
		switch (columnsIndex) {
		case 0:
			r =events.get(rowIndex).getTime();
			break;
		case 1:
			r = events.get(rowIndex).toString();	
			break;
		}
		return r;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		this.events = events;
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		if(!this.events.contains(e)) {
			this.events.add(e);
			fireTableDataChanged();
		}
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.events = new ArrayList<>();
		fireTableDataChanged();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		this.events = events;
		fireTableDataChanged();
	}

	@Override
	public void onError(String err) {
		
	}
}