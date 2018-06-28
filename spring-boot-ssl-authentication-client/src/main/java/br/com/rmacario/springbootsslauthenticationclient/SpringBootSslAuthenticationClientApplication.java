package br.com.rmacario.springbootsslauthenticationclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class SpringBootSslAuthenticationClientApplication {
	
	@Value("#{environment['adress.server']}")
	private String serverAdress;
	
	@Autowired
	private RestTemplate restTemplate;
	

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSslAuthenticationClientApplication.class, args);
	}
	
	
	@GetMapping("/")
	public String getLastDayCurrentMonth() {
		ResponseEntity<String> response = restTemplate.getForEntity(this.serverAdress, String.class);
		return response.getBody();
	}
}
