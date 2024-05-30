package application;

public class Rabotnik {

	private int id;
	private String FIO;
	private String ZP;
	public Rabotnik(int id, String FIO, String ZP) {
		super();
		this.id = id;
		this.FIO = FIO;
		this.ZP = ZP;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFIO() {
		return FIO;
	}
	public void setFIO(String fIO) {
		FIO = fIO;
	}
	public String getZP() {
		return ZP;
	}
	public void setZP(String zP) {
		ZP = zP;
	}
	public Rabotnik() {
		super();
	}
	
}
