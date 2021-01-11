package com.wenance.weapp.service;

import reactor.core.publisher.Mono;

public interface CexService {
	
	/**
	 * Método que recupera mediante un fetch a la api externa {@link https://cex.io/api/last_price/BTC/USD} 
	 * el precio actualizado del BTC con respecto al USD. Se utiliza <code>WebClient</code> para las solicitudes.
	 *  
	 * @return String con la actualización del precio de BTC/USD. {"lprice":"30000.0","curr1":"BTC","curr2":"USD"}
	 */
	Mono<String> fetchPrice();

}
