public class Element{ //The element class
	public double atomicmass;//defines our each of our element properties
	public String elname;
	public String abbr;
	public int line;

	public Element(){
		this(1, "Hydrogen", "t", 1);
	}
	public Element(double atomicmass, String elname, String abbr, int line){//referencing our currently running object for each of our properties
		this.elname = elname;
		this.atomicmass = atomicmass;
		this.abbr = abbr;
		this.line = line;
	}
	
}
