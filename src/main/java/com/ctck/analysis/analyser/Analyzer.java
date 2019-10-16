package com.ctck.analysis.analyser;

import java.util.Map;

import com.ctck.analysis.model.AnalysisOutput;

public interface Analyzer {
	Map<String, Object> analyze(double[][]... arr);
}
