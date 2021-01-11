package com.wenance.weapp.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wenance.weapp.dto.AvgChgDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.repository.CoinRepository;
import com.wenance.weapp.service.CoinService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class CoinServiceImpl implements CoinService {

	private CoinRepository coinRepository;
	
	@Autowired
	public CoinServiceImpl(CoinRepository coinRepository) {
		this.coinRepository = coinRepository;
	}

	@Override
	public Flux<Coin> findAll() {
		return Flux.fromIterable(coinRepository.findAll());
	}

	@Override
	public Mono<Coin> findByTimestamp(LocalDateTime timestamp) {
		return Mono.justOrEmpty(coinRepository.findByTimestamp(timestamp));
	}

	@Override
	public Flux<Coin> findCoinsBetweenTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
		return Flux.fromIterable(coinRepository.findByTimestampBetween(startTimestamp, endTimestamp));
	}	

	@Override
	public Mono<AvgChgDTO> getAverageAndChangeByTimestamps(LocalDateTime startTimestamp, LocalDateTime endTimestamp) {
		
		Flux<Coin> fluxCoin = Flux.fromIterable(coinRepository.findByTimestampBetween(startTimestamp, endTimestamp));
		fluxCoin.subscribe(n -> CoinServiceImpl.log.info("AVG MAX :" + n.toString()));
		
		Mono<AvgChgDTO> monoAvgMax = fluxCoin.collectList().flatMap(coins -> {
			
			if(!coins.isEmpty()) {		
				double avg = coins.stream().collect(Collectors.averagingDouble(Coin::getLprice));
				double max = coinRepository.getMaxLprice();
				double chgAux = avg / max - 1;
				// Se utiliza BigDecimal para redondear el resultado y dar formato
				BigDecimal chg = new BigDecimal(chgAux).setScale(5, RoundingMode.HALF_UP);
				
				return Mono.just(new AvgChgDTO(max, chg.doubleValue()));		
			} else {
				return Mono.empty();
			}
			
		});
				
		return monoAvgMax;	
	}

	@Override
	public Mono<Coin> save(Coin coin) {
		return Mono.just(coinRepository.save(coin));
	}

}
