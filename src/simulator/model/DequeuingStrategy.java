package simulator.model;
import java.util.List;

public interface DequeuingStrategy {
	List<Vehicle> dequeu(List<Vehicle> q);
}
