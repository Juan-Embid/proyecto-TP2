package simulator.model;

import java.util.ArrayList;
import java.util.List;

public class MoveFirstStrategy implements DequeuingStrategy{

	@Override
	public List<Vehicle> dequeu(List<Vehicle> q) {
		List<Vehicle> g = new ArrayList<>();
		g.add(q.get(0));
		return g;
	}

}
