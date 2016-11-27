package edu.hm.bugcoin;

import edu.hm.bugcoin.task.TaskWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

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
