package com.wenance.weapp.scheduler;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CexService;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Mono;

@Component
public class CoinScheduler {

	@Autowired
	CexService cexService;

	@Autowired
	CoinService coinService;

	@Scheduled(fixedRate = 10000)
	public void fetchAndSaveCoin() {

		Mono<String> monoCoin = cexService.fetch();

		monoCoin.subscribe(body -> {
			try {
				Coin coin = new ObjectMapper().readValue(body, Coin.class);
				coin.setTimestamp(Instant.now());
				
				coinService.save(coin);
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		});

	}

}
