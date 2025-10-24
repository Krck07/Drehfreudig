package Helper;

import Exeptions.DivByZeroException;

public class Rational {
    private int num;
    private int den;

    public Rational(int num, int den){
        if (den == 0) throw new DivByZeroException();
        int div = gcd(Math.abs(num), Math.abs(den));
        this.num = num/div;
        this.den = den/div;
        if (this.den < 0) {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    private static int gcd(int a, int b){
        return b == 0 ? a : gcd(b,a % b);
    }

    public static Rational multiply(Rational a, Rational b){
        return new Rational(a.num*b.num,a.den*b.den);
    }

    public Rational multiply(Rational other) {
        return new Rational(this.num * other.num, this.den * other.den);
    }

    public int multiply(int i) {
        return (i*this.num)/this.den;
    }

    public Rational divide(int i){
        if (i == 0) throw new DivByZeroException();
        return new Rational(this.num, this.den*i);
    }

    public static boolean equals(Rational a, Rational b) {
        if (a == null || b == null) return false;
        if (a == b) return true;
        return a.equals(b);
    }

    public double getDecimal(){
        return ((double) num )/den;
    }

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Rational rational)) return false;

        return num == rational.num && den == rational.den;
    }

    @Override
    public String toString() {
        return den == 1 ? String.valueOf(num) : num + "/" + den;
    }
}
