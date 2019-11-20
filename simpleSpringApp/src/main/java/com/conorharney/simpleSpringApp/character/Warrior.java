package com.conorharney.simpleSpringApp.character;

import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Primary
@Scope("prototype")
public class Warrior extends Person {

	
	public Warrior(String name) {
		super(4, 7, name);
		purchasePrice = 4.50f;
	}

	public String move() {
		return "Warrior " + name + " " + super.move();
	}

	public String attack() {
		super.Specialty();
		return "Warrior attacked";
	}

	public String speak() {
		
		return "Warrior " + name + " " + super.speak();
	}

	public String look() {
		return "Warrior " + name + " " + super.look();
	}
	
	public String levelUp()
	{
		strength += (exp / 4);
		return super.levelUp();
	}

}
