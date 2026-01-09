package com.mcnz.lambda;

public class LearnJavaLambdaSyntax {
	
	public static void main(String args[]) {
		
		SingleArgument sa1 =  n -> System.out.print(n);
		sa1.foo("Let us all ");
		sa1.foo("learn lambda syntax.\n");

		SingleArgument sa2 =  (String n) -> System.out.print(n);
		sa2.foo("Java lambda syntax ");
		sa2.foo("isn't hard.\n");
		
		SingleArgument sa3 =  (String n) -> { System.out.print(n); };
		sa3.foo("You just need a few ");
		sa3.foo("good Java lambda examples.\n");
		
		SingleArgument sa4 =  (String n) -> {
		    n = n.replaceAll("\\s","");
		    System.out.print(n);
		};
		sa4.foo("This Java lambda example ");
		sa4.foo("will not print with whitespace.\n");
		
		MultipleArguments ma1 = (String p, int x) -> {
			   System.out.printf("%s wants %s slices of pie.\n", p, x);
		};
		ma1.bar("Cameron ", 3);
		ma1.bar("Callie", 4);
		
		MultipleArguments ma2 = 
				( p, x ) -> System.out.printf ( "%s wants %s slices.\n", p, x );
		ma2.bar("Brandyn", 1);
		ma2.bar("Carter", 2);
	
	}

}


interface SingleArgument {
	public void foo(String s);
}

interface MultipleArguments {
	public void bar(String s, int i);
}
