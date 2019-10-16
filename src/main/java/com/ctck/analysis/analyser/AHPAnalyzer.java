package com.ctck.analysis.analyser;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.ctck.analysis.model.AnalysisOutput;
import com.ctck.analysis.normalizer.Normalizer;
import com.ctck.analysis.normalizer.SumScaleNormalizer;

public class AHPAnalyzer implements Analyzer {
	private double[] RI = new double[] {0, 0, 0, 0.58, 0.9, 1.12, 1.24, 1.32, 1.41, 1.46, 1.49, 1.52, 1.54, 1.56, 1.58, 1.59};
	public Map<String, Object> analyze(double[][]... arr) {
		//指标矩阵
		double[][] criteriaInput = arr[0];
		//方案矩阵组
		double[][][] alternativesInput = Arrays.copyOfRange(arr, 1, arr.length);
		
		//指标矩阵正则化
		double[][] normalizedCriteria = transpose(sumScale(transpose(criteriaInput)));
		printMatrix("NORMALIZEDMATRIX", normalizedCriteria);
		
		//指标特征向量
		double[] criteriaW = getEigenVector(normalizedCriteria);

		printVector("W", criteriaW);
		
		//指标最大特征根
		double maxEigen = 0;
		double[] BW = matrixProductVector(criteriaInput, criteriaW);
		int n = criteriaInput.length;
		for(int i=0; i<criteriaW.length; i++) {
			maxEigen += BW[i]/(n * criteriaW[i]);
		}
		
		//指标随机一致性比率
		Double CI = (maxEigen - n)/(n - 1);
		Double CR = CI/RI[n];
		
		printVector("BW", BW);
		System.out.println("maxEigen: " + maxEigen);
		System.out.println("CI: " + CI);
		System.out.println("CR: "+CR);
		
		//各指标的方案特征矩阵
		double[][] alternativesMatrix = new double[alternativesInput.length][alternativesInput[0].length];
		for (int i=0; i<alternativesInput.length; i++){
			double[][] normalizedAlternatives = transpose(sumScale(transpose(alternativesInput[i])));
			double[] alternativesW = getEigenVector(normalizedAlternatives);
			alternativesMatrix[i] = alternativesW;
		}
		
		double[][] scoreMatrix = transpose(alternativesMatrix);
		for(int i=0; i<scoreMatrix.length; i++){
			for(int j=0; j<scoreMatrix[0].length; j++){
				scoreMatrix[i][j] = scoreMatrix[i][j] * criteriaW[j];
			}
		}
		printMatrix("SCOREMATRIX", scoreMatrix);
		
		//各方案得分
		double [] scores = getSumOfRows(scoreMatrix);
		printVector("SCORES", scores);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CR", CR);
		map.put("W", criteriaW);
		map.put("scoreMatrix", transpose(scoreMatrix));
		map.put("scores", scores);
		return map;
	}
	
	private double[] getEigenVector(double[][] arr){
		double[] sumOfRow = getSumOfRows(arr);
		double sum = 0;
		for(int i=0; i<sumOfRow.length; i++) {
			sum += sumOfRow[i];
		}
		double[] eigenVector = new double[arr.length];
		for(int i=0; i<sumOfRow.length; i++) {
			eigenVector[i] = sumOfRow[i]/sum;
		}
		return eigenVector;
	}
	
	private double[] getSumOfRows(double[][] arr){
		double[] sumOfRow = new double[arr.length];
		for (int i=0; i<arr.length; i++) {
			double sum = 0;
			for(int j=0; j<arr[0].length; j++) {
				sum += arr[i][j];
			}
			sumOfRow[i] = sum;
		}
		return sumOfRow;
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
	
	private double[] vectorProductMatrix(double[] A, double[][] B) {

        int bRows = B.length;
        int bColumns = B[0].length;
        double[] C = new double[bColumns];

        for (int i = 0; i < bColumns; i++) {
        	C[i] = 0;
            for (int j = 0; j < bRows; j++) { 
                C[i] += B[j][i] * A[j];
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
	private void printMatrix(String name, double[][] arr) {
		System.out.println(name);
		for (int i=0; i < arr.length; i++) {
			for (int j=0; j < arr[0].length; j++) {
				System.out.print(String.format("%.2f", arr[i][j]) + " | ");
			}
			System.out.println("");
		}
		System.out.println("");
	}
	
	private void printVector(String name, double[] vec) {
		System.out.println(name);
		for(int i =0; i<vec.length; i++) {
			System.out.print(String.format("%.2f", vec[i]) + " | ");
		}
		System.out.println("");
	}
	
	public double[][] sumScale(double[][] arr) {
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
		return arr;
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
