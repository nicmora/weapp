package com.wenance.weapp.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wenance.weapp.exception.CoinException;
import com.wenance.weapp.service.CexService;

import reactor.core.publisher.Mono;

@Service
public class CexServiceImpl implements CexService {
	
	private static final Logger logger = LoggerFactory.getLogger(CexService.class);

	@Autowired
	private WebClient webClient;

	@Override
	public Mono<String> updatePrice() {
		return webClient.get()
				.uri("/last_price/BTC/USD")
				.retrieve()
                .onStatus(HttpStatus::is4xxClientError, clientResponse -> {
                    logger.error("Error endpoint with status code {}",  clientResponse.statusCode());
                    throw new CoinException("HTTP Status 400 error");
                })
                .onStatus(HttpStatus::is5xxServerError, clientResponse -> {
                    logger.error("Error endpoint with status code {}",  clientResponse.statusCode());
                    throw new CoinException("HTTP Status 500 error");
                })
				.bodyToMono(String.class);
	}

}
