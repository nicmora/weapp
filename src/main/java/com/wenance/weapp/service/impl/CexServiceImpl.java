package com.wenance.weapp.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wenance.weapp.service.CexService;

import reactor.core.publisher.Mono;

@Service
public class CexServiceImpl implements CexService {
	
	@Autowired
	private WebClient webClient;

	@Override
	public Mono<String> fetchPrice() {
		return webClient.get()
				.uri("/last_price/BTC/USD")
				.retrieve()
				.bodyToMono(String.class)
				.log("--- cex.io ---");
	}

}
