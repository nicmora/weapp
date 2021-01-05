package com.wenance.weapp.service;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenance.weapp.dto.CoinAveragePercentage;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.repository.CoinRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoinServiceImpl implements CoinService {

	@Autowired
	CoinRepository coinRepository;

	@Override
	public Flux<List<Coin>> findAll() {
		return Flux.just(coinRepository.findAll());
	}

	@Override
	public Mono<Coin> findByTimestamp(Instant timestamp) {
		return Mono.just(coinRepository.findByTimestamp(timestamp));
	}
	
	@Override
	public Flux<List<Coin>> findCoinsBetweenTimestamps(Instant timestampOne, Instant timestampTwo) {
		return Flux.just(coinRepository.findByTimestampBetween(timestampOne, timestampTwo));
	}
	
	@Override
	public Mono<CoinAveragePercentage> getAverageAndPercentageMaxByTimestamps(Instant timestampOne,
			Instant timestampTwo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(Coin coin) {
		coinRepository.save(coin);
	}

}
