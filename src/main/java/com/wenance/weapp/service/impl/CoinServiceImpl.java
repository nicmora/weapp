package com.wenance.weapp.service.impl;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenance.weapp.dto.AvgMaxDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.repository.CoinRepository;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CoinServiceImpl implements CoinService {

	private static final Logger logger = LoggerFactory.getLogger(CoinService.class);

	@Autowired
	CoinRepository coinRepository;

	@Override
	public Flux<Coin> findAll() {
		return Flux.fromIterable(coinRepository.findAll());
	}

	@Override
	public Mono<Coin> findByTimestamp(LocalDateTime timestamp) {
		return Mono.justOrEmpty(coinRepository.findByTimestamp(timestamp));
	}

	@Override
	public Flux<Coin> findCoinsBetweenTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo) {
		return Flux.fromIterable(coinRepository.findByTimestampBetween(timestampOne, timestampTwo));
	}	

	@Override
	public Mono<AvgMaxDTO> getAvgAndMaxByTimestamps(LocalDateTime timestampOne, LocalDateTime timestampTwo) {
		Flux<Coin> fluxCoin = Flux.fromIterable(coinRepository.findByTimestampBetween(timestampOne, timestampTwo));
		Mono<AvgMaxDTO> monoAvgMax = fluxCoin.collect(Collectors.summarizingDouble(Coin::getLprice)).flatMap(sum -> {
			Double avg = sum.getAverage();
			Double max = sum.getMax();
			return Mono.just(new AvgMaxDTO(avg, max));
		});

		fluxCoin.subscribe(n -> logger.info("AVG MAX :" + n.toString()));

		return monoAvgMax;
	}

	@Override
	public Mono<Coin> save(Coin coin) {
		return Mono.just(coinRepository.save(coin));
	}

}
