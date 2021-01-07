package com.wenance.weapp.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wenance.weapp.dto.CoinAvgMaxDTO;
import com.wenance.weapp.entity.Coin;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {
	
	Coin findByTimestamp(LocalDateTime timestamp);
	
	List<Coin> findByTimestampBetween(LocalDateTime timestampOne, LocalDateTime timestampTwo);
	
	@Query(value = "SELECT AVG(c.lprice) as average, MAX(c.lprice) as max "
			+ "FROM Coin c WHERE timestamp >= :timestampOne and timestamp <= :timestampTwo "
			+ "GROUP BY c.curr1, c.curr2", nativeQuery = true)
	CoinAvgMaxDTO getAvgAndMaxByTimestampBetween(@Param("timestampOne") LocalDateTime timestampOne, @Param("timestampTwo") LocalDateTime timestampTwo);

}
