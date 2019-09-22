package com.ctck.analysis;

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
    	double[][] arr = new double[][] {
    		{8, 9, 8, 7, 5, 2, 9},
    		{7, 8, 7, 5, 7, 3, 8},
    		{9, 7, 9, 6, 6, 4, 7},
    		{6, 8, 8, 8, 4, 3, 6},
    		{8, 6, 6, 9, 8, 3, 8},
    		{8, 9, 5, 7, 6, 4, 8}
    	};
    	Analyzer GRA = new GRAAnalyzer();
    	AnalysisOutput ao = GRA.analyze(arr);
    	printMatrix(ao.getMatrix());
		System.out.println("GRAY RELATION MATRIX");
		
		
    	
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
