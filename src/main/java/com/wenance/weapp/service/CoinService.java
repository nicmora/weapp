package com.wenance.weapp.service;

import java.time.Instant;
import java.util.List;

import com.wenance.weapp.dto.CoinAvgMaxDTO;
import com.wenance.weapp.entity.Coin;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CoinService {

	Flux<List<Coin>> findAll();
	
	Mono<Coin> findByTimestamp(Instant timestamp);

	Mono<CoinAvgMaxDTO> getAverageAndMaxByTimestamps(Instant timestampOne, Instant timestampTwo);

	Flux<List<Coin>> findCoinsBetweenTimestamps(Instant timestampOne, Instant timestampTwo);

	Mono<Coin> save(Coin coin);

}
