package com.wenance.weapp.service;

import java.time.LocalDateTime;

import com.wenance.weapp.dto.AvgMaxDTO;
import com.wenance.weapp.entity.Coin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinService {

	Flux<Coin> findAll();
	
	Mono<Coin> findByTimestamp(LocalDateTime timestamp);

	Flux<Coin> findCoinsBetweenTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo);

	Mono<AvgMaxDTO> getAvgAndMaxByTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo);
	
	Mono<Coin> save(Coin coin);

}
