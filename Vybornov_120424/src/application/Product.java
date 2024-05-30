package application;

public class Product {
	private int id;
	private String Product_Name;
	private float Cost;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProduct_Name() {
		return Product_Name;
	}

	public void setOperator(String Product_Name) {
		this.Product_Name = Product_Name;
	}

	public float getCost() {
		return Cost;
	}

	public void setCost(float Cost) {
		this.Cost = Cost;
	}

	public Product(int id, String Product_Name, float Cost) {
		super();
		this.id = id;
		this.Product_Name = Product_Name;
		this.Cost = Cost;
	}

	public Product() {
		super();
	}
}
