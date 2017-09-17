package com.system.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EsConfig {

	@Value("${es.host}")
	private String esHost;

	@Bean
	public RestClient getRestClient() {
		String[] array = esHost.split(":");
		if (array != null && array.length == 2) {
			RestClient restClient = RestClient.builder(new HttpHost(array[0], Integer.valueOf(array[1]), "http"))
					.build();
			return restClient;
		}

		return null;

	}

}
