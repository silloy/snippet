package com.tarena.model;

/**
 * @author SuShaohua
 * @date 2016/8/23 15:33
 * @description
 */
public class Rational extends Number implements Comparable<Rational> {
    private long numberator = 0;
    private long denominator = 1;
    public Rational(){
        this(0, 1);
    }

    public Rational(long numberator, long denominator) {
        long gcd = gcd(numberator, denominator);
        this.numberator = ((denominator > 0) ? 1 : -1) * numberator / gcd;
        this.denominator = Math.abs(denominator) /gcd;
    }

    private static long gcd(long n, long d) {
        long n1 = Math.abs(n);
        long n2 = Math.abs(d);
        int gcd = 1;
        for (int k = 1; k <= n1 && k <= n2; k++ ){
            if (n1 % k == 0 && n2 % k == 0)
                gcd = k;
        }
        return gcd;
    }

    public long getNumberator() {
        return numberator;
    }

    public void setNumberator(long numberator) {
        this.numberator = numberator;
    }

    public long getDenominator() {
        return denominator;
    }

    public void setDenominator(long denominator) {
        this.denominator = denominator;
    }

    @Override
    public String toString() {
        if (denominator == 1)
            return numberator + "";
        else
            return numberator + "/" + denominator;
    }


    @Override
    public int hashCode() {
        int result = (int) (numberator ^ (numberator >>> 32));
        result = 31 * result + (int) (denominator ^ (denominator >>> 32));
        return result;
    }

    public Rational substract(Rational r){
        long n = numberator * r.getDenominator() - denominator * r.getNumberator();
        long d = denominator * r.getDenominator();
        return new Rational(n, d);
    }

    @Override
    public int compareTo(Rational o) {
        return 0;
    }

    @Override
    public int intValue() {
        return (int)doubleValue();
    }

    @Override
    public long longValue() {
        return (long)doubleValue();
    }

    @Override
    public float floatValue() {
        return (float)doubleValue();
    }

    @Override
    public double doubleValue() {
        return numberator * 1.0 / denominator;
    }
}
