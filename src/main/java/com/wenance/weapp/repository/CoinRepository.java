package com.wenance.weapp.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wenance.weapp.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {

	Optional<Coin> findByTimestamp(LocalDateTime timestamp);

	List<Coin> findByTimestampBetween(LocalDateTime startTimestamp, LocalDateTime endTimestamp);
	
	@Query("SELECT max(c.lprice) FROM Coin c")
	Double getMaxLprice();

}
