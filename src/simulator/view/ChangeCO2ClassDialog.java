package simulator.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.model.RoadMap;
import simulator.model.Vehicle;

@SuppressWarnings("serial")
public class ChangeCO2ClassDialog extends JDialog {
	
	private JPanel emerge;
	private JLabel text;
	private JLabel vehicleText;
	private JLabel ticksText;
	private JLabel co2Text;
	private JComboBox<Vehicle> vehicle;
	private JComboBox<Integer> co2;
	private JSpinner ticks;
	
	private JPanel buttons;
	private JPanel options;
	private JButton ok;
	private JButton cancel;

	private int estado = 0;
	private DefaultComboBoxModel<Vehicle> vehicleModel;
	private DefaultComboBoxModel<Integer> co2Model;

	public ChangeCO2ClassDialog(Frame father) {
		
		super(father, true);
		initGUI();
	}

	private void initGUI() {
		
		setTitle("Change CO2 Class");
		
		emerge = new JPanel();
		emerge.setLayout(new BoxLayout(emerge, BoxLayout.Y_AXIS));
		setContentPane(emerge);
		
		text = new JLabel("<html>Schedule an event to change the CO2 class of a vehicle after a given number of<br>simulation ticks form now.</html>");
		text.setAlignmentX(CENTER_ALIGNMENT);
		emerge.add(text);
		emerge.add(Box.createRigidArea(new Dimension(0, 20)));		
	
		buttons = new JPanel();
		buttons.setAlignmentX(CENTER_ALIGNMENT);
		emerge.add(buttons);
		
		vehicleText = new JLabel("Vehicle: ", JLabel.CENTER);
		vehicleModel = new DefaultComboBoxModel<Vehicle>();
		vehicle = new JComboBox<Vehicle>(vehicleModel);
		vehicle.setVisible(true);
		buttons.add(vehicleText);
		buttons.add(vehicle);
		
		co2Text = new JLabel("CO2 Class: ", JLabel.CENTER);
		co2Model = new DefaultComboBoxModel<Integer>();
		co2 = new JComboBox<Integer>(co2Model);
		co2.setVisible(true);
		buttons.add(co2Text);
		buttons.add(co2);
		
		ticks = new JSpinner();
		ticksText = new JLabel("Ticks: ", JLabel.CENTER);
		ticks = new JSpinner(new SpinnerNumberModel(10, 1, 99999, 1));
		ticks.setMinimumSize(new Dimension(80, 30));
		ticks.setMaximumSize(new Dimension(200, 30));
		ticks.setPreferredSize(new Dimension(80, 30));
		
		buttons.add(ticksText);
		buttons.add(ticks);
		
		options = new JPanel();
		options.setAlignmentX(CENTER_ALIGNMENT);
		emerge.add(options);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				estado = 0;
				ChangeCO2ClassDialog.this.setVisible(false);
			}
		});
		options.add(cancel);

		ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if ((vehicleModel.getSelectedItem() != null) && (co2Model.getSelectedItem() != null))
				{
					estado = 1;
					ChangeCO2ClassDialog.this.setVisible(false);
				}
			}
		});
		options.add(ok);

		setPreferredSize(new Dimension(500, 200));
		pack();
		setResizable(false);
		setVisible(false);
	}
	
	public int open(RoadMap mapa) {
		
		for (Vehicle v : mapa.getVehicles())
		{
			vehicleModel.addElement(v);
		}
		for (int i = 0; i < 11; i++)
		{
			co2Model.addElement(i);
		}
		
		setLocation(getParent().getLocation().x + 10, getParent().getLocation().y + 10);
		setVisible(true);
		
		return estado;
	}

	public Integer getTicks() {
		
		return (Integer) ticks.getValue();
	}

	public Integer getCO2Class() {
		
		return (Integer) co2Model.getSelectedItem();
	}
	
	public Vehicle getVehicle() {
		
		return (Vehicle) vehicleModel.getSelectedItem();
	}

}/*
package simulator.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import simulator.misc.Pair;
import simulator.model.Vehicle;


public class ChangeCO2ClassDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private boolean status;
	
	private JComboBox<String> boxVehicles;
	private JComboBox<Integer> boxCO2;
	private JSpinner ticksSpinner;
	private JButton cancelButton;
	private JButton okButton;

	ChangeCO2ClassDialog(Frame frame) {
		super(frame, true);
		initGui();
	}
	
	private void initGui()  {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		add(mainPanel);
		
		//Description;
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 3));
		mainPanel.add(topPanel);
		
		JLabel descLabel = new JLabel("Schedule an event to change the CO2 class of a vehicle after a given number o simulation ticks from now.");
		topPanel.add(descLabel);
		
		// Vehicles
		JPanel centerPanel = new JPanel();
		mainPanel.add(centerPanel);
		
		JLabel vehiclesLabel = new JLabel("Vehicle:");
		centerPanel.add(vehiclesLabel);
		
		boxVehicles = new JComboBox<String>();
		boxVehicles.setPreferredSize(new Dimension(50, 25));
		centerPanel.add(boxVehicles);
		
		// CO2 
		JLabel co2Label = new JLabel("CO2 Class:");
		centerPanel.add(co2Label);
		
		boxCO2 = new JComboBox<Integer>();
		boxCO2.setPreferredSize(new Dimension(50, 25));
		for (int i = 0; i < 10; i++)
			boxCO2.addItem(i);
		centerPanel.add(boxCO2);
		
		// Ticks
		JLabel ticksLabel = new JLabel("Ticks:");
		centerPanel.add(ticksLabel);
		ticksSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 1000, 1));
		centerPanel.add(ticksSpinner);
		
		
		//Cancel and Ok Buttons
		JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 2, 3));
		mainPanel.add(bottomPanel);
		
		cancelButton = new JButton("Cancel");
		bottomPanel.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				status = false;
				setVisible(false);
			}

		});
		
		okButton = new JButton("Ok");
		bottomPanel.add(okButton);
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (boxVehicles.getSelectedItem() != null && boxCO2.getSelectedItem() != null)
					status = true;
				setVisible(false);
			}
		});
		
		pack();
		setName("Change CO2 Class");
		setLocationRelativeTo(null);
	}
	
	boolean open(List<Vehicle> vehicles) {
		
		boxVehicles.removeAllItems();
		for (Vehicle v : vehicles)
			boxVehicles.addItem(v.toString());
		
		setVisible(true);
		return status;
	}
	
	List<Pair<String, Integer>> getNewCO2Vehicle() {
		List<Pair<String, Integer>> data = new ArrayList<>();
		String v = (String) boxVehicles.getSelectedItem().toString();
		Integer co2 = (Integer) boxCO2.getSelectedItem();
		data.add(new Pair<String, Integer>(v, co2));
		return data;
	}
	
	int getTime() { return (int) ticksSpinner.getValue(); }
	
}*/