package com.pranav.brothers.rest;

import java.util.List;

import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pranav.brothers.beans.OutputBean;
import com.pranav.brothers.service.BatchService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class BatchController {

	@Autowired
	private BatchService batchService;
	
	@PostMapping("/start")
    public String startBatch(@RequestBody String filePath) throws Exception {
		JobParametersBuilder paramsBuilder = new JobParametersBuilder();
		paramsBuilder.addString("file.input", filePath);
		batchService.start(paramsBuilder);
        return "Batch job has been invoked";
    }
	
	@PostMapping("/accept-data")
    public String accetpData(@RequestBody List<OutputBean> outputBeans) throws Exception {
		log.info("Batch Data Received. Size-->{}",outputBeans.size());
        return "Batch Data Received";
    }
}
