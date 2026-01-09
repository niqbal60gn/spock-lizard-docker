package com.mcnz.lambda;

import java.util.*;

public class LambdaExample {

	public static void main(String[] args) {
		Integer[] numbers = {5, 12, 11, 7};
		Arrays.sort(numbers, new Comparator<Integer>() {
			
			public int compare(Integer a, Integer b) {
				return b - a;
			}
			
		});
		System.out.println(Arrays.toString(numbers));
	}
	
	public static void lambdasWithHats() {
		Integer[] numbers = {5, 12, 11, 7};
		Arrays.sort(numbers, (a, b) -> b-a);	
		System.out.println(Arrays.toString(numbers));
	}
	
	public static void menWithoutHats() {
		Integer[] numbers = {5, 12, 11, 7};
		Arrays.sort(numbers, new Comparator<Integer>() {
			
			public int compare(Integer a, Integer b) {
				return b - a;
			}

		});
		System.out.println(Arrays.toString(numbers));
	}
	
	public static void lammasWithHats() {

		Integer[] numbers = {5, 12, 11, 7};
		Comparator<Integer> myComparator = new MyComparator();
		Arrays.sort(numbers, myComparator);
		System.out.println(Arrays.toString(numbers));
	}
	
}

class MyComparator implements Comparator<Integer> {
	@Override
	public int compare(Integer a, Integer b) {
		return b - a;
	}
}
