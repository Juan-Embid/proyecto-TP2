package simulator.view;

import java.awt.GridLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;

import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {

		private boolean _stopped;
		private JFileChooser file;
		private JButton fileLoad;
		private JButton changePollution;
		private JButton changeWeather;
		private JButton run;
		private JButton stop;
		private JSpinner ticks;
		private JButton exit;
		private Controller _ctrl;
		
	ControlPanel(Controller controller){
		_ctrl = controller;
		_stopped = false;
		_ctrl.addObserver(this);
		initGUI();
	}

	private void initGUI() {
		// TODO Auto-generated method stub
		ticks = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1)); //value, min, max, step
		JPanel mainPanel = new JPanel(new GridLayout());
		mainPanel.setLayout(new GridLayout());
	}
	
	private JButton createButton() {
		JButton button = new JButton();
		return button;
		//TODO terminar
	}
	
	private void run_sim(int n) {
		if (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
				} catch (Exception e) {
		// TODO show error message
			_stopped = true;
			return;
		}
		SwingUtilities.invokeLater(new Runnable() {
		@Override
		
		public void run() {
			run_sim(n - 1);
		}
		});
			} else {
				enableToolBar(true);
				_stopped = true;
				}
			}
	
		private void enableToolBar(boolean b) {
		// TODO Auto-generated method stub
		
	}

		private void stop() {
		_stopped = true;
	}
		
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {
		// TODO Auto-generated method stub
		
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
