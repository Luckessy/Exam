package application;

public class User {
 private String login;
 private String pass;

public String getLogin() {
	return login;
}
public void setLogin(String login) {
	this.login = login;
}
public String getPass() {
	return pass;
}
public void setPass(String pass) {
	this.pass = pass;
}
public User(String login, String pass) {
	super();
	this.login = login;
	this.pass = pass;
}
public User() {
	super();
}

}
