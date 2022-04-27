package simulator.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingUtilities;
import simulator.control.Controller;
import simulator.misc.Pair;
import simulator.model.Event;
import simulator.model.NewSetContClassEvent;
import simulator.model.RoadMap;
import simulator.model.SetWeatherEvent;
import simulator.model.TrafficSimObserver;
import simulator.model.Weather;

@SuppressWarnings("serial") // para que me quite el warning del serial, que por alguna razon todo el rato sale
public class ControlPanel extends JPanel implements TrafficSimObserver {

	private volatile Thread _thread; // se comparte info entre los threads de una misma clase
	private JFileChooser file;
	private JButton fileLoad;
	private JButton changePollution;
	private JButton changeWeather1;
	private JButton run;
	private JButton stop;
	private JSpinner ticks;
	private JSpinner delay;
	private JButton exit;
	private Controller _ctrl;
	private JLabel tickLabel;
	private JLabel delayLabel;
	private boolean _stopped;
	private ChangeCO2ClassDialog changeCO2;
	private ChangeWeatherDialog changeWeather;
	private RoadMap map;
	private int time1;
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
		
		// FILE BUTTON
		file = new JFileChooser("File Chooser");
		file.setCurrentDirectory(new File("resources/examples"));
		fileLoad = createButton("Load File", "resources/icons/open.png");
		fileLoad.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					int option = file.showOpenDialog(ControlPanel.this.getParent());
					if (option == JFileChooser.APPROVE_OPTION) {
						File openFile = file.getSelectedFile();
						_ctrl.reset();
						_ctrl.loadEvents(new FileInputStream(openFile));
					}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(getParent(), e.getMessage(), "File not found", JOptionPane.WARNING_MESSAGE); // para que se ponga encima y centrado, mensaje, cabecera, tipo de mensaje

				}
			}
		});
		miTool.add(fileLoad);
		miTool.addSeparator();
		
		// POLLUTION BUTTON
		changePollution = createButton("Pullution", "resources/icons/co2class.png");
		changePollution.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					changeCO2Function();
				} catch (Exception e){
					e.printStackTrace();
				}
			}
		});
		miTool.add(changePollution);

		// WHEATHER BUTTON
		changeWeather1 = createButton("Weather", "resources/icons/weather.png");
		changeWeather1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				try {
					changeWeatherFunction();	
				} catch (Exception e){
					e.printStackTrace();
				}			}
		});
		miTool.add(changeWeather1);
		miTool.addSeparator();
		
		// RUN BUTTON 
		run = createButton("Run", "resources/icons/run.png");
		run.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				_stopped = false;
				enableToolBar(true);
				run_sim((int) ticks.getValue(), (long) delay.getValue());
			}
		});
		miTool.add(run);
		
		// STOP BUTTON
		stop = createButton("Stop", "resources/icons/stop.png");
		stop.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				stop();
				enableToolBar(false);
				_stopped = true;			}
		});
		miTool.add(stop);
		
		// TICKS 
		tickLabel = new JLabel ("Ticks: ");
		miTool.add(tickLabel); 	
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 1000, 1)); //value, min, max, step
		ticks.setMinimumSize(new Dimension(70, 35));
		ticks.setMaximumSize(new Dimension(70, 35));
		ticks.setPreferredSize(new Dimension(70, 35));
		miTool.add(ticks);
		miTool.addSeparator();
		
		// DELAY 
		delayLabel = new JLabel ("Delay: ");
		miTool.add(delayLabel); 	
		delay = new JSpinner(new SpinnerNumberModel(10, 0, 1000, 1)); //value, min, max, step
		delay.setMinimumSize(new Dimension(70, 35));
		delay.setMaximumSize(new Dimension(70, 35));
		delay.setPreferredSize(new Dimension(70, 35));
		miTool.add(delay);
		miTool.addSeparator();
		
		// EXIT BUTTON
		miTool.add(Box.createGlue());
		exit = createButton("Exit", "resources/icons/exit.png");
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				int exitDialog = JOptionPane.showConfirmDialog(getParent(), "Are you sure you want to quit?", "Quit", JOptionPane.YES_NO_OPTION);
				if (exitDialog == 0)
					System.exit(0);
			}
		});
		miTool.add(exit);
	}
	
	private JButton createButton(String phrase, String icon) {
		JButton button = new JButton(new ImageIcon(icon));
		button.setToolTipText(phrase);
		return button;
	}
	
	private void run_sim(int n, long m) {
		while (n > 0 && !_stopped) {
			try {
				_ctrl.run(1);
			} catch (Exception e) {
				_stopped = true;
				return;
			}
			// sleep con el error handler. A lo mejor sería mejor poner el throws interruptedexception
			try {Thread.sleep((long) delay.getValue());}catch(InterruptedException e) {System.out.println(e);}
			//n--; // TODO hay que hacer esto y despues hacer el n - 1??
			SwingUtilities.invokeLater(new Runnable() {
				@Override
				public void run() {
					run_sim(n - 1, m); // TODO comprobar que la m no hay que tocarla
				}
			});
		}
		if (n <= 0 && !_stopped) { // TODO comprobar que hay que poner este if con el while
			_stopped = true;
			enableToolBar(true);
		}
	}
	
	protected void changeWeatherFunction() {
		changeWeather = new ChangeWeatherDialog((Frame) SwingUtilities.getWindowAncestor(this));
		if (changeWeather.open(map) != 0) {
			List<Pair<String, Weather>> cs = new ArrayList<>();
			cs.add(new Pair<String, Weather>(changeWeather.getRoad().getId(), changeWeather.getWeather()));
			_ctrl.addEvent(new SetWeatherEvent(time1+changeWeather.getTicks(), cs));
		}
	}
	
	protected void changeCO2Function() {
		changeCO2 = new ChangeCO2ClassDialog((Frame) SwingUtilities.getWindowAncestor(this));
		if (changeCO2.open(map) != 0) {
			List<Pair<String, Integer>> cs = new ArrayList<>();
			cs.add(new Pair<String, Integer>(changeCO2.getVehicle().getId(), changeCO2.getCO2Class()));
			_ctrl.addEvent(new NewSetContClassEvent(time1+changeCO2.getTicks(), cs));
		}
	}
	
	private void enableToolBar(boolean b) { // deshabilitamos todos los botones excepto el del stop
		fileLoad.setEnabled(_stopped);
		changePollution.setEnabled(_stopped);
		changeWeather1.setEnabled(_stopped);
		run.setEnabled(_stopped);
		stop.setEnabled(b);
		exit.setEnabled(_stopped);
	}

	private void stop() {
		_stopped = true;
	}
	
	@Override
	public void onAdvanceStart(RoadMap map, List<Event> events, int time) {		
	}

	@Override
	public void onAdvanceEnd(RoadMap map, List<Event> events, int time) {		
		this.map = map;
		time1 = time;	
	}

	@Override
	public void onEventAdded(RoadMap map, List<Event> events, Event e, int time) {		
		this.map = map;
		time1 = time;	
	}

	@Override
	public void onReset(RoadMap map, List<Event> events, int time) {		
		this.map = map;
		time1 = time;	
	}

	@Override
	public void onRegister(RoadMap map, List<Event> events, int time) {
		this.map = map;
		time1 = time;	
	}

	@Override
	public void onError(String err) {		
	}
}