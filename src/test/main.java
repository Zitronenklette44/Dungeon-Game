package test;

public class main {

	public static void main(String[] args) {
		Person stephanPerson = new Stephan();
		Person justinPerson = new Justin();
		
		System.out.println("Name: "+ stephanPerson.name);
		System.out.println("Alter: "+ stephanPerson.alter);
		System.out.println("Ist DUmm: "+ stephanPerson.isDumm);
		System.out.println("------------------------------------------");
		stephanPerson.gehLernen();
		System.out.println("Name: "+ stephanPerson.name);
		System.out.println("Alter: "+ stephanPerson.alter);
		System.out.println("Ist DUmm: "+ stephanPerson.isDumm);
		System.out.println("------------------------------------------");
		
		System.out.println("Name: "+ justinPerson.name);
		System.out.println("Alter: "+ justinPerson.alter);
		System.out.println("Ist DUmm: "+ justinPerson.isDumm);
		System.out.println("------------------------------------------");
		justinPerson.unterhalteDichMitJackob();
		
		System.out.println("Name: "+ justinPerson.name);
		System.out.println("Alter: "+ justinPerson.alter);
		System.out.println("Ist DUmm: "+ justinPerson.isDumm);
		System.out.println("------------------------------------------");

	}

}
