package org.ko.namingofzk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class Server3Application {

	public static void main(String[] args) {
		SpringApplication.run(Server3Application.class, args);
	}


	@GetMapping("serve")
	public String get() {
		return "server3";
	}

}
