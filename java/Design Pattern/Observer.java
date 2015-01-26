public interface Observer{
	void update(Object objs);
}
public abstract class Observable{

	public final ArrayList<Class<?>>abserList = new ArrayList<Class<?>>();

	
