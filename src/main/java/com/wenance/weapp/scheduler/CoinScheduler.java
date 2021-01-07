package com.wenance.weapp.scheduler;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.exception.CoinException;
import com.wenance.weapp.service.CexService;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Mono;

@Component
public class CoinScheduler {
	
	private static final Logger logger = LoggerFactory.getLogger(CoinScheduler.class);

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
				throw new CoinException(e.getMessage());
			}
			logger.info(coin.toString());
			coinService.save(coin);
		});

	}

}
