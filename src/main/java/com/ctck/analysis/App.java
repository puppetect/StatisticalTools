package com.ctck.analysis;

import com.ctck.analysis.analyser.AHPAnalyzer;
import com.ctck.analysis.analyser.Analyzer;
import com.ctck.analysis.analyser.GRAAnalyzer;
import com.ctck.analysis.model.AnalysisOutput;
import com.ctck.analysis.normalizer.MinMaxNormalizer;
import com.ctck.analysis.normalizer.Normalizer;
import com.ctck.analysis.normalizer.SumScaleNormalizer;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	double[][] arr = new double[][] {{1,1,1},{2,2,2},{3,3,3}};
    	Normalizer norm = new SumScaleNormalizer();
//    	printMatrix("AXIS0 NORM", norm.normalize(arr, 0));
//    	arr = new double[][] {{1,1,1},{2,2,2},{3,3,3}};
//    	printMatrix("AXIS1 NORM", norm.normalize(arr, 1));
//    	double[][] arr = new double[][] {
//		{7.4, 0.70, 0.00, 1.9, 0.076, 11.0, 34.0, 0.9978, 3.51, 0.56, 9.4, 5},
//		{7.8, 0.88, 0.00, 2.6, 0.098, 25.0, 67.0, 0.9968, 3.20, 0.68, 9.8, 5},
//		{7.8, 0.76, 0.04, 2.3, 0.092, 15.0, 54.0, 0.9970, 3.26, 0.65, 9.8, 5},
//		{11.2, 0.28, 0.56, 1.9, 0.075, 17.0, 60.0, 0.9980, 3.16, 0.58, 9.8, 6},
//		{7.4, 0.70, 0.00, 1.9, 0.076, 11.0, 34.0, 0.9978, 3.51, 0.56, 9.4, 5}
//	};
    	double[][] arr = new double[][] {
    		{8, 9, 8, 7, 5, 2, 9},
    		{7, 8, 7, 5, 7, 3, 8},
    		{9, 7, 9, 6, 6, 4, 7},
    		{6, 8, 8, 8, 4, 3, 6},
    		{8, 6, 6, 9, 8, 3, 8},
    		{8, 9, 5, 7, 6, 4, 8}
    	};
//    	arr = norm.normalize(arr, 0);
    	Analyzer GRA = new GRAAnalyzer();
    	AnalysisOutput ao = GRA.analyze(arr);
    	printMatrix("GRAY RELATION MATRIX", ao.getMatrix());
		
		
//		double[][] criteria = new double[][] {
//			{1, 1, 1, 4, 1, (float)1/2},
//			{1, 1, 2, 4, 1, (float)1/2},
//			{1, (float)1/2, 1, 5, 3, (float)1/2},
//			{(float)1/4, (float)1/4, (float)1/5, 1, (float)1/3, (float)1/3},
//			{1, 1, (float)1/3, 3, 1, 1},
//			{2, 2, 2, 3, 1, 1}
//			};
//		double[][] score1 = new double[][] {
//			{1, 1/4, 1/2},
//			{4, 1, 3},
//			{2, 1/3, 1}
//		};
//		double[][] score2 = new double[][] {
//			{1, 1/4, 1/5},
//			{4, 1, 1/2},
//			{5, 2, 1}
//		};
//		double[][] score3 = new double[][] {
//			{1, 3, 1/5},
//			{1/3, 1, 1},
//			{5, 1, 1}
//		};
//		double[][] score4 = new double[][] {
//			{1, 1/3, 5},
//			{3, 1, 7},
//			{1/5, 1/7, 1}
//		};
//		double[][] score5 = new double[][] {
//			{1, 1, 7},
//			{1, 1, 7},
//			{1/7, 1/7, 1}
//		};
//		double[][] score6 = new double[][] {
//			{1, 7, 9},
//			{1/7, 1, 5},
//			{1/9, 1/5, 1}
//		};
//		Analyzer AHP = new AHPAnalyzer();
//		AnalysisOutput ahpo = AHP.analyze(criteria);
    }
    
    private static void printMatrix(String name, double[][] arr) {
		System.out.println(name);
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				System.out.print(String.format("%.2f", arr[i][j]) + " | ");
			}
			
			System.out.println("");
		}
		System.out.println("");
	}
}
