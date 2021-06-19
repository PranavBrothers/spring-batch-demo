package com.pranav.brothers.apachebeam;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessContext;

public class TransformationService {

	public List<String> executeFileTransformation(DoFn.ProcessContext context, ImportContext importContext, String[] lines, Integer batchSize) {
		return  Arrays.asList(lines).stream()
				.peek(row -> importContext.getRowNumber().getAndIncrement())
				.filter(row -> row !=null)
				.peek(row -> processRow(context, importContext,row, batchSize))
				.collect(Collectors.toList());
	}

	private void processRow(ProcessContext context, ImportContext importContext, String row, Integer batchSize) {
		String[] columnArray = row.split("\\,");
		String batchData = columnArray[0]+"--"+columnArray[1];
		//importContext.getBatchData().add(batchData);
		//context.output(batchData);
		if(importContext.getBatchData().size()<batchSize) {
			importContext.getBatchData().add(batchData);
		}else {
			context.output(importContext.getBatchData());
			importContext.getBatchData().clear();
			importContext.getBatchData().add(batchData);
		}
	}
	
}
