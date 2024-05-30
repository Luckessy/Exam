package application;

public class film {
	private int id;
	private String operator;
	private String rejiser;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getOperator() {
		return operator;
	}



	public void setOperator(String operator) {
		this.operator = operator;
	}



	public String getRejiser() {
		return rejiser;
	}



	public void setRejiser(String rejiser) {
		this.rejiser = rejiser;
	}



	public film(int id, String operator, String rejiser) {
		super();
		this.id = id;
		this.operator = operator;
		this.rejiser = rejiser;
	}



	public film() {
		super();
	}


}
