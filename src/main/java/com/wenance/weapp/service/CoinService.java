package com.wenance.weapp.service;

import java.time.LocalDateTime;

import com.wenance.weapp.dto.AvgChgDTO;
import com.wenance.weapp.entity.Coin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinService {

	Flux<Coin> findAll();
	
	Mono<Coin> findByTimestamp(LocalDateTime timestamp);

	/**
	 * Realiza una búsqueda mediante el campo "timestamp" utilizando el operador between
	 * @param startTimestamp
	 * @param endTimestamp
	 * @return Flujo de objetos Coin
	 */
	Flux<Coin> findCoinsBetweenTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp);

	/**
	 * Realiza una búsqueda mediante el campo "timestamp" utilizando el operador between.
	 * Recupera el valor máximo del campo "lprice"
	 * @param startTimestamp
	 * @param endTimestamp
	 * @return Valor promedio y diferencia porcentual con respecto al valor máximo del precio de BTC
	 */
	Mono<AvgChgDTO> getAverageAndChangeByTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp);
	
	Mono<Coin> save(Coin coin);

}
