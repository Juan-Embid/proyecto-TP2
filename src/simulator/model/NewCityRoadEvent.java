package simulator.model;

public class NewCityRoadEvent extends NewRoadEvent{

	public NewCityRoadEvent(int time, String id, String srcJun, String destJunc, int length, int co2Limit, int maxSpeed, Weather weather) {
		super(time, destJunc, destJunc, destJunc, maxSpeed, maxSpeed, maxSpeed, weather);		
	}

	@Override
	protected Road cityRoad() {
		return new CityRoad(id, junSrc, junDest, maxSpeed, co2Limit, length, weather);
	}
}