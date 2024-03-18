package org.example.Model;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Polynomial {
    private Map<Integer, MonomialDouble> polMap;

    public Polynomial() {
        this.polMap = new HashMap<>();
    }

    public void addMonomial(MonomialDouble monomial, int degree){
        this.polMap.put(degree, monomial);
    }

    public Map<Integer, MonomialDouble> getMonomialMap() {
        return polMap;
    }

    public Polynomial parsing(String input) {
        Polynomial polynomial = new Polynomial();

        String regex = "([-+]?\\d*x?\\^?\\d*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        String sign = "+";

        while (matcher.find()) {
            String match = matcher.group().trim();
            if (!match.isEmpty()) {
                if (match.charAt(0) == '+') {
                    sign = "+";
                } else if (match.charAt(0) == '-') {
                    sign = "-";
                }

                MonomialDouble monomial = monomialParse(match, sign);
                addToPolinom(monomial, polynomial);
            }
        }

        return polynomial;
    }

    public MonomialDouble monomialParse(String monomialString, String sign){

        MonomialDouble monomialAux = new MonomialDouble(0);

        String[] parts = monomialString.split("x\\^", 2);
        String coefficient = parts[0].trim();

        //calcul coeficient
        if(coefficient.length() < 2 || coefficient.length() == 2 && coefficient.charAt(1) == 'x') {
            if (sign.equals("-"))
                monomialAux.setCoefficient(-1);
            else
                monomialAux.setCoefficient(1);
        }
        else{
            int numar = Integer.parseInt(coefficient);
            monomialAux.setCoefficient(numar);
        }

        if(coefficient.length() == 2 && coefficient.charAt(1) == 'x')
            monomialAux.setDegree(1);

        if(parts.length > 1 && !parts[1].isEmpty()) {
            int numar = Integer.parseInt(parts[1]);
            monomialAux.setDegree(numar);
        }
        return monomialAux;
    }

    public void addToPolinom(MonomialDouble monomial, Polynomial polynom){
        if(polynom.getMonomialMap().containsKey(monomial.getDegree().intValue())){
            MonomialDouble actualMonomial = (MonomialDouble) polynom.getMonomialMap().get(monomial.getDegree().intValue());
            actualMonomial.setCoefficient(actualMonomial.getCoefficient().doubleValue() + monomial.getCoefficient().doubleValue());
        }
        else{
            polynom.addMonomial(monomial, monomial.getDegree().intValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for(Map.Entry<Integer, MonomialDouble> entry : polMap.entrySet()){
            int degree = entry.getKey();
            double coef = entry.getValue().getCoefficient().doubleValue();
            String aux = new String("");

            if(coef == 0)
                continue;
            if(coef == 1 && degree != 0)
                aux += "+";
            else
                if (coef == -1 && degree != 0)
                    aux += "-";
                else {
                if (coef < 0)
                    aux += String.format("%.1f", coef);
                else
                    aux += "+" + String.format("%.1f", coef);
                }
            if(degree > 1)
                aux += "x^" + String.format("%d", degree);
            else
                if(degree == 1)
                    aux += "x";

            str.insert(0,aux);
        }

        return str.toString();
    }
}
