package com.conorharney.simpleSpringApp.account;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.conorharney.simpleSpringApp.character.Person;

@Component
@Scope("singleton")
public class AccountManager {
	static int nextId = 0;
	static Map<String, Account> accountNames = new  HashMap<String, Account>();
	
	public static void createAccount(String accountName, String accountPassword)
	{
		if(!accountNames.containsKey(accountName))
		{
			accountNames.put(accountName, new Account(nextId, accountName, accountPassword));
			nextId++;
			System.out.println("Account created");
		}
		else
			System.out.println("Account already exists");
	}
	
	public static void buyCharacter(String accountName, String accountPassword, Person characterToPurchase)
	{
		if(verifyAccount(accountName, accountPassword))
		{
			accountNames.get(accountName).buyCharacter(characterToPurchase, accountPassword);
		}
	}
	
	private static boolean verifyAccount(String name, String password)
	{
		boolean verified = false;
		if(!accountNames.containsKey(name))
		{
			System.out.println("Invallid account");
		}
		else if (!accountNames.get(name).checkPassword(password))
		{
			System.out.println("Incorrect password for this accont");
		}
		else
		{
			verified = true;
		}
		return verified;
	}
	
	public static void listAccountsCharacters(String name, String password)
	{
		if(verifyAccount(name,password))
		{
			accountNames.get(name).listCharacters();
		}
	}
	
	public static void listAccounts()
	{
		for (String currentKey : accountNames.keySet())
			System.out.println(accountNames.get(currentKey).name);
	}
	
	public void topUpAccount(String name, String password, double amount)
	{
		if(verifyAccount(name, password))
		{
			accountNames.get(name).addCurrency(amount);
		}
	}
}
