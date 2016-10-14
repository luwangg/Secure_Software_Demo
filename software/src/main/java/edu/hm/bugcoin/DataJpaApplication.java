package edu.hm.bugcoin;

import edu.hm.bugcoin.web.identity.DummyIdentityProvider;
import edu.hm.bugcoin.web.identity.IdentityProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataJpaApplication {

	// ----------------------------------------------------------------------------------
	//  global variables
	// ----------------------------------------------------------------------------------

	public static IdentityProvider identityProvider;


	// ----------------------------------------------------------------------------------
	//  application entry
	// ----------------------------------------------------------------------------------


	public static void main(String[] args) throws Exception {

		identityProvider = new DummyIdentityProvider();

		SpringApplication.run(DataJpaApplication.class, args);
	}

}
