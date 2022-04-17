package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final String[] columnNames = { "Id", "Green", "Queues" };
	
	private List<Junction> juncts;
	
	public JunctionTableModel(Controller _ctrl) {
		juncts = new ArrayList<>();
		_ctrl.addObserver(this);
	}

	public String getcolumnName(int i) {
		return columnNames[i];
	}
	
	
	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return juncts.size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Object temp = null;
		switch (columnIndex) {
		case 0:
			temp = juncts.get(rowIndex).getId();
			break;
		case 1:
			temp = juncts.get(rowIndex).getId();
			break;
		case 2:
			temp = juncts.get(rowIndex).getId();
			break;
		}
		return temp;
	}

	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		this.juncts = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.juncts = new ArrayList<>();
		fireTableDataChanged();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onError(String err) {
		// TODO Auto-generated method stub
		
	}

}
