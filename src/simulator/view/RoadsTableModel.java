package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class RoadsTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final String[] columnNames = { "Id", "Length", "Weather", "Max. Speed", "Speed Limit", "Total CO2", "CO2 Limit" };

	private List<Road> roads;
	
	public RoadsTableModel(Controller _ctrl) {
		roads = new ArrayList<>();
		_ctrl.addObserver(this);
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}
	
	public String getcolumnName(int i) {
		return columnNames[i];
	}
	
	@Override
	public int getRowCount() {
		return roads.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		
		Object object = null;
		switch(columnIndex) {
		case 0:
			object = roads.get(rowIndex).getId();
		case 1:
			object = roads.get(rowIndex).getLength();
		case 2:
			object = roads.get(rowIndex).getWeather();
		case 3:
			object = roads.get(rowIndex).getMaxSpeed();
		case 4:
			object = roads.get(rowIndex).getSpeedLimit();
		case 5:
			object = roads.get(rowIndex).getTotalCO2();
		case 6:
			object = roads.get(rowIndex).getContLimit();
		}
		return object;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		roads = map.getRoads();
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		roads = new ArrayList<>();
		fireTableDataChanged();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onError(String err) {
		
	}

}
