package simulator.model;

public class NewJunctionEvent extends Event {
	
	private int xCoor, yCoor;
	private String id;
	private LightSwitchStrategy lsStrategy;
	private DequeuingStrategy dqStrategy;

	public NewJunctionEvent(int time, String id, LightSwitchStrategy lsStrategy, DequeuingStrategy dqStrategy, int xCoor, int yCoor) {
			super(time);
			this.xCoor = xCoor;
			this.yCoor = yCoor;
			this.id = id;
			this.lsStrategy = lsStrategy;
			this.dqStrategy = dqStrategy;
	}

	@Override
	void execute(RoadMap map) {
		Junction newJunction = new Junction(id, lsStrategy, dqStrategy, xCoor, yCoor);
		map.addJunction(newJunction);
	}
}