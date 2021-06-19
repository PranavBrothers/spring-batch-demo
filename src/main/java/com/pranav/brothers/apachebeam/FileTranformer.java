package com.pranav.brothers.apachebeam;

import java.io.IOException;

import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.options.ValueProvider;
import org.apache.beam.sdk.transforms.DoFn;

public class FileTranformer extends DoFn<FileIO.ReadableFile, String> {

	private static final long serialVersionUID = 1L;
	
	private ValueProvider<Integer> batchSize;
	

	public FileTranformer(ValueProvider<Integer> batchSize) {
		super();
		this.batchSize = batchSize;
	}

	@ProcessElement
	public void processElement(ProcessContext context) throws IOException {
		FileIO.ReadableFile inputFile = context.element();
		System.out.println(inputFile.getMetadata().resourceId().getFilename());
		String fileData = inputFile.readFullyAsUTF8String();
		String [] importedFile = fileData.split("\n");
		TransformationService transformationService= new TransformationService();
		
		ImportContext importContext = new ImportContext();
		transformationService.executeFileTransformation(context, importContext, importedFile, batchSize.get());
		
		//String outputString = inputString.toUpperCase();
		//context.output(fileData);
	}
}
