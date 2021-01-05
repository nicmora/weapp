package com.wenance.weapp.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.weapp.dto.CoinAveragePercentage;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/btc")
public class CoinController {

	@Autowired
	CoinService coinService;

	@GetMapping
	public Flux<List<Coin>> getAll() {
		return coinService.findAll();
	}
	
	@GetMapping("getCoinByTimestamp")
	public Mono<Coin> getCoinByTimestamp(@RequestParam Instant timestamp) {
		return coinService.findByTimestamp(timestamp);
	}
	
	@GetMapping("getAverageAndPercentageMaxByTimestamps")
	public Mono<CoinAveragePercentage> getAverageAndPercentageMaxByTimestamps(
			@RequestParam Instant timestampOne,
			@RequestParam Instant timestampTwo) {
		return coinService.getAverageAndPercentageMaxByTimestamps(timestampOne, timestampTwo);
	}
	
	@GetMapping("getCoinsBetweenTimestamps")
	public Flux<List<Coin>> getCoinsBetweenTimestamps(
			@RequestParam Instant timestampOne,
			@RequestParam Instant timestampTwo) {
		return coinService.findCoinsBetweenTimestamps(timestampOne, timestampTwo);
	}

}
