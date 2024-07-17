package test;

public class Person {
	String name;
	int alter;
	boolean isDumm;
	
	public Person(String name, int alter, boolean isDumm) {
		this.name = name;
		this.alter = alter;
		this.isDumm = isDumm;
	}
	
	public void gehLernen() {
		isDumm = false;
	}
	
	public void unterhalteDichMitJackob() {
		isDumm = true;
	}

}
