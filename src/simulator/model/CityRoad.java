package simulator.model;

public class CityRoad extends Road{

	CityRoad(String id, Junction srcJunc, Junction destJunc, int maxSpeed, int contLimit, int length, Weather weather) {
		super(id, srcJunc, destJunc, maxSpeed, contLimit, length, weather);
	}

	@Override
	void reduceTotalContamination() {
		if(enviCondition == Weather.STORM || enviCondition == Weather.WINDY)
			totalPollution -= 10;
		else
			totalPollution -=2;
		if(totalPollution < 0)
			totalPollution = 0;
	}

	@Override
	void updateSpeedLimit() {
		speedLimit = maxVelocity;
	}

	@Override
	int calculateVehicleSpeed(Vehicle v) {
		int temp = ((11 - v.getContClass()) * speedLimit) / 11;
		v.setSpeed(temp);
		return 0;
	}

}
