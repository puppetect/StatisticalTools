package com.ctck.analysis.normalizer;

public class MinMaxNormalizer implements Normalizer {

	public double[][] normalize(double[][] arr) {
		Double min = Double.MAX_VALUE;
		Double max = Double.MIN_VALUE;
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[i].length; j++) {
				max = Math.max(arr[i][j], max);
				min = Math.min(arr[i][j], min);
			}
		}
		for (int i=0; i<arr.length; i++) {
			for (int j=0; j<arr[i].length; j++) {
				arr[i][j] = (arr[i][j] - min) / (max - min);
			}
		}
		return arr;
	}

}
