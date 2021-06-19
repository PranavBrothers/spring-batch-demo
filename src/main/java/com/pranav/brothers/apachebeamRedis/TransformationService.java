package com.pranav.brothers.apachebeamRedis;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.DoFn.ProcessContext;
import org.apache.beam.sdk.values.KV;

public class TransformationService {

	public List<String> executeFileTransformation(DoFn.ProcessContext context, String[] lines) {
		return  Arrays.asList(lines).stream()
				.filter(row -> row !=null)
				.peek(row -> processRow(context,row))
				.collect(Collectors.toList());
	}

	private void processRow(ProcessContext context,  String row) {
		String[] columnArray = row.split("\\,");
		context.output(KV.of( columnArray[0], columnArray[1]));
	}
	
}