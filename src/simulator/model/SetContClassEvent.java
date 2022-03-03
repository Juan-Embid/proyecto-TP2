package simulator.model;
import java.util.List;
import simulator.misc.Pair;

public class SetContClassEvent extends Event {
	
	private List<Pair<String, Integer>> cs;
	
	public SetContClassEvent(int time, List<Pair<String, Integer>> cs) {
		super(time);
		if (cs.isEmpty())
			throw new IllegalArgumentException("Error: list is empty");
		this.cs = cs;
	}
	
	@Override
	void execute(RoadMap map) {
		for (Pair<String, Integer> c : cs) {
			if(map.getRoad(c.getFirst()) == null)
				throw new IllegalArgumentException("Road doesn't exist in the RoadMap");
			map.getRoad(c.getFirst()).addContamination(c.getSecond());
		}		
	}
}