package com.pranav.brothers.apachebeamRedis;

import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.FileIO;
import org.apache.beam.sdk.io.redis.RedisIO;
import org.apache.beam.sdk.io.redis.RedisIO.Write.Method;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PDone;

public class ApacheBeamFileParserToRedisService{
	
	public static void main(String[] args) {
		RedisWriterOption pipelineOptions = PipelineOptionsFactory.fromArgs(args).as(RedisWriterOption.class);
		Pipeline pipeline = Pipeline.create(pipelineOptions);
		PCollection<FileIO.ReadableFile> fileData = pipeline.apply(FileIO.match().filepattern(pipelineOptions.getInputFile())).apply(FileIO.readMatches());
		PCollection<KV<String, String>> keyValueData =  fileData.apply(ParDo.of(new KeyValueTransformer()));
		PDone pDone = keyValueData.apply(RedisIO.write().withEndpoint(pipelineOptions.getRedisHost(), pipelineOptions.getRedisPort()).withMethod(Method.SET));
		pipeline.run();//.waitUntilFinish();
	}
	
	 
}