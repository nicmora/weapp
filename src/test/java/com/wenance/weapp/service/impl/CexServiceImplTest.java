package com.wenance.weapp.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.reactive.server.WebTestClient;

@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CexServiceImplTest {
	
	@Autowired
	WebTestClient webTestClient;
	
	@Value("${cex-url}")
	String BASE_URL;

	@Test
	void testFetchPrice() {
		this.webTestClient.get()
			.uri(BASE_URL + "/last_price/BTC/USD")
			.accept(MediaType.ALL)
			.exchange()
            .expectStatus().is2xxSuccessful()
            .expectHeader().valueEquals("Content-Type", "text/json")
            .expectBody()
            .jsonPath("$.lprice").exists()
            .jsonPath("$.curr1").isEqualTo("BTC")
            .jsonPath("$.curr2").isEqualTo("USD");
	}

}
