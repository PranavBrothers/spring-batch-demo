package com.pranav.brothers.apachebeamRedis;

import java.io.IOException;

import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.values.KV;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class KeyValueTransformer extends DoFn<FileIO.ReadableFile, KV<String, String>> {

	private static final long serialVersionUID = 1L;
	
	@ProcessElement
	public void processElement(ProcessContext context) throws IOException {
		FileIO.ReadableFile inputFile = context.element();
		String fileData = inputFile.readFullyAsUTF8String();
		String [] importedFile = fileData.split("\n");
		TransformationService transformationService= new TransformationService();
		transformationService.executeFileTransformation(context, importedFile);
	}
}