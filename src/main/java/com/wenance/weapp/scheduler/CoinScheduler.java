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

@Slf4j
@Component
public class CoinScheduler {

	@Autowired
	private CexService cexService;

	@Autowired
	private CoinService coinService;

	/*
	 * Este método está configurado para ejecutarse cada 10 segundos automáticamente.
	 * Su función es llamar al servicio cexService que retorna un string
	 * con el siguiente formato: {"lprice":"31500.5","curr1":"BTC","curr2":"USD"}.
	 * Luego se construye la entidad para ser persistida mediante el servicio coinService.
	 */
	@Scheduled(fixedRate = 10000)
	public void fetchAndSaveCoin() {

		Mono<String> monoResponse = cexService.fetchPrice();
		monoResponse.subscribe(response -> {
			try {
				Coin coin = new ObjectMapper().readValue(response, Coin.class);
				coin.setTimestamp(LocalDateTime.now());
				CoinScheduler.log.info(coin.toString());
				coinService.save(coin);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		});

	}

}
