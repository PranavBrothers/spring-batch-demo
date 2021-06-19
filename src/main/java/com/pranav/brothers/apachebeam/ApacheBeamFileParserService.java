package com.pranav.brothers.apachebeam;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.PCollection;

public class ApacheBeamFileParserService{
	
	public static void main(String[] args) {
		JsonFileWriterOption pipelineOptions =
			        PipelineOptionsFactory.fromArgs(args).as(JsonFileWriterOption.class);
		Pipeline pipeline = Pipeline.create(pipelineOptions);
		PCollection<FileIO.ReadableFile> fileData = pipeline.apply(FileIO.match().filepattern(pipelineOptions.getInputFile()))
				.apply(FileIO.readMatches());
		PCollection<String> fileString =  fileData.apply(ParDo.of(new FileTranformer(pipelineOptions.getBlockSize())));
		
		fileString.apply(ParDo.of(new PrintStream()));
		pipeline.run().waitUntilFinish();
	}

	static class PrintStream extends DoFn<String, Void> {
		private static final long serialVersionUID = 1L;
		@ProcessElement
		public void processElement(ProcessContext context) {
			String inputString = context.element();
			System.out.println("data -->"+inputString);
		}
	}

}
