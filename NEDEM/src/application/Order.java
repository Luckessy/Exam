package application;

import javafx.scene.chart.PieChart.Data;

public class Order {
private int Order_Num;
private String Order_Date;
private String Oborud_treb_rem;
private String Type_neisp;
private String Opis_problem;
private int Klient;
private String Status;

public int getOrder_Num() {
	return Order_Num;
}
public void setOrder_Num(int order_Num) {
	Order_Num = order_Num;
}
public String getOrder_Date() {
	return Order_Date;
}
public void setOrder_Date(String order_Date) {
	Order_Date = order_Date;
}
public String getOborud_treb_rem() {
	return Oborud_treb_rem;
}
public void setOborud_treb_rem(String oborud_treb_rem) {
	Oborud_treb_rem = oborud_treb_rem;
}
public String getType_neisp() {
	return Type_neisp;
}
public void setType_neisp(String type_neisp) {
	Type_neisp = type_neisp;
}
public String getOpis_problem() {
	return Opis_problem;
}
public void setOpis_problem(String opis_problem) {
	Opis_problem = opis_problem;
}
public int getKlient() {
	return Klient;
}
public void setKlient(int klient) {
	Klient = klient;
}
public String getStatus() {
	return Status;
}
public void setStatus(String status) {
	Status = status;
}
public Order(int order_Num, String order_Date, String oborud_treb_rem, String type_neisp, String opis_problem,
		int klient, String status) {
	super();
	Order_Num = order_Num;
	Order_Date = order_Date;
	Oborud_treb_rem = oborud_treb_rem;
	Type_neisp = type_neisp;
	Opis_problem = opis_problem;
	Klient = klient;
	Status = status;
}
public Order() {
	super();
}
@Override
public String toString() {
	return Order_Num + ", " + Order_Date + ", " + Oborud_treb_rem
			+ ", " + Type_neisp + ", " + Opis_problem + ", " + Klient + ", "
			+ Status;
}


}
