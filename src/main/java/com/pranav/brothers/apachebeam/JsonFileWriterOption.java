package com.pranav.brothers.apachebeam;

import org.apache.beam.sdk.options.Default;
import org.apache.beam.sdk.options.Description;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.ValueProvider;

public interface JsonFileWriterOption extends PipelineOptions{

	@Description("Path of input File")
	@Default.String("./src/main/resources/contacts.csv")
	ValueProvider<String> getInputFile();
	void setInputFile(ValueProvider<String> value);
	
	@Description("Path of output File")
	@Default.String("./src/main.resources/")
	String getOutputFile();
	void setOutputFile(String value);
	
	@Description("Batch Size")
	@Default.Integer(10)
	ValueProvider<Integer> getBlockSize();
	void setBlockSize(ValueProvider<Integer> value);
}
