package com.ctck.analysis.analyser;

public class AnalyzerHelper {
	public double[][] transpose(double arr[][]){
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
	public double[][] copy(double arr[][]){
	    int m = arr.length;
	    int n = arr[0].length;
	    double ret[][] = new double[m][n];

	    for (int i = 0; i < m; i++) {
	        for (int j = 0; j < n; j++) {
	            ret[i][j] = arr[i][j];
	        }
	    }
	    return ret;
	}
	public void printMatrix(String name, double[][] arr) {
		System.out.println(name);
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				System.out.print(String.format("%.2f", arr[i][j]) + " | ");
			}
			
			System.out.println("");
		}
		System.out.println("");
	}
	public void printVector(String name, double[] vec) {
		System.out.println(name);
		for(int i =0; i<vec.length; i++) {
			System.out.print(String.format("%.2f", vec[i]) + " | ");
		}
		System.out.println("");
	}
}
