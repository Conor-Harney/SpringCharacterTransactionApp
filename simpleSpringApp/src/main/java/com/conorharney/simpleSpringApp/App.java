package com.conorharney.simpleSpringApp;

import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conorharney.simpleSpringApp.account.AccountManager;
import com.conorharney.simpleSpringApp.character.Person;
import com.conorharney.simpleSpringApp.character.Warrior;
import com.conorharney.simpleSpringApp.character.Wizard;
import com.conorharney.simpleSpringApp.configs.AppConfig;
import com.conorharney.simpleSpringApp.readers.FileReader;


public class App 
{
	static Scanner m_scanner = new Scanner(System.in);
	static ApplicationContext context;
	
    public static void main( String[] args )
    {
    	String command;
    	boolean running = true;
    	context = new AnnotationConfigApplicationContext(AppConfig.class);
    	System.out.println("running");
    	AccountManager accountManager = (AccountManager)context.getBean(AccountManager.class);
    	help();
    	while(running)
    	{
    		command = m_scanner.nextLine().replaceAll("\\s", "");	    
		    if(command.equalsIgnoreCase("exit"))
		    {
		    	running = false;
		    	break;
	    	}
		    else if(command.equalsIgnoreCase("CreateAccount"))
		    {
		    	createAccount();
		    }
		    else if(command.equalsIgnoreCase("listCharacters"))
		    {
		    	listCharacters();
		    }
		    else if(command.equalsIgnoreCase("Buy"))
		    {
		    	buy();
		    }
		    else if(command.equalsIgnoreCase("topup"))
		    {
		    	topUp();
		    }
		    else if(command.equalsIgnoreCase("help"))
		    {
		    	help();
		    }
		    else
		    {
		    	help();
		    	System.out.println(FileReader.getInstance().getFirstLine("txt/", "commandNotRecognised.txt"));
		    }
    	}
    	
    }
    
    public static void help()
    {
    	for (String line : FileReader.getInstance().getAllLines("txt/", "options.txt"))
    	{
    		System.out.println(line);
    	}
    }
    
    public static void createAccount()
    {
    	List<String> lines = FileReader.getInstance().getAllLines("txt/", "createAccount.txt");
    	System.out.println(lines.get(0));
    	String accountName = m_scanner.nextLine().replaceAll("\\s", "");	
    	System.out.println(lines.get(1));
    	String accountPassword = m_scanner.nextLine().replaceAll("\\s", "");
    	context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context);
    }
    
    public static void listCharacters()
    {
    	List<String> lines = FileReader.getInstance().getAllLines("txt/", "listCharacters.txt");
    	System.out.println(lines.get(0));
    	String accountName = m_scanner.nextLine().replaceAll("\\s", "");	
    	System.out.println(lines.get(1));
    	String accountPassword = m_scanner.nextLine().replaceAll("\\s", "");
    	context.getBean(AccountManager.class).listAccountsCharacters(accountName, accountPassword);
    }
    
    public static void topUp()
    {
    	List<String> lines = FileReader.getInstance().getAllLines("txt/", "topUp.txt");
    	System.out.println(lines.get(0));
    	String name = m_scanner.nextLine().replaceAll("\\s", "");	
    	System.out.println(lines.get(1));
    	String password = m_scanner.nextLine().replaceAll("\\s", "");
    	System.out.println(lines.get(2));
    	String amount = m_scanner.nextLine().replaceAll("\\s", "");
    	if(amount.matches("^(\\d)+(\\d\\.)*$"))
    	{
    		context.getBean(AccountManager.class).topUpAccount(name, password, Double.parseDouble(amount));
    		System.out.println(lines.get(3));
    	}
    	else
    	{
    		System.out.println("Invalid amount");
    	}
    	
    }
    
    public static void buy()
    {
    	List<String> lines = FileReader.getInstance().getAllLines("txt/", "buyCharacter.txt");
    	System.out.println(lines.get(0));
    	String accountName = m_scanner.nextLine().replaceAll("\\s", "");	
    	System.out.println(lines.get(1));
    	String accountPassword = m_scanner.nextLine().replaceAll("\\s", "");
    	System.out.println(lines.get(2));
    	String characterName = m_scanner.nextLine().replaceAll("\\s", "");
    	
    	System.out.println(lines.get(3));
    	String characterType = m_scanner.nextLine().replaceAll("\\s", "");
    	if(characterType.equalsIgnoreCase("wizard")) 
    		context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Wizard.class, characterName) );
    	else if(characterType.equalsIgnoreCase("warrior"))
    		context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Warrior.class, characterName) );
    	else System.out.println("Invalid type");
    }
}
