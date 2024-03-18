package org.example.Model;

public class MonomialDouble extends Monomial{
    private double coefficient = 0;

    public MonomialDouble(int degree) {
        super(degree);
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    @Override
    public Number getCoefficient() {
        return coefficient;
    }
}
