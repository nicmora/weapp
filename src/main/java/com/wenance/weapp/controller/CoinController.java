package com.wenance.weapp.controller;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
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

	/*@GetMapping("/coin")
	public Mono<Coin> getCoinByTimestamp(@NotNull @RequestParam LocalDateTime timestamp) {
		return coinService.findByTimestamp(timestamp);
	}*/
	
	@GetMapping("/coins/timestamps")
	public Flux<List<Coin>> getCoinsBetweenTimestamps(
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime timestampOne,
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime timestampTwo) {
		return coinService.findCoinsBetweenTimestamps(timestampOne, timestampTwo);
	}

	@GetMapping("/coin/avg")
	public Mono<CoinAvgMaxDTO> getAverageAndMaxByTimestamps(
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime timestampOne,
			@RequestParam @NotNull @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss") LocalDateTime timestampTwo) {
		return coinService.getAverageAndMaxByTimestamps(timestampOne, timestampTwo);
	}

}
