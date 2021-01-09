package com.wenance.weapp.service;

import reactor.core.publisher.Mono;

public interface CexService {
	
	/**
	 * Método que realiza un fetch mediante WebClient a la api externa: 
	 * {@link https://cex.io/api/last_price/BTC/USD}
	 * @return String con la actualización del precio de BTC/USD
	 */
	Mono<String> fetchPrice();

}
