package com.ctck.analysis.normalizer;

public class SumScaleNormalizer implements Normalizer {

	public double[][] normalize(double[][] arr) {
		double[] sumOfRows = new double[arr.length];
		for(int i=0; i<arr.length; i++) {
			double sum = 0;
			for (int j=0; j<arr[0].length; j++) {
				sum += arr[i][j];
			}
			sumOfRows[i] = sum;
		}
		System.out.println("");
		for(int i=0; i<arr.length; i++) {
			for(int j=0; j<arr[0].length; j++) {
				arr[i][j] = arr[i][j] / sumOfRows[i];
			}
		}
		return arr;
	}

}
