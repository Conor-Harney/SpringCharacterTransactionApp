package com.conorharney.simpleSpringApp;

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.conorharney.simpleSpringApp.account.AccountManager;
import com.conorharney.simpleSpringApp.character.Person;
import com.conorharney.simpleSpringApp.character.Warrior;
import com.conorharney.simpleSpringApp.character.Wizard;
import com.conorharney.simpleSpringApp.configs.AppConfig;

public class AppTest 
{
	//happy path test
	//add an account
	//top up the account
	//purchase 2 characters 
	//list characters
	static ApplicationContext context;
	String accountName,	accountPassword, char1Name, char2Name;
	double  acccountTopUpAmmount;
	@BeforeClass
    public static void BeforeClass() {
		context = new AnnotationConfigApplicationContext(AppConfig.class);
    }

    @Before
    public void before() {
        System.out.println("Before");
    	acccountTopUpAmmount = 20;
    }
    
    @Test
    public void test() {
    	
    }
    
    @Test
    public void testCreateAccount() {
    	accountName = "tcaAcc1";
    	accountPassword = "tcapass1";
    	acccountTopUpAmmount = 20;
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    }
    @Test
    public void testBadAccountName() {
    	accountName = "tbanAcc1";
    	accountPassword = "tbanpass1";
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    }
    @Test
    public void testBadAccountPass() {
    	accountName = "tbapAcc1";
    	accountPassword = "tbappass1";
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount(accountName, "badPass"));
    }
    
    @Test
    public void testTopup() {
    	accountName = "ttpAcc1";
    	accountPassword = "ttppass1";
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount(accountName, "badPass"));
    	assertTrue(context.getBean(AccountManager.class).topUpAccount(accountName, accountPassword, acccountTopUpAmmount) == acccountTopUpAmmount);
    }
    
    @Test
    public void testBuyingCharacter() {
    	accountName = "tbcAcc1";
    	accountPassword = "tbcpass1";
    	char1Name = "tbcchar1"; 
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount(accountName, "badPass"));
    	assertTrue(context.getBean(AccountManager.class).topUpAccount(accountName, accountPassword, acccountTopUpAmmount) >= acccountTopUpAmmount);
    	assertNotNull(context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Wizard.class, char1Name) ));
    }
    @Test
    public void testBuyingMultiplecharacters() {
    	accountName = "tbmcAcc1";
    	accountPassword = "tbmcpass1";
    	char1Name = "tbmcchar1"; 
    	char2Name = "tbmcchar2"; 
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount(accountName, "badPass"));
    	assertTrue(context.getBean(AccountManager.class).topUpAccount(accountName, accountPassword, acccountTopUpAmmount) >= acccountTopUpAmmount);
    	assertNotNull(context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Wizard.class, char1Name) ));
    	assertNotNull(context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Warrior.class, char2Name) ));
    }
    
    @Test
    public void testListingCharacters() {
    	accountName = "tlcAcc1";
    	accountPassword = "tlcpass1";
    	char1Name = "ttlchar1"; 
    	char2Name = "tlcchar2"; 
    	assertNotNull(context.getBean(AccountManager.class).createAccount(accountName, accountPassword, context));
    	assertTrue(context.getBean(AccountManager.class).verifyAccount(accountName, accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount("badname", accountPassword));
    	assertFalse(context.getBean(AccountManager.class).verifyAccount(accountName, "badPass"));
    	assertTrue(context.getBean(AccountManager.class).topUpAccount(accountName, accountPassword, acccountTopUpAmmount) >= acccountTopUpAmmount);
    	assertNotNull(context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Wizard.class, char1Name) ));
    	assertNotNull(context.getBean(AccountManager.class).buyCharacter(accountName, accountPassword, (Person)context.getBean(Warrior.class, char2Name) ));
    	assertNotNull(context.getBean(AccountManager.class).listAccountsCharacters(accountName, accountPassword));
    }

    @After
    public void after() {
        System.out.println("After");
    }

    @AfterClass
    public static void AfterTest() {
        System.out.println("AfterClass");
    }
}
