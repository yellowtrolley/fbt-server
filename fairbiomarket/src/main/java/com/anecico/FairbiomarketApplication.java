package com.anecico;

import io.katharsis.spring.boot.KatharsisConfigV2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(KatharsisConfigV2.class)
public class FairbiomarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(FairbiomarketApplication.class, args);
	}
}
