package com.ctck.analysis.analyser;

import com.ctck.analysis.model.AnalysisOutput;

public class GRAAnalyzer implements Analyzer {

	static double rho = 0.5;
	
	public AnalysisOutput analyze(double[][] arr) {
		//每个项目的每个指标得分和满分比较，得出各项目得分
		double[] refRow = new double[] {9, 9, 9, 9, 8, 9, 9};
		double[] vectorOutput = singleGRA(arr, refRow);
		//每个指标作为参考值和其他指标比较，得出关联度
		double[][] matrixTransposed = transpose(arr);
		double[][] matrixOutput = new double[arr[0].length][arr.length];
		for(int i=0; i<matrixTransposed.length; i++) {
			double[] refVector = matrixTransposed[i];
			double[] tempVector = singleGRA(matrixTransposed, refVector);
			matrixOutput[i] = tempVector;
		}
		
		AnalysisOutput ao = new AnalysisOutput();
		ao.setVector(vectorOutput);
		ao.setMatrix(matrixOutput);
		return ao;
	}
	
	public double[] singleGRA(double[][] arr, double[] ref) {
		int rowCount = arr.length;
		int colCount = arr[0].length;
		for (int i=0; i<ref.length; i++) {
			System.out.print(ref[i] + " | ");
		};
		System.out.print("\n");
		double min = Double.MAX_VALUE;
		double max = Double.MIN_VALUE;
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				arr[i][j] = Math.abs(ref[j] - arr[i][j]);
				if(arr[i][j] < min) {
					min = arr[i][j];
				} else if(arr[i][j] > max) {
					max = arr[i][j];
				}
			}
		}
		double[][] coef = new double[rowCount][colCount];
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				coef[i][j] = (min + rho * colCount) / (arr[i][j] + rho * colCount);
			}	
		}
		double[] score = new double[rowCount];
		for (int i=0; i < coef.length; i++) {
			double sumOfRow = 0;
			for (int j=0; j < coef[i].length; j++) {
				sumOfRow += coef[i][j];
			}
			score[i] = sumOfRow / colCount;
		}
		return score;
	}
	
	private double[][] transpose(double arr[][]){
	    int m = arr.length;
	    int n = arr[0].length;
	    double ret[][] = new double[n][m];

	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            ret[j][i] = arr[i][j];
	        }
	    }
	    return ret;
	}
	
	

}
