package com.ctck.analysis.analyser;

import com.ctck.analysis.model.AnalysisOutput;

public interface Analyzer {
	AnalysisOutput analyze(double[][] arr);
}
