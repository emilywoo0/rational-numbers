package RationalNumber;

public class RationalNumber implements RationalNumberInterface {
	
	
	protected int numerator;
	protected int denominator;
	
	// -- constructs rational number of 0/1 [default]
	public RationalNumber() {
		
		this.numerator = 0;
		this.denominator = 1;
	}
	
	
	// -- constructs rational number of inputed numerator and denominator
	// -- throw exception if denominator is 0 or less
	public RationalNumber(int n, int d) throws IllegalArgumentException {
		
		// -- denominator must be greater than 0
		if (d < 1) {
			throw new IllegalArgumentException("Denominator must be greater than 0");
		}
		
		this.numerator = n;
		this.denominator = d;
	}
	
	
	// -- ??
	public RationalNumber(RationalNumberInterface rhs) {
		
		this.numerator = rhs.getNumerator();
		this.denominator = rhs.getDenominator();
	}
	
	
	// -- return numerator
	@Override
	public int getNumerator() {
		
		return this.numerator;
	}

	
	// -- return denominator
	@Override
	public int getDenominator() {
		
		return this.denominator;
	}
	
	
	
	// -- equals operation
	@Override
	public boolean equals(Object o) {
		
		// -- if o is a Rational Number (if o is of RationalNumber)
		// -- then cast it as RationalNumber
		if ( o instanceof RationalNumber ) {
			RationalNumber rn = (RationalNumber) o;
			
			
			// -- if the two rational numbers are already exactly equal (unsimplified)
			if ( (this.numerator == rn.numerator) && (this.denominator == rn.denominator) ) {
				return true;
			}
			
			
			
//			// -- else
//			// -- first, simplify "this"
//			// -- find GFC
//			for (int i=10; i>1; i--) {
//				if ((this.numerator % i == 0) && (this.denominator % i == 0)) {
//					this.numerator = (this.numerator / i);
//					this.denominator = (this.denominator / i);
//				}
//			}
//			
//			// -- second, simplify "rn"
//			// find GFC
//			for (int i=10; i>1; i--) {
//				if ((rn.numerator % i == 0) && (rn.denominator % i == 0)) {
//					rn.numerator = (rn.numerator / i);
//					rn.denominator = (rn.denominator / i);
//				}
//			}
//			
//			
//			// -- if the two rational numbers are equal when simplified
//			if ( (this.numerator == rn.numerator) && (this.denominator == rn.denominator) ) {
//				return true;
//			}
			
		}
	
		return false;
	}
	
	@Override
	public String toString() {
		// -- simplify fraction:
		
		
		// -- if "this" is a whole number return as whole
		if (this.numerator % this.denominator == 0) {
			return this.numerator / this.denominator + "";
		}
			
		// -- find GFC and simplify
		for (int i=10; i>1; i--) {
			if ((this.numerator % i == 0) && (this.denominator % i == 0)) {
				this.numerator = (this.numerator / i);
				this.denominator = (this.denominator / i);
			}
		}
				
		
		// -- negate numerator if necessary
		// -- / cancel double negative if necessary
		if (this.denominator < 0) {
			this.numerator =  (this.numerator * -1);
			this.denominator = (this.denominator * -1);
		}
				
		// -- simplify to mixed number
			  if ((this.numerator*-1) > this.denominator)	{
			   int mixed = this.numerator / this.denominator;
			   this.numerator = this.numerator - (this.denominator * (this.numerator / this.denominator));
			return mixed + " " + (this.numerator*-1) + "/" + this.denominator;
					    
	    }
		
			  
		return this.numerator + "/" + this.denominator;
	}
	
	
	// -- add two rational numbers
	@Override
	public RationalNumberInterface add(RationalNumberInterface rhs) {
		
		RationalNumber sum = new RationalNumber();
		
		// -- check if the two denominators are the same
		if (this.denominator == rhs.getDenominator()) {
			// -- if so, add numerators across
			sum.numerator = this.numerator + rhs.getNumerator();
			// -- set denominator to the already common denominator
			sum.denominator = this.denominator;
		}
		else {
			// -- calculate using the butterfly method:
			// -- cross multiply to calculate the numerator
			sum.numerator = (this.numerator * rhs.getDenominator()) + (rhs.getNumerator() * this.denominator);
			// -- multiply denominators to find a common denominator
			sum.denominator = this.denominator * rhs.getDenominator();
		}
		
		return sum;
	}
	
	

	// -- subtract two rational numbers
	@Override
	public RationalNumberInterface sub(RationalNumberInterface rhs) {
		
		RationalNumber diff = new RationalNumber();
		
		// -- check if the two denominators are the same
		if (this.denominator == rhs.getDenominator()) {
			// -- if so, subtract numerators across
			diff.numerator = this.numerator - rhs.getNumerator();
			// -- set denominator to the already common denominator
			diff.denominator = this.denominator;
		}
		else {
			// -- calculate using the butterfly method
			// -- cross multiply to calculate the numerator
			diff.numerator = (this.numerator * rhs.getDenominator()) - (rhs.getNumerator() * this.denominator);
			// -- multiply denominators to find a common denominator
			diff.denominator = this.denominator * rhs.getDenominator();
		}
		
		return diff;
	}
	

	
	// -- multiply two rational numbers
	@Override
	public RationalNumberInterface mult(RationalNumberInterface rhs) {
		
		RationalNumber prod = new RationalNumber();
		
		// -- multiply numerators across
		prod.numerator = this.numerator * rhs.getNumerator();
		// -- multiply denominators across
		prod.denominator = this.denominator * rhs.getDenominator();
		
		
		return prod;
	}
	

	
	// -- divide two rational numbers
	@Override
	public RationalNumberInterface div(RationalNumberInterface rhs) throws ArithmeticException {
		
		// -- denominators must be greater than 0
		if ( (this.denominator == 0) || (rhs.getDenominator() == 0) ) {
			throw new ArithmeticException("Denominator must be greater than 0");
		}
		
		RationalNumber quo = new RationalNumber();
		
		try {
			// -- multiply "this." by the reciprocal of "rhs"
			quo.numerator = this.numerator * rhs.getDenominator();
			quo.denominator = this.denominator * rhs.getNumerator();
		}
		catch (ArithmeticException e){
			System.out.println("Division invalid");
		}
				
		return quo;
		
	}
	

	
	// -- square root of one positive rational number
	@Override
	public double sqrt() throws ArithmeticException {

		double sqrt = Math.sqrt((double) this.numerator / (double) this.denominator);
		
		if (sqrt < 0) {
			throw new ArithmeticException("Square root input must be positive");
		}
		
		return sqrt;	
	}

}
