package com.pranav.brothers.service;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BatchService {
	
	@Autowired
    @Qualifier("transformContacts")
    private Job transformContacts;
	  
	@Autowired
    private JobLauncher jobLauncher;

    public String start(JobParametersBuilder paramsBuilder) throws Exception {
        jobLauncher.run(transformContacts, paramsBuilder.toJobParameters());
        return "Batch job has been invoked";
    }
}