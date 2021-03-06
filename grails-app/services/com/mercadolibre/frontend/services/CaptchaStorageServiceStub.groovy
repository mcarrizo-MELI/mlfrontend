package com.mercadolibre.frontend.services

class CaptchaStorageServiceStub {

	static transactional = false
	
	private static cache = [:]

	public get(key) {
		cache[key]
	}
	
	public set(String key, String value){
		cache[key] = value
	}
	
	public append(key, append){
		cache[key] = cache[key] + append
	}
}