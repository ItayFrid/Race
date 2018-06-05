package game.racers;

import java.util.Hashtable;

public abstract class IRacer {
	private Hashtable<String,Object> attributes;
	
	public IRacer() {
		attributes = new Hashtable<String,Object>();
	}
	
	public abstract void addAttribute(String string,Object object);
	
	public Hashtable<String,Object> getAttributes(){
		return attributes;
	}
}
