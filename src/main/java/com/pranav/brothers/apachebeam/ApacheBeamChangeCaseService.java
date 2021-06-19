package com.pranav.brothers.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.Create;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class ApacheBeamChangeCaseService{

	public static void main(String[] args) {
		PipelineOptions pipelineOptions = PipelineOptionsFactory.create();
		Pipeline pipeline = Pipeline.create(pipelineOptions);
		PCollection<String> upperCaseStream = pipeline.apply(Create.of("WELCOME", "TO", "APACHE BEAM", "LEARNING"));
		PCollection<String> lowerCaseStream = upperCaseStream.apply(ParDo.of(new ChangeToLowerCase()));
		lowerCaseStream.apply(ParDo.of(new PrintStream()));
		pipeline.run().waitUntilFinish();
	}

	static class ChangeToLowerCase extends DoFn<String, String> {
		private static final long serialVersionUID = 1L;
		@ProcessElement
		public void processElement(ProcessContext context) {
			String inputString = context.element();
			String outputString = inputString.toLowerCase();
			context.output(outputString);
		}
	}

	static class PrintStream extends DoFn<String, Void> {
		private static final long serialVersionUID = 1L;
		@ProcessElement
		public void processElement(ProcessContext context) {
			String inputString = context.element();
			System.out.println(inputString);
		}
	}

}
