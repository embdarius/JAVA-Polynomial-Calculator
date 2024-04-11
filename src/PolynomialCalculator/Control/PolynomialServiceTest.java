package PolynomialCalculator.Control;

import PolynomialCalculator.Polynomial.Monomial;
import PolynomialCalculator.Polynomial.Polynomial;
import org.junit.jupiter.api.Test;

class PolynomialServiceTest {
    PolynomialService polynomialService = new PolynomialService();

    @Test
    void addOrSubstract() {
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        polynomial1.addMonomial(new Monomial(3, 2));
        polynomial1.addMonomial(new Monomial(5,3));
        polynomial2.addMonomial(new Monomial(8, 2));
        polynomial2.addMonomial(new Monomial(-3, 3));

        Polynomial result = new Polynomial();
        result.addMonomial(new Monomial(11, 2));
        result.addMonomial(new Monomial(2, 3));
        assert result.isEqualTo(polynomialService.addOrSubstract("add", polynomial1, polynomial2));
        result.resetPolynomial();
        result.addMonomial(new Monomial(-5, 2));
        result.addMonomial(new Monomial(8, 3));
        assert result.isEqualTo(polynomialService.addOrSubstract("substract", polynomial1, polynomial2));
    }

    @Test
    void multiplyPolynomials() {
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();
        polynomial1.addMonomial(new Monomial(5, 3));
        polynomial1.addMonomial(new Monomial(2,2));
        polynomial2.addMonomial(new Monomial(7,8));
        polynomial2.addMonomial(new Monomial(4,1));

        Polynomial result = new Polynomial();
        result.addMonomial(new Monomial(35,11));
        result.addMonomial(new Monomial(20,4));
        result.addMonomial(new Monomial(14,10));
        result.addMonomial(new Monomial(8,3));
        assert result.isEqualTo(polynomialService.multiplyPolynomials(polynomial1,polynomial2));
        polynomial1.resetPolynomial();
        polynomial2.resetPolynomial();
        result.resetPolynomial();

        polynomial1.addMonomial(new Monomial(7,1));
        polynomial2.addMonomial(new Monomial(3,8));
        polynomial2.addMonomial(new Monomial(100, 16));
        polynomial2.addMonomial(new Monomial(27,0));
        result.addMonomial(new Monomial(21, 9));
        result.addMonomial(new Monomial(700, 17));
        result.addMonomial(new Monomial(189,1));

        assert result.isEqualTo(polynomialService.multiplyPolynomials(polynomial1, polynomial2));
    }

    @Test
    void derivePolynomial() {
        Polynomial polynomial = new Polynomial();
        polynomial.addMonomial(new Monomial(5,3));
        polynomial.addMonomial(new Monomial(7, 8));
        Polynomial result = new Polynomial();
        result.addMonomial(new Monomial(15, 2));
        result.addMonomial(new Monomial(56, 7));
        assert result.isEqualTo(polynomialService.derivePolynomial(polynomial));

        polynomial.resetPolynomial();
        result.resetPolynomial();

        polynomial.addMonomial(new Monomial(200, 6));
        polynomial.addMonomial(new Monomial(15,0));
        result.addMonomial(new Monomial(1200, 5));

        assert result.isEqualTo(polynomialService.derivePolynomial(polynomial));
    }

    @Test
    void integratePolynomial() {
        Polynomial polynomial = new Polynomial();
        polynomial.addMonomial(new Monomial(5,3));
        polynomial.addMonomial(new Monomial(7, 8));
        Polynomial result = new Polynomial();
        result.addMonomial(new Monomial(5/4d, 4));
        result.addMonomial(new Monomial(7/9d, 9));
        assert result.isEqualTo(polynomialService.integratePolynomial(polynomial));

        polynomial.resetPolynomial();
        result.resetPolynomial();

        polynomial.addMonomial(new Monomial(200, 6));
        polynomial.addMonomial(new Monomial(15,0));
        result.addMonomial(new Monomial(200/7d, 7));
        result.addMonomial(new Monomial(15, 1));

        System.out.println(result.getPolynomialAsString());
        System.out.println(polynomialService.integratePolynomial(polynomial).getPolynomialAsString());
        assert result.isEqualTo(polynomialService.integratePolynomial(polynomial));
    }

    @Test
    void dividePolynomials() {
        Polynomial polynomial1 = new Polynomial();
        Polynomial polynomial2 = new Polynomial();

        polynomial1.addMonomial(new Monomial(2,3));
        polynomial1.addMonomial(new Monomial(8,2));
        polynomial1.addMonomial(new Monomial(-6,1));
        polynomial1.addMonomial(new Monomial(10,0));

        polynomial2.addMonomial(new Monomial(1,1));
        polynomial2.addMonomial(new Monomial(-2, 0));

        Polynomial result = new Polynomial();
        result.addMonomial(new Monomial(2, 2));
        result.addMonomial(new Monomial(12, 1));
        result.addMonomial(new Monomial(18, 0));

        assert result.isEqualTo(polynomialService.dividePolynomials(polynomial1,polynomial2));
        result.resetPolynomial();
        polynomial1.resetPolynomial();
        polynomial2.resetPolynomial();

        polynomial1.addMonomial(new Monomial(1, 2));
        polynomial1.addMonomial(new Monomial(5, 1));
        polynomial1.addMonomial(new Monomial(6, 0));
        polynomial2.addMonomial(new Monomial(1,1));
        polynomial2.addMonomial(new Monomial(2,0));

        result.addMonomial(new Monomial(1,1));
        result.addMonomial(new Monomial(3,0));

        assert result.isEqualTo(polynomialService.dividePolynomials(polynomial1,polynomial2));

        result.resetPolynomial();
        polynomial1.resetPolynomial();
        polynomial2.resetPolynomial();

        polynomial1.addMonomial(new Monomial(6,4));
        polynomial1.addMonomial(new Monomial(-9,2));
        polynomial1.addMonomial(new Monomial(18, 0));
        polynomial2.addMonomial(new Monomial(1,1));
        polynomial2.addMonomial(new Monomial(-3, 0));

        result.addMonomial(new Monomial(6,3));
        result.addMonomial(new Monomial(18,2));
        result.addMonomial(new Monomial(45, 1));
        result.addMonomial(new Monomial(135, 0));

        assert result.isEqualTo(polynomialService.dividePolynomials(polynomial1,polynomial2));
    }
}