package com.conorharney.simpleSpringApp.account;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.conorharney.simpleSpringApp.character.Person;

@Component
@Scope("prototype")
public class Account {
	int id;
	String name;
	float currency;
	String password;
	List<Person> playerCharacters;
	
	public Account(int id, String name, String password)
	{
		this.id = id;
		this.name = name;
		this.currency = 0.0f;
		this.password = password;
		playerCharacters = new ArrayList();
	}
	
	public boolean checkPassword(String password)
	{
		return this.password.equals(password);
	}
	
	public boolean buyCharacter(Person character, String password)
	{
		boolean itemPurchased = false;
		if(!checkPassword(password))
		{
			System.out.println("Invalid details");
		}
		if(character.getPrice() > currency)
		{
			System.out.println("Not enough currency");
		}
		else
		{
			playerCharacters.add(character);
			this.currency -= character.getPrice();
			itemPurchased = true;
			System.out.println(character.getClass().getSimpleName() + " " + character.getName() + " has been added to your account!");
		}
		return itemPurchased;
	}
	
	public void listCharacters()
	{
		System.out.println("Characters:");
		for(Person person: playerCharacters)
		{
			System.out.println(person.getClass().getSimpleName() + " - " + person.getName());
		}
	}
	
	public void addCurrency(double currency)
	{
		this.currency += currency;
	}
	
	public double getCurrency()
	{
		return this.currency;
	}
}
