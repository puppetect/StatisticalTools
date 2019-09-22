package com.ctck.analysis.analyser;

import com.ctck.analysis.model.AnalysisOutput;
import com.ctck.analysis.normalizer.Normalizer;
import com.ctck.analysis.normalizer.SumScaleNormalizer;

public class AHPAnalyzer implements Analyzer {
	private double[] RI = new double[] {0, 0, 0, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.46, 1.49, 1.52, 1.54, 1.56, 1.58, 1.59};
	public AnalysisOutput analyze(double[][] arr) {
		System.out.println("INPUTMATRIX");
		printMatrix(arr);
		Normalizer normalizer = new SumScaleNormalizer();
		double[][] normalizedMatrix = transpose(normalizer.normalize(transpose(arr)));
		System.out.println("NORMALIZEDMATRIX");
		printMatrix(normalizedMatrix);
		double[] sumOfRow = new double[normalizedMatrix.length];
		for (int i=0; i<normalizedMatrix.length; i++) {
			double sum = 0;
			for(int j=0; j<normalizedMatrix[0].length; j++) {
				sum += normalizedMatrix[i][j];
			}
			sumOfRow[i] = sum;
		}
		double sum = 0;
		for(int i=0; i<sumOfRow.length; i++) {
			sum += sumOfRow[i];
		}
		double[] W = new double[normalizedMatrix.length];
		for(int i=0; i<sumOfRow.length; i++) {
			W[i] = sumOfRow[i]/sum;
		}
		double[] BW = matrixProductVector(arr, W);
		System.out.println("W");
		printVector(W);
		System.out.println("BW");
		printVector(BW);
		// lambda = sum(BW/(n*W))
		double lambda = 0;
		int n = arr.length;
		for(int i=0; i<W.length; i++) {
			lambda += BW[i]/(n * W[i]);
		}
		System.out.println("lambda: " + lambda);
		Double CI = (lambda - n)/(n - 1);
		System.out.println("CI: " + CI);
		Double CR = CI/RI[n];
		System.out.println("CR: "+CR);
		AnalysisOutput ao = new AnalysisOutput();
		return ao;
	}
	
	private double[] matrixProductVector(double[][] A, double[] B) {

        int aRows = A.length;
        int aColumns = A[0].length;

        double[] C = new double[aColumns];

        for (int i = 0; i < aRows; i++) {
        	C[i] = 0;
            for (int j = 0; j < aColumns; j++) { 
                C[i] += A[i][j] * B[j];
            }
        }
        

        return C;
    }
	
	private double[][] matrixProductMatrix(double[][] A, double[][] B) {

        int aRows = A.length;
        int aColumns = A[0].length;
        int bRows = B.length;
        int bColumns = B[0].length;

        if (aColumns != bRows) {
            throw new IllegalArgumentException("A:Rows: " + aColumns + " did not match B:Columns " + bRows + ".");
        }

        double[][] C = new double[aRows][bColumns];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bColumns; j++) {
                C[i][j] = 0.00000;
            }
        }

        for (int i = 0; i < aRows; i++) { // aRow
            for (int j = 0; j < bColumns; j++) { // bColumn
                for (int k = 0; k < aColumns; k++) { // aColumn
                    C[i][j] += A[i][k] * B[k][j];
                }
            }
        }

        return C;
    }
	private void printMatrix(double[][] arr) {
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				System.out.print(String.format("%.2f", arr[i][j]) + " | ");
			}
			
			System.out.println("");
		}
		System.out.println("");
	}
	private void printVector(double[] vec) {
		for(int i =0; i<vec.length; i++) {
			System.out.print(String.format("%.2f", vec[i]) + " | ");
		}
		System.out.println("");
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
