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
	 * Método que se encarga de realizar una búsqueda de objetos Coin a partir de dos timestamps 
	 * aplicando el operador between.
	 * 
	 * @param startTimestamp 	Timestamp de clase <code>LocalDateTime</code> en formato <code>"dd/MM/yyyy HH:mm:ss"</code>
	 * @param endTimestamp 		Timestamp de clase <code>LocalDateTime</code> en formato <code>"dd/MM/yyyy HH:mm:ss"</code>
	 * 
	 * @return Flujo de objetos de clase Coin 
	 */
	Flux<Coin> findCoinsBetweenTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp);

	/**
	 * Método que se encarga de calcular el valor promedio y variación con respecto al máximo precio del BTC. Para esto se 
	 * realiza una búsqueda de objetos Coin a partir de dos timestamps aplicando el operador between.
	 * 
	 * @param startTimestamp 	Timestamp de clase <code>LocalDateTime</code> en formato <code>"dd/MM/yyyy HH:mm:ss"</code>
	 * @param endTimestamp 		Timestamp de clase <code>LocalDateTime</code> en formato <code>"dd/MM/yyyy HH:mm:ss"</code>
	 * 
	 * @return valor promedio y variación con respecto al máximo precio del BTC
	 */
	Mono<AvgChgDTO> getAverageAndChangeByTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp);
	
	Mono<Coin> save(Coin coin);

}
