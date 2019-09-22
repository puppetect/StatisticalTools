package com.ctck.analysis;

import com.ctck.analysis.analyser.AHPAnalyzer;
import com.ctck.analysis.analyser.Analyzer;
import com.ctck.analysis.analyser.GRAAnalyzer;
import com.ctck.analysis.model.AnalysisOutput;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
//    	double[][] arr = new double[][] {
//    		{8, 9, 8, 7, 5, 2, 9},
//    		{7, 8, 7, 5, 7, 3, 8},
//    		{9, 7, 9, 6, 6, 4, 7},
//    		{6, 8, 8, 8, 4, 3, 6},
//    		{8, 6, 6, 9, 8, 3, 8},
//    		{8, 9, 5, 7, 6, 4, 8}
//    	};
//    	Analyzer GRA = new GRAAnalyzer();
//    	AnalysisOutput ao = GRA.analyze(arr);
//    	System.out.println("GRAY RELATION MATRIX");
//    	printMatrix(ao.getMatrix());
		
		
		double[][] criteria = new double[][] {
			{1, 1, 1, 4, 1, (float)1/2},
			{1, 1, 2, 4, 1, (float)1/2},
			{1, (float)1/2, 1, 5, 3, (float)1/2},
			{(float)1/4, (float)1/4, (float)1/5, 1, (float)1/3, (float)1/3},
			{1, 1, (float)1/3, 3, 1, 1},
			{2, 2, 2, 3, 1, 1}
			};
		double[][] score1 = new double[][] {
			{1, 1/4, 1/2},
			{4, 1, 3},
			{2, 1/3, 1}
		};
		double[][] score2 = new double[][] {
			{1, 1/4, 1/5},
			{4, 1, 1/2},
			{5, 2, 1}
		};
		double[][] score3 = new double[][] {
			{1, 3, 1/5},
			{1/3, 1, 1},
			{5, 1, 1}
		};
		double[][] score4 = new double[][] {
			{1, 1/3, 5},
			{3, 1, 7},
			{1/5, 1/7, 1}
		};
		double[][] score5 = new double[][] {
			{1, 1, 7},
			{1, 1, 7},
			{1/7, 1/7, 1}
		};
		double[][] score6 = new double[][] {
			{1, 7, 9},
			{1/7, 1, 5},
			{1/9, 1/5, 1}
		};
		Analyzer AHP = new AHPAnalyzer();
		AnalysisOutput ahpo = AHP.analyze(criteria);
    }
    
    private static void printMatrix(double[][] arr) {
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				System.out.print(String.format("%.2f", arr[i][j]) + " | ");
			}
			
		System.out.print("\n");
		}
	}
}
