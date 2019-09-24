package com.ctck.analysis.normalizer;

public class MinMaxNormalizer implements Normalizer {

	public double[][] normalize(double[][] arr, int axis) {
		
		if(axis == 0) {
			for (int i=0; i<arr.length; i++) {
				Double min = 10.0;
				Double max = 0.0;
				for (int j=0; j<arr[0].length; j++) {
					max = Math.max(arr[i][j], max);
					min = Math.min(arr[i][j], min);
				}
				System.out.println("max="+max+",min="+min);
				for (int j=0; j<arr[i].length; j++) {
					arr[i][j] = (arr[i][j] - min) / (max - min);
				}
			}
		}
		if(axis == 1) {
			for (int i=0; i<arr[0].length; i++) {
				Double min = 10.0;
				Double max = 0.0;
				for (int j=0; j<arr.length; j++) {
					max = Math.max(arr[j][i], max);
					min = Math.min(arr[j][i], min);
				}
				System.out.println("max="+max+",min="+min);
				for (int j=0; j<arr.length; j++) {
					arr[j][i] = (arr[j][i] - min) / (max - min);
				}
			}
		}
		return arr;
	}

}
