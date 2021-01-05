package com.wenance.weapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@Service
public class CexServiceImpl implements CexService {

	@Autowired
	private WebClient webClient;

	@Override
	public Mono<String> fetch() {
		
		return webClient.get()
				.uri("/last_price/BTC/USD")
				.retrieve()
				.bodyToMono(String.class);
		
	}

}
