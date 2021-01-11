package com.wenance.weapp.scheduler;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CexService;
import com.wenance.weapp.service.CoinService;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@EnableScheduling
@ConditionalOnProperty(value = "app.scheduling.enable", havingValue = "true", matchIfMissing = true)
@Component
public class CoinScheduler {

	@Autowired
	private CexService cexService;

	@Autowired
	private CoinService coinService;

	/*
	 * Método programado para ejecutarse cada 10 segundos
	 */
	@Scheduled(fixedRate = 10000)
	public void fetchAndSaveCoin() {

		Mono<String> monoResponse = cexService.fetchPrice();
		monoResponse.subscribe(response -> {
			try {
				Coin coin = new ObjectMapper().readValue(response, Coin.class);
				// Se omite los milisegundos
				LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
				coin.setTimestamp(now);
				CoinScheduler.log.info(coin.toString());
				
				coinService.save(coin);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		});

	}

}
