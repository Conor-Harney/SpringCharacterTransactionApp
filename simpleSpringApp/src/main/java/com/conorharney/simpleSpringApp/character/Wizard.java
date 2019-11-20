package com.conorharney.simpleSpringApp.character;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class Wizard extends Person {
	
	public Wizard(String name) {
		super(7, 4, name);
		purchasePrice = 5.00f;
	}

	public String move() {
		return "Wizard " + name + " " + super.move();
	}

	public String speak() {
		
		return "Wizard " + super.speak();
	}

	public String look() {
		return "Wizard " + super.look();
	}
	
	public String cast()
	{
		super.Specialty();
		return "Wizard casted";
	}
	
	public String levelUp()
	{
		intelligence += (exp / 4);
		return super.levelUp();
	}

}
