package PolynomialCalculator.Polynomial;

public class Monomial {
    private Double coefficient;
    private Integer degree;
    public Monomial(double coefficient, int degree){
        this.coefficient = coefficient;
        this.degree = degree;
    }

    public int getDegree(){
        return this.degree;
    }

    public Double getCoefficient(){
        return this.coefficient;
    }

    public void setCoefficient(Double coefficient){
        this.coefficient = coefficient;
    }

}
