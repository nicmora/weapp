package com.wenance.weapp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.wenance.weapp.dto.AvgChgDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.repository.CoinRepository;
import com.wenance.weapp.service.CoinService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest
class CoinServiceImplTest {
	
	@Autowired
	private CoinService coinService;
	
	@Autowired
	private CoinRepository coinRepository;

	private LocalDateTime now;

	@BeforeEach
	void init() {
		this.now = LocalDateTime.now();
		LocalDateTime nowAux = LocalDateTime.from(now);

		Coin coinOne = Coin.builder().lprice(new Double(30000)).curr1("BTC").curr2("USD").timestamp(nowAux).build();
		coinService.save(coinOne);
		CoinServiceImplTest.log.info(coinOne.toString());

		Coin coinTwo = Coin.builder().lprice(new Double(30500)).curr1("BTC").curr2("USD").timestamp(nowAux.plusSeconds(10)).build();
		coinService.save(coinTwo);
		CoinServiceImplTest.log.info(coinTwo.toString());

		Coin coinThree = Coin.builder().lprice(new Double(31000)).curr1("BTC").curr2("USD").timestamp(nowAux.plusSeconds(20)).build();
		coinService.save(coinThree);
		CoinServiceImplTest.log.info(coinThree.toString());
		
	}
	
	@AfterEach
	void clear() {
		coinRepository.deleteAll();
	}

//	@Test
//	void testGetAvgAndMaxByTimestamps() {
//		LocalDateTime start = LocalDateTime.from(now).plusSeconds(6);
//		LocalDateTime end = LocalDateTime.from(now).plusSeconds(12);
//				
//		coinService.findAll().subscribe(s -> CoinServiceImplTest.log.info(s.toString()));
//		CoinServiceImplTest.log.info(start.toString());
//		CoinServiceImplTest.log.info(now.toString());
//		CoinServiceImplTest.log.info(end.toString());
//		
//		AvgChgDTO monoAvgChg = coinService.getAverageAndChangeByTimestamps(start, end).block();
//		assertThat(monoAvgChg.getAverage()).isEqualTo(30500);
//	}

	@Test
	void testSave() {
		Coin coinAny = Coin.builder()
				.lprice(new Double(30000))
				.curr1("BTC").curr2("USD")
				.timestamp(LocalDateTime.now()).build();
		
		assertThat(coinService.save(coinAny).block().getClass()).isEqualTo(Coin.class);
	}

}
