package com.conorharney.simpleSpringApp.character;

public class Person {
	
	double intelligence;
	double strength;
	String name;
	double exp;
	int level;
	float purchasePrice;
	
	public Person(double intelligence, double strength, String name)
	{
		this.intelligence = intelligence;
		this.strength = strength;
		this.name = name;
		this.exp = 1;
		this.level = 1;
		purchasePrice = 3.50f;
	}
	
	public String move() 
	{
		return "moved";
	}
	
	public String speak()
	{
		return "spoke";
	}
	
	public String look() 
	{
		return "looked";
	}
	
	public String levelUp()
	{
		intelligence += (exp / 2);
		strength += (exp / 2);
		return "leveled up";
	}
	
	public void Specialty()
	{
		exp++;
	}
	
	public double getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(double intelligence) {
		this.intelligence = intelligence;
	}

	public double getStrength() {
		return strength;
	}

	public void setStrength(double strength) {
		this.strength = strength;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getExp() {
		return exp;
	}

	public void setExp(double exp) {
		this.exp = exp;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public float getPrice()
	{
		return this.purchasePrice;
	}
	
}
