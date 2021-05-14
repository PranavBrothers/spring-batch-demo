package com.pranav.brothers.batch;

import java.io.IOException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.pranav.brothers.batch.processor.InputBeanProcessor;
import com.pranav.brothers.batch.reader.InputBeanFieldSetMapper;
import com.pranav.brothers.batch.writer.OutputBeanWriter;
import com.pranav.brothers.beans.InputBean;
import com.pranav.brothers.beans.OutputBean;

@Configuration
@EnableBatchProcessing
public class SpringBatchConfiguration {
	
	@Value("${chunk.size}")
	private int chunkSize;

	private static final String[] TOKENS = {"first_name","last_name","company_name","address","city","county","state","zip","phone1","phone","email"};

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory;

	@Bean(name = "transformContacts")
	public Job transformBookRecords(Step step1) throws IOException {
		return jobBuilderFactory.get("transformContacts").flow(step1).end().build();
	}

	@Bean
	@StepScope
	public FlatFileItemReader<InputBean> csvItemReader(@Value("#{jobParameters['file.input']}") String input) {
		FlatFileItemReaderBuilder<InputBean> builder = new FlatFileItemReaderBuilder<>();
		FieldSetMapper<InputBean> inputBeanFieldSetMapper = new InputBeanFieldSetMapper();
		return builder.name("inputCSVReader").resource(new FileSystemResource(input)).delimited().names(TOKENS)
				.fieldSetMapper(inputBeanFieldSetMapper).build();
	}

	@Bean
	@StepScope
	public InputBeanProcessor inputBeanProcessor() {
		return new InputBeanProcessor();
	}

	@Bean
	@StepScope
	public OutputBeanWriter outputBeanWriter() {
		return new OutputBeanWriter();
	}

	@Bean
	public Step step1(ItemReader<InputBean> csvItemReader, ItemWriter<OutputBean> outputBeanWriter) throws IOException {
		return stepBuilderFactory.get("step1").<InputBean, OutputBean>chunk(chunkSize).reader(csvItemReader)
				.processor(inputBeanProcessor()).writer(outputBeanWriter).build();
	}

}