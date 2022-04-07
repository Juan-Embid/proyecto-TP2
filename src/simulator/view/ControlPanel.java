package simulator.view;

import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
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

		private JFileChooser file;
		private JButton fileLoad;
		private JButton changePollution;
		private JButton changeWeather;
		private JButton run;
		private JButton stop;
		private JSpinner ticks;
		private JButton exit;
		private Controller _ctrl;
		private boolean _stopped;
		
	ControlPanel(Controller controller){
		_ctrl = controller;
		_stopped = false;
		_ctrl.addObserver(this);
		initGUI();
	}

	private void initGUI() {
		file = new JFileChooser("Select File");
		file.setCurrentDirectory(new File("resorces/examples"));
		fileLoad = createButton("Load File", "resources/icons/open.png");
		/*fileLoad.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					int chosen = file.showOpenDialog(null);
					if(chosen == JFileChooser.APPROVE_OPTION) {
						File chosenFile = file.getSelectedFile();
						_ctrl.reset();
						_ctrl.loadEvents(new FileInputStream(chosenFile));
					}
				}catch(FileNotFoundException exception){
				JOptionPane.showMessageDialog(null, "File not found", "Search Error", JOptionPane.ERROR_MESSAGE);
				}
			}
		});*/
		this.add(fileLoad);
		
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1)); //value, min, max, step
		this.add(ticks);
		
		changePollution = createButton("Pullution", "resources/icons/co2class.png");
		this.add(changePollution);
		
		changeWeather = createButton("Weather", "resources/icons/weather.png");
		this.add(changeWeather);
		
		run = createButton("Run", "resources/icons/run.png");
		this.add(run);
		
		stop = createButton("Stop", "resources/icons/stop.png");
		this.add(stop);
		
		exit = createButton("Exit", "resources/icons/exit.png");
		this.add(exit);
	}
	private JButton createButton(String phrase, String icon) {
		JButton button = new JButton(phrase, new ImageIcon(icon));
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
