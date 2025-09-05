package Helper;

import Exeptions.DivByZeroExeption;

public class Rational {
    private int num;
    private int den;

    public Rational(int num, int den){
        if (den == 0) throw new DivByZeroExeption();
        int div = gcd(Math.abs(num), Math.abs(den));
        this.num = num/div;
        this.den = den/div;
        if (den < 0) {
            this.num = -this.num;
            this.den = -this.den;
        }
    }

    private static int gcd(int a, int b){
        return b == 0 ? a : gcd(b,a % b);
    }
}
