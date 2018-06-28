package br.com.rmacario.springbootsslauthenticationclient.configuration;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

	@Bean
	public RestTemplate generate() {
		return new RestTemplate();
	}
	
	@PostConstruct
	public void initSsl(){
		System.setProperty("javax.net.ssl.keyStore", getClass().getClassLoader().getResource("client-keystore.jks").getPath());
		System.setProperty("javax.net.ssl.keyStorePassword", "secret");
		System.setProperty("javax.net.ssl.trustStore", getClass().getClassLoader().getResource("client-truststore.jks").getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", "secret");
	}
}
