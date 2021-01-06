package com.wenance.weapp.controller;

import java.time.Instant;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.weapp.dto.CoinAvgMaxDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class CoinController {

	@Autowired
	CoinService coinService;

	@GetMapping("/coins")
	public Flux<List<Coin>> getAll() {
		return coinService.findAll();
	}

	@GetMapping("/coin")
	public Mono<Coin> getCoinByTimestamp(@RequestParam Instant timestamp) {
		return coinService.findByTimestamp(timestamp);
	}
	
	@GetMapping("/coins/timestamps")
	public Flux<List<Coin>> getCoinsBetweenTimestamps(
			@RequestParam Instant timestampOne,
			@RequestParam Instant timestampTwo) {
		return coinService.findCoinsBetweenTimestamps(timestampOne, timestampTwo);
	}

	@GetMapping("/coin/avg")
	public Mono<CoinAvgMaxDTO> getAverageAndPercentageMaxByTimestamps(
			@RequestParam Instant timestampOne,
			@RequestParam Instant timestampTwo) {
		return coinService.getAverageAndMaxByTimestamps(timestampOne, timestampTwo);
	}

}