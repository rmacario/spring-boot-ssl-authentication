package br.com.rmacario.springbootsslauthenticationclient.configuration;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
	
	@Value("#{environment['cert.password']}")
	private String certificadoPassword;
	
	@Value("#{environment['cert.name.keystore']}")
	private String keyStoreCertName;
	
	@Value("#{environment['cert.name.truststore']}")
	private String trustStoreCertName;
	

	@Bean
	public RestTemplate generate() {
		return new RestTemplate();
	}
	
	@PostConstruct
	public void initSsl(){
		System.setProperty("javax.net.ssl.keyStore", getClass().getClassLoader().getResource(this.keyStoreCertName).getPath());
		System.setProperty("javax.net.ssl.keyStorePassword", this.certificadoPassword);
		System.setProperty("javax.net.ssl.trustStore", getClass().getClassLoader().getResource(this.trustStoreCertName).getPath());
		System.setProperty("javax.net.ssl.trustStorePassword", this.certificadoPassword);
	}
}
