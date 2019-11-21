package com.conorharney.simpleSpringApp.account;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import com.conorharney.simpleSpringApp.character.Person;

@Component
@Scope("singleton")
public class AccountManager {
	static int nextId = 0;
	static Map<String, Account> accountNames = new  HashMap<String, Account>();
	
	public static Account createAccount(String accountName, String accountPassword, ApplicationContext context)
	{
		Account account = null;
		if(!accountNames.containsKey(accountName))
		{
			account = context.getBean(Account.class, nextId, accountName, accountPassword);
			accountNames.put(accountName, account);
			nextId++;
			System.out.println("Account created");
		}
		else
			System.out.println("Account already exists");
		
		return account;
	}
	
	public static Person buyCharacter(String accountName, String accountPassword, Person characterToPurchase)
	{
		if(verifyAccount(accountName, accountPassword))
		{
			return accountNames.get(accountName).buyCharacter(characterToPurchase, accountPassword);
		}
		else
			return null;
	}
	
	public static boolean verifyAccount(String name, String password)
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
	
	public static List<Person> listAccountsCharacters(String name, String password)
	{
		if(verifyAccount(name,password))
		{
			return accountNames.get(name).listCharacters();
		}
		else return null;
	}
	
	public static void listAccounts()
	{
		for (String currentKey : accountNames.keySet())
			System.out.println(accountNames.get(currentKey).name);
	}
	
	public double topUpAccount(String name, String password, double amount)
	{
		if(verifyAccount(name, password))
		{
			accountNames.get(name).addCurrency(amount);
			return accountNames.get(name).getCurrency();
		}
		else return 0;
	}
}
