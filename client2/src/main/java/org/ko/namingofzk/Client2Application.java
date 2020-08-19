package org.ko.namingofzk;

import org.ko.namingofzk.sdk.client.properties.ZkProperties;
import org.ko.namingofzk.sdk.client.zk.ServerAddressHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableConfigurationProperties(ZkProperties.class)
@RestController
public class Client2Application {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired private ServerAddressHolder serverAddressHolder;

	public static void main(String[] args) {
		SpringApplication.run(Client2Application.class, args);
	}

	@GetMapping("hello")
	public String hello() {
		return restTemplate.getForObject(serverAddressHolder.getUrl(), String.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}

}
