package com.mcnz.lambda;

public class JavaLambdaSyntaxExample {

	public static void main(String[] args) {
		
		lambdaFun( n -> System.out.print(n) );
		
		lambdaFun( (String n) -> System.out.print(n) );
		
		lambdaFun( (String n) -> { System.out.print(n); } );
		
		lambdaFun( (String n) ->  { 
			n = n.replaceAll("\\s","");
			System.out.print(n); 
		} );
		
		moreLambdaFun( (String p, int x) ->  { 
			System.out.printf("%s1 wants %s2 slices of pie.\n", p, x);
		} ); 
		
		moreLambdaFun( (String p, int x) -> {
			   System.out.printf("%s1 wants %s2 slices of pie.\n", p, x);
		} );
		
		moreLambdaFun( (p, x) ->  System.out.printf("%s1 wants %s2 slices of pie.\n", p, x) ); 

	}
	
	public static void lambdaFun(SingleArgMethod sam) {
		sam.foo("Fun with ");
		sam.foo("Java lambda syntax.");
	}
	
	public static void moreLambdaFun(MultiArgMethod mam) {
		mam.bar("Cameron ", 3);
		mam.bar("Callie", 4);
	}
}


interface SingleArgMethod {
	public void foo(String s);
}

interface MultiArgMethod {
	public void bar(String s, int i);
}
