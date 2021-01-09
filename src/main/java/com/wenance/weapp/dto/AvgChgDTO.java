package com.wenance.weapp.dto;

import org.springframework.format.annotation.NumberFormat;
import org.springframework.format.annotation.NumberFormat.Style;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AvgChgDTO {

	@NumberFormat(style = Style.CURRENCY)
	private Double average;

	@NumberFormat(style = Style.PERCENT)
	private Double change;

}
