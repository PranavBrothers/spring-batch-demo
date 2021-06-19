package com.pranav.brothers.apachebeam;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import lombok.Data;

@Data
public class ImportContext {

	private AtomicInteger rowNumber = new AtomicInteger();
	private List<String> batchData = new ArrayList<>();
}
