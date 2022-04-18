package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;
import simulator.model.Vehicle;

public class VehiclesTableModel extends AbstractTableModel implements TrafficSimObserver{
	
	List<Vehicle> vehicles;
	
	private static final String columnNames[] = {"Id", "Location", "Iterinary", "CO2 Class", "Max. Speed", "Speed", "Total CO2", "Distance"}; 
	
	VehiclesTableModel(Controller _ctrl){
		vehicles = new ArrayList<>();
		_ctrl.addObserver(this);
	}
	
	public String getColumName(int i) {
		return columnNames[i];
	}
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return vehicles.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object object = null;
		switch(columnIndex) {
		case 0:
			object = vehicles.get(rowIndex).getId();
		case 1:
			object = vehicles.get(rowIndex).getLocation();
		case 2:
			object = vehicles.get(rowIndex).getItinerary();
		case 3:
			object = vehicles.get(rowIndex).getContClass();
		case 4:
			object = vehicles.get(rowIndex).getMaxSpeed();
		case 5:
			object = vehicles.get(rowIndex).getTotalCO2();
		case 6:
			object = vehicles.get(rowIndex).getTotalDistance();
		}
		
		return object;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		vehicles = map.getVehicles();
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		vehicles = map.getVehicles();
		fireTableDataChanged();		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		vehicles = map.getVehicles();
		fireTableDataChanged();			
	}

	@Override
	public void onError(String err) {
		
	}

}
