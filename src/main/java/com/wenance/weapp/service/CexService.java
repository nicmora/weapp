package com.wenance.weapp.service;

import reactor.core.publisher.Mono;

public interface CexService {
	
	Mono<String> fetch();

}
