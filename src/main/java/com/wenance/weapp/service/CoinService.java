package com.wenance.weapp.service;

import java.time.LocalDateTime;
import java.util.List;

import com.wenance.weapp.dto.CoinAvgMaxDTO;
import com.wenance.weapp.entity.Coin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinService {

	Flux<List<Coin>> findAll();
	
	Mono<Coin> findByTimestamp(LocalDateTime timestamp);

	Mono<CoinAvgMaxDTO> getAverageAndMaxByTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo);

	Flux<List<Coin>> findCoinsBetweenTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo);

	Mono<Coin> save(Coin coin);

}
