package PolynomialCalculator.Control;

import PolynomialCalculator.GUI.PolynomialGUI;
import PolynomialCalculator.Polynomial.Polynomial;

import javax.swing.*;
public class PolynomialController {
    private static PolynomialGUI polynomialGUI;
    private Polynomial polynomial1;
    private Polynomial polynomial2;

    public PolynomialController(){
        polynomial1 = new Polynomial();
        polynomial2 = new Polynomial();

        SwingUtilities.invokeLater(() -> {
            if(polynomialGUI == null){
                polynomialGUI = new PolynomialGUI(PolynomialController.this);
                polynomialGUI.setVisible(true);
            }
        });
    }

    private void setPolynomials(String which) throws PatternMatchingException {
        if(which.equals("both")){
            polynomialService.setPolynomial(polynomialGUI, polynomial1, 1);
            polynomialService.setPolynomial(polynomialGUI, polynomial2,2);
        }else if(which.equals("first")){
            polynomialService.setPolynomial(polynomialGUI, polynomial1,1);
        }else{
            polynomialService.setPolynomial(polynomialGUI,polynomial2,2);
        }

    }

    private void clearPolynomials(){
        polynomial1.resetPolynomial();
        polynomial2.resetPolynomial();
    }

    public void substractPolynomials() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("both");

        showResult(polynomialService.addOrSubstract("substract", polynomial1,polynomial2).getPolynomialAsString().toString());

    }

    PolynomialService polynomialService = new PolynomialService();
    public void addPolynomials() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("both");

        showResult(polynomialService.addOrSubstract("add", polynomial1,polynomial2).getPolynomialAsString().toString());
    }



    public void multiplyPolynomials() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("both");

        showResult(polynomialService.multiplyPolynomials(polynomial1,polynomial2).getPolynomialAsString().toString());
    }

    public void derivePolynomial() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("first");

        showResult(polynomialService.derivePolynomial(polynomial1).getPolynomialAsString().toString());
    }

    public void integratePolynomial() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("first");

        showResult(polynomialService.integratePolynomial(polynomial1).getPolynomialAsString().toString());

    }

    public void dividePolynomials() throws PatternMatchingException {
        clearPolynomials();
        setPolynomials("both");

        showResult(polynomialService.dividePolynomials(polynomial1,polynomial2).getPolynomialAsString().toString());
    }

    private void showResult(String result){
        polynomialGUI.updateResultField(result);
    }

    public static void main(String[] args) {
        new PolynomialController();
    }

}
