package com.kapumota.microservicios.composite.product.productcompositeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages="com.kapumota")
public class ProductCompositeServiceApplication {

	/*@Bean
	RestTemplate restTemplate(){
		return new RestTemplate();
	}*/
	public static void main(String[] args) {
		SpringApplication.run(ProductCompositeServiceApplication.class, args);
	}

}
