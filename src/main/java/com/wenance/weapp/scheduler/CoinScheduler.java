package com.wenance.weapp.scheduler;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CexService;
import com.wenance.weapp.service.CoinService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class CoinScheduler {

	@Autowired
	CexService cexService;

	@Autowired
	CoinService coinService;

	@Scheduled(fixedRate = 10000)
	public void fetchAndSaveCoin() {

		Mono<String> monoResponse = cexService.updatePrice();
		monoResponse.subscribe(response -> {
			Coin coin = null;
			try {
				coin = new ObjectMapper().readValue(response, Coin.class);
				coin.setTimestamp(LocalDateTime.now());
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
			CoinScheduler.log.info(coin.toString());
			coinService.save(coin);
		});

	}

}
