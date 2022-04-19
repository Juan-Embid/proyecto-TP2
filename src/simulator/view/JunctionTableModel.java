package simulator.view;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.Junction;
import simulator.model.Road;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class JunctionTableModel extends AbstractTableModel implements TrafficSimObserver{

	private static final String[] columnNames = { "Id", "Green", "Queues" };
	
	private List<Junction> juncts;
	
	public JunctionTableModel(Controller _ctrl) {
		juncts = new ArrayList<Junction>();
		_ctrl.addObserver(this);
	}

	public String getColumnName(int i) {
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

		Object s = null;
		String queue = "";
		
		switch (columnIndex)
		{
			case 0:
				s = juncts.get(rowIndex).getId();
				break;
			case 1:
				int indice = juncts.get(rowIndex).getGreenLightIndex();
				
				if (indice == -1)
				{
					s = "NONE";
				}
				else
				{
					s = juncts.get(rowIndex).getInRoads().get(indice);
					object = juncts.get(rowIndex).getInRoads().get(index);
				break;
			case 2:
				for (Road r : juncts.get(rowIndex).getInRoads())
					queue += r.getId() + ":" + r.getVehicles().toString()+ " ";
				
				object = queue;
				break;
			default:
				break;
		}
		
		return object;
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		this.juncts = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		this.juncts = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {		
		this.juncts = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		this.juncts = new ArrayList<>();
		fireTableDataChanged();
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		this.juncts = map.getJunctions();
		fireTableDataChanged();
	}

	@Override
	public void onError(String err) {
		
	}

}
