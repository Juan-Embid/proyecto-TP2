package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import simulator.control.Controller;
import simulator.model.Event;
import simulator.model.RoadMap;
import simulator.model.TrafficSimObserver;

public class ControlPanel extends JPanel implements TrafficSimObserver {

		private JFileChooser file;
		private JButton fileLoad;
		private JButton changePollution;
		private JButton changeWeather;
		private JButton run;
		private JButton stop;
		private JSpinner ticks;
		private JButton exit;
		private Controller _ctrl;
		private JLabel tickLabel;
		private boolean _stopped;
		
	ControlPanel(Controller controller){
		_ctrl = controller;
		_stopped = false;
		_ctrl.addObserver(this);
		initGUI();
	}

	private void initGUI() {
		this.setLayout(new BorderLayout());
		JToolBar miTool = new JToolBar();
		this.add(miTool, BorderLayout.NORTH);
		
		//TODO borrar las dos lineas de abajo si no dan problemas
		//file = new JFileChooser("Select File");
		//file.setCurrentDirectory(new File("resorces/examples"));
		fileLoad = createButton("Load File", "resources/icons/open.png");
		miTool.add(fileLoad);
		miTool.addSeparator();
		
		changePollution = createButton("Pullution", "resources/icons/co2class.png");
		miTool.add(changePollution);

		changeWeather = createButton("Weather", "resources/icons/weather.png");
		miTool.add(changeWeather);
		miTool.addSeparator();
		
		run = createButton("Run", "resources/icons/run.png");
		miTool.add(run);
		
		stop = createButton("Stop", "resources/icons/stop.png");
		miTool.add(stop);
		
		tickLabel = new JLabel ("Ticks: ");
		miTool.add(tickLabel); 	
		
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1)); //value, min, max, step
		ticks.setMinimumSize(new Dimension(70, 35));
		ticks.setMaximumSize(new Dimension(70, 35));
		ticks.setPreferredSize(new Dimension(70, 35));
		miTool.add(ticks);
		miTool.addSeparator();

		miTool.add(Box.createGlue());
		exit = createButton("Exit", "resources/icons/exit.png");
		miTool.add(exit);
	}
	private JButton createButton(String phrase, String icon) {
		JButton button = new JButton(new ImageIcon(icon));
		button.setToolTipText(phrase);
		return button;
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