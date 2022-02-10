package simulator.model;

public class InterCityRoad extends Road{

	InterCityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length,
			Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	void reduceTotalContamination() {
		//((100 - x) * tc) / 100
	}

	@Override
	void updateSpeedLimit() {
		
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		return 0;
	}

}
