package org.example.Model;

public abstract class Monomial {
    private int degree;

    public Monomial(int degree) {
        this.degree = degree;
    }

    public abstract Number getCoefficient();
    public Number getDegree(){
        return degree;
    }

    public void setDegree(int degree) {
        this.degree = degree;
    }
}
