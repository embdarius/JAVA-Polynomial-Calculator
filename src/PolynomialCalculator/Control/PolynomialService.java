package PolynomialCalculator.Control;

import PolynomialCalculator.Polynomial.Monomial;
import PolynomialCalculator.Polynomial.Polynomial;
import PolynomialCalculator.GUI.PolynomialGUI;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class PolynomialService {
    Polynomial addOrSubstract(String operation, Polynomial polynomial1, Polynomial polynomial2){

            LinkedHashMap<Integer, Monomial> result = new LinkedHashMap<>();
            for(Map.Entry<Integer, Monomial> entry : polynomial1.getMonomialsMap().entrySet()){
                result.put(entry.getValue().getDegree(), entry.getValue());
            }
            for(Map.Entry<Integer, Monomial> entry : polynomial2.getMonomialsMap().entrySet()){
                if(!result.containsKey(entry.getKey())){
                    if(operation.equals("substract")){
                        entry.getValue().setCoefficient(entry.getValue().getCoefficient() * (-1));
                    }
                    result.put(entry.getValue().getDegree(), entry.getValue());
                }
                else{
                    Double entryCoefficient = entry.getValue().getCoefficient();
                    Double resultCoefficient = result.get(entry.getKey()).getCoefficient();
                    double newCoefficient = -1.0;
                    if(operation.equals("add")){
                        newCoefficient = entryCoefficient + resultCoefficient;
                    } else if(operation.equals("substract")){
                        newCoefficient = resultCoefficient - entryCoefficient;}
                    else{
                        System.out.println("INVALID ARGUMENT PROVIDED");
                    }
                    Monomial newMonomial = new Monomial(newCoefficient, entry.getValue().getDegree());
                    result.put(entry.getKey(), newMonomial);
                }
            }
            return new Polynomial(result);
    }

    private int getCoefficientFromStringMonomial(String monomial){
        int length = monomial.length();
        StringBuilder coefficient = new StringBuilder();
        for(int i = 0; i < length; i++){
            if(!Character.isDigit(monomial.charAt(i))){
                break;
            } else{
                coefficient.append(monomial.charAt(i));
            }
        }
        return Integer.parseInt(coefficient.toString());
    }

    private int getDegreeFromStringMonomial(String monomial){
        int length = monomial.length();
        if(length == 1){
            return 1;
        }
        if(length > 1 && monomial.charAt(1) != '^'){
            System.out.println("INVALID X POWER FORMAT -> ^ ");
            return -1;
        }
        StringBuilder degreeString = new StringBuilder();
        for(int i = 2 ; i < length ; i++){
            degreeString.append(monomial.charAt(i));
        }
        return Integer.parseInt(degreeString.toString());

    }

    private Polynomial CreatePolynomial(String[] arrayOfMonomials, Polynomial polynomial) throws PatternMatchingException {
        for(String monomial : arrayOfMonomials){
            int coefficient = -1;
            int degree = -1;
            char sign = '?';
            monomial = monomial.replaceAll("\\s+", "");
            if(monomial.isEmpty())
                throw new PatternMatchingException("MONOMIAL PROVIDED IN CreatePolynomial FUNCTION IS EMPTY");
            if(monomial.charAt(0) == '+') sign = '+';
            else if(monomial.charAt(0) == '-') sign = '-';
            monomial = monomial.substring(1);

            if(Character.isDigit(monomial.charAt(0))){
                coefficient = getCoefficientFromStringMonomial(monomial);
                monomial = monomial.substring(String.valueOf(coefficient).length());
                if(!monomial.isEmpty() && (monomial.charAt(0) == 'X' || monomial.charAt(0) == 'x')){
                    degree = getDegreeFromStringMonomial(monomial);
                }else if(monomial.isEmpty()){
                    degree = 0;
                }
            }else if(monomial.charAt(0) == 'X' || monomial.charAt(0) == 'x'){
                coefficient = 1;
                degree = getDegreeFromStringMonomial(monomial);
            }
            if(coefficient == -1 || degree == -1 || sign == '?'){
                throw new PatternMatchingException("INVALID MONOMIAL IN TEXT FIELD");
            }
            if(sign == '-') coefficient *= -1;
            Monomial currentMonomial = new Monomial(coefficient, degree);
            polynomial.addMonomial(currentMonomial);
        }
        return polynomial;
    }

    void setPolynomial(PolynomialGUI polynomialGUI, Polynomial polynomial, int field) throws PatternMatchingException {
        String polynomialText;
        if(field == 1){
            polynomialText = polynomialGUI.getPolynomial1FieldText();
        }else {
            polynomialText = polynomialGUI.getPolynomial2FieldText();
        }


        if(polynomialText.isBlank()){
            throw new PatternMatchingException("one or both polynomial text fields is blank, can't pattern match");
        }
        String[] arrayOfMonomials1 = polynomialText.split("(?=[-+])");
        if(arrayOfMonomials1[0].charAt(0) != '-' && arrayOfMonomials1[0].charAt(0) != '+'){
            arrayOfMonomials1[0] = "+" + arrayOfMonomials1[0];
        }
        polynomial = CreatePolynomial(arrayOfMonomials1, polynomial);
    }

    Polynomial multiplyPolynomials(Polynomial polynomial1, Polynomial polynomial2){

        Polynomial resultPolynomial = new Polynomial();

        for(Map.Entry<Integer, Monomial> entry1 : polynomial1.getMonomialsMap().entrySet()) {
            //System.out.println(entry1.getValue().getMonomialAsString() + "  " );
            for(Map.Entry<Integer, Monomial> entry2 : polynomial2.getMonomialsMap().entrySet()){
                double resultCoefficient = entry1.getValue().getCoefficient() * entry2.getValue().getCoefficient();
                int resultDegree = entry1.getValue().getDegree() + entry2.getValue().getDegree();
                resultPolynomial.addMonomial(new Monomial(resultCoefficient, resultDegree));
            }
        }
        return resultPolynomial;
    }

    Polynomial derivePolynomial(Polynomial polynomial){
        Polynomial resultPolynomial = new Polynomial();

        for(Map.Entry<Integer, Monomial> entry : polynomial.getMonomialsMap().entrySet()){
            Double coefficient = entry.getValue().getCoefficient();
            int degree = entry.getValue().getDegree();

            double resultCoefficient = coefficient * degree;
            int resultDegree = degree - 1;

            if(resultDegree >= 0){
                resultPolynomial.addMonomial(new Monomial(resultCoefficient, resultDegree));
            }

        }
        return resultPolynomial;
    }

    Polynomial integratePolynomial(Polynomial polynomial){
        Polynomial resultPolynomial = new Polynomial();

        for(Map.Entry<Integer, Monomial> entry : polynomial.getMonomialsMap().entrySet()){
            Double coefficient = entry.getValue().getCoefficient();
            int degree = entry.getValue().getDegree();

            coefficient /= (degree+1);
            resultPolynomial.addMonomial(new Monomial(coefficient, degree+1));
        }
        return resultPolynomial;
    }

    Polynomial dividePolynomials(Polynomial polynomial1, Polynomial polynomial2){
        Polynomial resultPolynomial = new Polynomial();
        TreeMap<Integer, Monomial> sortedMonomials1 = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Monomial> sortedMonomials2 = new TreeMap<>(Comparator.reverseOrder());
        TreeMap<Integer, Monomial> resultedMonomials = new TreeMap<>(Comparator.reverseOrder());
        sortedMonomials1.putAll(polynomial1.getMonomialsMap());
        sortedMonomials2.putAll(polynomial2.getMonomialsMap());

        Monomial dividentLeadterm = sortedMonomials1.get(sortedMonomials1.firstKey());          //x^4 + 3X^2 + 5
        Monomial divisorLeadTerm = sortedMonomials2.get(sortedMonomials2.firstKey());           // ( X + 2 )

        while(!sortedMonomials1.isEmpty() && sortedMonomials1.firstKey() >= sortedMonomials2.firstKey()){
            int newDegree = dividentLeadterm.getDegree() - divisorLeadTerm.getDegree();
            double newCoefficient = dividentLeadterm.getCoefficient() / divisorLeadTerm.getCoefficient();
            Monomial resultedMonomial = new Monomial(newCoefficient, newDegree);
            resultPolynomial.addMonomial(resultedMonomial);

            for(Monomial monomial : sortedMonomials2.values()){
                int degree = newDegree + monomial.getDegree();
                double coefficient = newCoefficient * monomial.getCoefficient()*(-1);
                resultedMonomials.put(degree, new Monomial(coefficient, degree));
            }
            for(Monomial dividentMonomial : sortedMonomials1.values()){
                if(resultedMonomials.containsKey(dividentMonomial.getDegree())){
                    double resultingCoeff = dividentMonomial.getCoefficient() + resultedMonomials.get(dividentMonomial.getDegree()).getCoefficient();
                    if(resultingCoeff > 0){
                        resultedMonomials.put(dividentMonomial.getDegree(), new Monomial(resultingCoeff, dividentMonomial.getDegree()));
                    }else{
                        resultedMonomials.remove(dividentMonomial.getDegree());
                    }

                }else{
                    resultedMonomials.put(dividentMonomial.getDegree(), new Monomial(dividentMonomial.getCoefficient(), dividentMonomial.getDegree()));
                }
            }
            if(resultedMonomials.isEmpty()) break;
            dividentLeadterm = resultedMonomials.get(resultedMonomials.firstKey());
            sortedMonomials1.clear();
            sortedMonomials1.putAll(resultedMonomials);
            resultedMonomials.clear();
        }
        return resultPolynomial;
    }

    private static int gcdByEuclidsAlgorithm(int n1, int n2) {
        if (n2 == 0) {
            return n1;
        }
        return gcdByEuclidsAlgorithm(n2, n1 % n2);
    }

    private static String simplify(int n1, int n2){
        while(gcdByEuclidsAlgorithm(n1, n2) > 1){
            int gcd = gcdByEuclidsAlgorithm(n1, n2);
            n1 = n1/gcd;
            n2 = n2/gcd;
        }
        return n1 + "/" + n2;
    }
    public static String doubleToFraction(double number, int largestRightOfDecimal){
        long signValue = 1;
        if (number < 0) {
            number = -number;
            signValue = -1;
        }
        final long SECOND_MULTIPLIER_MAX_VALUE = (long)Math.pow(10, largestRightOfDecimal - 1);
        final long FIRST_MULTIPLIER_MAX_VALUE = SECOND_MULTIPLIER_MAX_VALUE * 10L;
        final double ERROR_VALUE = Math.pow(10, -largestRightOfDecimal - 1);
        long firstMultiplierValue = 1;
        long secondMultiplierValue = 1;
        boolean isNotIntegerOrIrrational = false;
        long truncatedNumberValue = (long)number;

        double numeratorValue = signValue * number * FIRST_MULTIPLIER_MAX_VALUE;
        double denominatorValue = FIRST_MULTIPLIER_MAX_VALUE;

        double errorValue = number - truncatedNumberValue;
        while ((errorValue >= ERROR_VALUE) && (firstMultiplierValue <= FIRST_MULTIPLIER_MAX_VALUE)){
            secondMultiplierValue = 1;
            firstMultiplierValue *= 10;
            while ((secondMultiplierValue <= SECOND_MULTIPLIER_MAX_VALUE) && (secondMultiplierValue < firstMultiplierValue) ){
                double differenceValue = (number * firstMultiplierValue) - (number * secondMultiplierValue);
                truncatedNumberValue = (long)differenceValue;
                errorValue = differenceValue - truncatedNumberValue;
                if (errorValue < ERROR_VALUE){
                    isNotIntegerOrIrrational = true;
                    break;
                }
                secondMultiplierValue *= 10;
            }
        }
        if (isNotIntegerOrIrrational){
            numeratorValue = signValue * truncatedNumberValue;
            denominatorValue = firstMultiplierValue - secondMultiplierValue;
        }
        return simplify((int)numeratorValue, (int)denominatorValue);
    }
}
