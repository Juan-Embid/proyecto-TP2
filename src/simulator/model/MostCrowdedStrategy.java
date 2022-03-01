package simulator.model;

import java.util.List;

public class MostCrowdedStrategy implements LightSwitchStrategy{
	private int timeslot;
	
	public MostCrowdedStrategy(int timeslot) {
		this.timeslot = timeslot;
	}
	@Override
	public int chooseNextGreen(List<Road> roads, List<List<Vehicle>> qs, int currGreen, int lastSwitchingTime,
			int currTime) {
		if(roads.isEmpty())
			return -1;
		else if(currGreen == -1) {
			return nextGreen(qs, 0);
		}
		else if(lastSwitchingTime < timeslot)
			return currGreen;
		return nextGreen(qs, currGreen +1);
	}
	private int nextGreen(List<List<Vehicle>> qs, int beginning ) {
		int max = 0, index = 0, beg = 0;
		boolean finished = false, startagain = false;
		
		if(beginning != 0)
			beg = beginning % qs.size();
			
		//no empieza desde el principio
		for(int i = beg; i < qs.size() && !finished; i++){
			//cuando ya a vuelto a empezar tiene que revisar hasta que llega al anterior del que empezó en un inicio
			if(startagain && i == beginning % qs.size() - 1)
			finished = true;
			
			//va cogiendo la cola más larga
			if(qs.get(i).size() > max) {
				max = qs.get(i).size();
				index = i;
			}
			
			//cuando llega a la última cola de coches empieza por el principio de esta
			if(i == qs.size() - 1 && beg != 0) {
				i = 0;
				startagain = true;
			}
		}
		return index;
	}

}
