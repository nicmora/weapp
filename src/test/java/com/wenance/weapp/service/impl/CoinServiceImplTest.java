package com.wenance.weapp.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import com.wenance.weapp.dto.AvgChgDTO;
import com.wenance.weapp.entity.Coin;
import com.wenance.weapp.repository.CoinRepository;
import com.wenance.weapp.service.CoinService;

@TestPropertySource(properties = "app.scheduling.enable=false")
@SpringBootTest
class CoinServiceImplTest {

	@Autowired
	private CoinService coinService;

	@MockBean
	private CoinRepository coinRepository;

	private List<Coin> data(LocalDateTime now) {
		Coin coinOne = Coin.builder()
				.lprice(new Double(30000))
				.curr1("BTC")
				.curr2("USD")
				.timestamp(now)
				.build();
		
		Coin coinTwo = Coin.builder()
				.lprice(new Double(30500))
				.curr1("BTC")
				.curr2("USD")
				.timestamp(now.plusSeconds(10))
				.build();
		
		Coin coinThree = Coin.builder()
				.lprice(new Double(31000))
				.curr1("BTC")
				.curr2("USD")
				.timestamp(now.plusSeconds(20))
				.build();

		List<Coin> coins = new ArrayList<>();
		coins.add(coinOne);
		coins.add(coinTwo);
		coins.add(coinThree);

		return coins;
	}

	@Test
	void testGetAvgAndMaxByTimestamps() {
		LocalDateTime now = LocalDateTime.now().withNano(0);
		LocalDateTime start = LocalDateTime.from(now.minusSeconds(1));
		LocalDateTime end = LocalDateTime.from(now).plusSeconds(31);

		when(coinRepository.findByTimestampBetween(start, end)).thenReturn(this.data(now));
		when(coinRepository.getMaxLprice()).thenReturn(31000d);

		AvgChgDTO monoAvgChg = coinService.getAverageAndChangeByTimestamps(start, end).block();

		assertThat(monoAvgChg.getAverage()).isEqualTo(30500);
		assertThat(monoAvgChg.getChange()).isEqualTo(-0.01613);
	}

}
