package PolynomialCalculator.Polynomial;

import PolynomialCalculator.Control.PolynomialService;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Polynomial {
    private LinkedHashMap<Integer, Monomial> monomials;
    public Polynomial(){
        monomials = new LinkedHashMap<Integer, Monomial>();
    }
    public Polynomial(LinkedHashMap<Integer, Monomial> monomialsMap){
        this.monomials = monomialsMap;
    }

    public LinkedHashMap<Integer, Monomial> getMonomialsMap(){
        return this.monomials;
    }

    public void addMonomial(Monomial monomial) {
        int degree = monomial.getDegree();

        if (!monomials.containsKey(degree)){
            monomials.put(degree, monomial);
        }
        else{           //If a monomial with the same degree exists, we add them together
            Monomial existintMonomial = monomials.get(degree);
            Double existingMonomialCoefficient = existintMonomial.getCoefficient();
            Double newMonomialCoefficient = monomial.getCoefficient();
            Double combinedMonomialCoefficient = existingMonomialCoefficient + newMonomialCoefficient;

            existintMonomial.setCoefficient(combinedMonomialCoefficient);
        }
    }

    public void resetPolynomial(){
        monomials.clear();
    }

    public StringBuilder getPolynomialAsString(){
        TreeMap<Integer, Monomial> orderedMonomials = new TreeMap<>(Collections.reverseOrder());
        orderedMonomials.putAll(monomials);
        StringBuilder polynomial = new StringBuilder();
        for(Map.Entry<Integer, Monomial> entry : orderedMonomials.entrySet()){
            double coefficient = entry.getValue().getCoefficient();
            int degree = entry.getKey();        // Key of a monomial is its degree
            System.out.println("COefficient = " + coefficient);

            String currentMonomial;
            if(coefficient == (int)(coefficient)){
                int coeff = (int)(coefficient);
                currentMonomial = coeff + "X^" + degree + " + ";
            }else {
                currentMonomial = PolynomialService.doubleToFraction(coefficient, 2) + "X^" + degree + " + ";
            }
            polynomial.append(currentMonomial);
        }
        polynomial.delete(polynomial.length() - 3, polynomial.length());
        return polynomial;
    }

    public boolean isEqualTo(Polynomial polynomial){
        if(this.monomials.size() != polynomial.monomials.size()){
            System.out.println("FALSE HERE 0");
            return false;
        }else {
            for(Monomial monomial : this.monomials.values()){
                if(!polynomial.monomials.containsKey(monomial.getDegree())){
                    System.out.println("FALSE HERE1");
                    return false;
                }else{
                    double thisCoefficient = monomial.getCoefficient();
                    double otherCoefficient = polynomial.monomials.get(monomial.getDegree()).getCoefficient();
                    if(Math.abs(thisCoefficient - otherCoefficient) > 0.0001){
                        System.out.println("FALSE HERE 2");
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
