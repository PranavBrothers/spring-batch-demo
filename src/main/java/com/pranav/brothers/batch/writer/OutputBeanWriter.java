package com.pranav.brothers.batch.writer;

import static org.springframework.http.HttpMethod.POST;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pranav.brothers.beans.OutputBean;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OutputBeanWriter implements ItemWriter<OutputBean> {
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${api.url}")
	private String apiUrl;

	@Override
	public void write(List<? extends OutputBean> outputBean) throws Exception {
		log.info("JSON Data --> {}", objectMapper.writeValueAsString(outputBean));
		log.info("Response body {}", invokeHttpPost(outputBean, apiUrl));
	}
	
	private String invokeHttpPost(Object requestPayload, String endPointUrl) {
		ResponseEntity<String> response = restTemplate.exchange(endPointUrl, POST,
				new HttpEntity<>(requestPayload, header), String.class);
		return response.getBody();
	}
	
	static HttpHeaders header = new HttpHeaders();
	static {
		header.setContentType(MediaType.APPLICATION_JSON);
	}
}

