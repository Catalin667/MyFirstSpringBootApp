package myFirstApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.transaction.SystemException;
import javax.transaction.Transaction;
import javax.websocket.Session;

@SpringBootApplication
public class MyAppApplication {
	public static void main(String[] args){
		SpringApplication.run(MyAppApplication.class, args);
	}
}
