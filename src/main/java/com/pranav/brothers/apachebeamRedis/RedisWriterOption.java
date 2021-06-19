package com.pranav.brothers.apachebeamRedis;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface RedisWriterOption extends PipelineOptions{

	@Description("Path of input File")
	@Default.String("./src/main/resources/contacts.csv")
	ValueProvider<String> getInputFile();
	void setInputFile(ValueProvider<String> value);
	
	@Description("Default Redis Host Name")
	@Default.String("localhost")
	String getRedisHost();
	void setRedisHost(String value);
	
	@Description("Default Redis Port Number")
	@Default.Integer(6379)
	Integer getRedisPort();
	void setRedisPort(Integer portNo);
}