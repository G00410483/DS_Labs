package ie.atu.sw;

import java.time.LocalDate;

public class Ninja implements Cloneable{
	private long id;
	private String name;
	private LocalDate dob;
	private int level;
	
	//Generate bean method here from Source menu

	
	public Ninja(long id, String name, LocalDate dob, int level) {
		super();
		this.id = id;
		this.name = name;
		this.dob = dob;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	
	
	
}
