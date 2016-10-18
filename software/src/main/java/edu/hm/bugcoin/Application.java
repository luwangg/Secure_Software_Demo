package edu.hm.bugcoin;

import edu.hm.bugcoin.task.TaskWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application
{

	// ----------------------------------------------------------------------------------
	//  object variabls
	// ----------------------------------------------------------------------------------


	// ----------------------------------------------------------------------------------
	//  application entry
	// ----------------------------------------------------------------------------------

	public static void main(String[] args) throws Exception
	{
		SpringApplication.run(Application.class, args);
	}

}
