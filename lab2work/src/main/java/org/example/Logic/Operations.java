package org.example.Logic;

import org.example.Model.MonomialDouble;
import org.example.Model.Polynomial;

import java.util.Map;

public class Operations {
    public Polynomial addPolinoms(Polynomial pol1, Polynomial pol2) {
        Polynomial pol3 = new Polynomial();
        for (Map.Entry<Integer, MonomialDouble> entry1 : pol1.getMonomialMap().entrySet()){

            MonomialDouble mon1 = entry1.getValue();
            int degree1 = entry1.getKey();

            MonomialDouble mon2 = pol2.getMonomialMap().getOrDefault(degree1, new MonomialDouble(0));
            MonomialDouble monAux = new MonomialDouble(0);

            monAux.setCoefficient(mon2.getCoefficient().doubleValue() + mon1.getCoefficient().doubleValue());
            monAux.setDegree(degree1);

            pol3.getMonomialMap().put(degree1, monAux);
        }

        for (Map.Entry<Integer, MonomialDouble> entry2 : pol2.getMonomialMap().entrySet()){
            MonomialDouble mon1 = entry2.getValue();
            int degree2 = entry2.getKey();
            if(!pol3.getMonomialMap().containsKey(degree2))
                pol3.addMonomial(mon1,degree2);
        }

        return pol3;
    }

    public Polynomial substractPolinoms(Polynomial pol1, Polynomial pol2) {
        Polynomial pol3 = new Polynomial();
        for (Map.Entry<Integer, MonomialDouble> entry1 : pol1.getMonomialMap().entrySet()){

            MonomialDouble mon1 = entry1.getValue();
            int degree1 = entry1.getKey();

            MonomialDouble mon2 = pol2.getMonomialMap().getOrDefault(degree1, new MonomialDouble(0));
            MonomialDouble monAux = new MonomialDouble(0);
            monAux.setCoefficient( mon1.getCoefficient().doubleValue() - mon2.getCoefficient().doubleValue());
            monAux.setDegree(degree1);
            pol3.getMonomialMap().put(degree1, monAux);
        }

        for (Map.Entry<Integer, MonomialDouble> entry2 : pol2.getMonomialMap().entrySet()){
            MonomialDouble mon1 = entry2.getValue();
            int degree2 = entry2.getKey();
            MonomialDouble monAux = new MonomialDouble(0);
            monAux.setCoefficient(- mon1.getCoefficient().doubleValue());
            if(!pol3.getMonomialMap().containsKey(degree2))
                pol3.addMonomial(monAux, degree2);
        }
        return pol3;
    }

    public Polynomial multiplyPolinoms(Polynomial pol1, Polynomial pol2){
        Polynomial pol3 = new Polynomial();
        for(Map.Entry<Integer, MonomialDouble> entry1 : pol1.getMonomialMap().entrySet()){
            for (Map.Entry<Integer, MonomialDouble> entry2 : pol2.getMonomialMap().entrySet()){

                MonomialDouble mon1 = entry1.getValue();
                MonomialDouble mon2 = entry2.getValue();
                int degree3 = entry1.getKey() + entry2.getKey();

                MonomialDouble monAux = new MonomialDouble(degree3);
                monAux.setCoefficient(mon1.getCoefficient().doubleValue() * mon2.getCoefficient().doubleValue());
                pol3.addToPolinom(monAux,pol3);
            }
        }
        return  pol3;
    }

    public Polynomial derivatePolynomial(Polynomial pol1){
        Polynomial pol2 = new Polynomial();
        for(Map.Entry<Integer, MonomialDouble> entry : pol1.getMonomialMap().entrySet()){
            int degree = entry.getKey();
            MonomialDouble mon1 = entry.getValue();
            MonomialDouble monAux = new MonomialDouble(0);
            if(degree == 0)
                continue;

            monAux.setCoefficient( mon1.getCoefficient().doubleValue() * degree);
            monAux.setDegree(degree - 1);
            pol2.getMonomialMap().put(degree - 1,monAux);
        }
        return pol2;
    }

    public Polynomial integratePolynomial(Polynomial pol1){
        Polynomial pol2 = new Polynomial();
        for(Map.Entry<Integer, MonomialDouble> entry : pol1.getMonomialMap().entrySet()){
            int degree = entry.getKey();
            MonomialDouble mon1 = entry.getValue();
            MonomialDouble monAux = new MonomialDouble(0);

            double coefficient = mon1.getCoefficient().doubleValue() / (degree + 1);
            monAux.setCoefficient(coefficient);
            monAux.setDegree(degree + 1);
            pol2.getMonomialMap().put(degree + 1, monAux);
        }
        return pol2;
    }

    public String divisionPolynoms(Polynomial pol1, Polynomial pol2) {
        Polynomial quotient = new Polynomial();
        Integer[] vectorQ = descendingPoynomArray(pol2);
        Integer[] vectorP = descendingPoynomArray(pol1);
        int biggestDegreeP = vectorP[0];
        while(vectorQ[0] < biggestDegreeP) {
            vectorP = descendingPoynomArray(pol1);
            biggestDegreeP = vectorP[0];

            MonomialDouble monAux = new MonomialDouble(0);
            monAux.setDegree(biggestDegreeP - vectorQ[0]);

            double coefP = pol1.getMonomialMap().getOrDefault(biggestDegreeP, new MonomialDouble(0)).getCoefficient().doubleValue();
            double coefQ = pol2.getMonomialMap().getOrDefault(vectorQ[0], new MonomialDouble(0)).getCoefficient().doubleValue();

            monAux.setCoefficient(coefP/coefQ);

            quotient.getMonomialMap().put(monAux.getDegree().intValue(), monAux);

            Polynomial polAux = new Polynomial();
            polAux.getMonomialMap().put(monAux.getDegree().intValue(), monAux);

            pol1 = substractPolinoms(pol1, multiplyPolinoms(pol2, polAux));

        }
        String s = "Quotient: " + quotient.toString() + "  Remainder: " + pol1.toString();
        return s;
    }

    public Integer[] descendingPoynomArray(Polynomial pol){
        Integer[] vector = new Integer[pol.getMonomialMap().size()];
        int k = 0;
        for(Map.Entry<Integer, MonomialDouble> entry : pol.getMonomialMap().entrySet()) {
            if (entry.getValue().getCoefficient().doubleValue() != 0) {
                vector[k] = entry.getKey();
                k++;
            }
        }

        Integer[] vectorAux = new Integer[k];

        for (int i = 0; i < k; i++) {
            vectorAux[i] = vector[k - 1 - i];
        }

        return vectorAux;
    }

}
