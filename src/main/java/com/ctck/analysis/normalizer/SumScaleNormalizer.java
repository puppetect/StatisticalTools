package com.ctck.analysis.normalizer;

public class SumScaleNormalizer implements Normalizer{

	public double[][] normalize(double[][] arr, int axis) {
		if(axis == 0) {
			double[] sumOfRows = new double[arr.length];
			for(int i=0; i<arr.length; i++) {
				double sum = 0;
				for (int j=0; j<arr[0].length; j++) {
					sum += arr[i][j];
				}
				sumOfRows[i] = sum;
			}
			for(int i=0; i<arr.length; i++) {
				for(int j=0; j<arr[0].length; j++) {
					arr[i][j] = arr[i][j] / sumOfRows[i];
				}
			}
		} else if(axis == 1) {
			double[] sumOfCols = new double[arr[0].length];
			for(int i=0; i<arr[0].length; i++) {
				double sum = 0;
				for (int j=0; j<arr.length; j++) {
					sum += arr[j][i];
				}
				sumOfCols[i] = sum;
			}
			for(int i=0; i<arr[0].length; i++) {
				for(int j=0; j<arr.length; j++) {
					arr[j][i] = arr[j][i] / sumOfCols[i];
				}
			}
		}
		return arr;
	}

}
