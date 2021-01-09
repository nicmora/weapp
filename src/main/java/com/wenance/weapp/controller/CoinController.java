package com.wenance.weapp.controller;

import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wenance.weapp.dto.AvgChgDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.service.CoinService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/coins")
public class CoinController {

	@Autowired
	private CoinService coinService;

	@GetMapping
	public Flux<Coin> getAll() {
		return coinService.findAll();
	}

	@GetMapping("/get-coin")
	public Mono<Coin> getCoinByTimestamp(@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime timestamp) {
		return coinService.findByTimestamp(timestamp);
	}
	
	@GetMapping("/get-coins")
	public Flux<Coin> getCoinsBetweenTimestamps(
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime startTimestamp,
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime endTimestamp) {
		return coinService.findCoinsBetweenTimestamps(startTimestamp, endTimestamp);
	}

	@GetMapping("/avg-chg")
	public Mono<AvgChgDTO> getAverageAndChangeByTimestamps(
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime startTimestamp,
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime endTimestamp) {
		return coinService.getAverageAndChangeByTimestamps(startTimestamp, endTimestamp);
	}

}
