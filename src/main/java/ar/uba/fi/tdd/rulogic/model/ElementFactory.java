package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Pattern;

public class ElementFactory {

    public Element build(String input) {
        String clean = input.replaceAll("\\s+","");
        clean = clean.replaceAll("\\.", "");
        if(isFact(clean)) {
            Fact fact = new Fact(clean);
            return fact;
        } else if(isRule(clean)) {
            Rule rule = new Rule(clean);
            return rule;
        }
        return null;
    }

    private boolean isFact(String input) {
        boolean b = Pattern.matches("^[^\\(]*\\([^)]*\\)$", input);
        return b;
    }

    private boolean isRule(String input) {
        boolean b = Pattern.matches("^[^\\(]*\\([^)]*\\):-([^\\(]*\\([^)]*\\), *)*([^\\(]*\\([^)]*\\))$", input);
        return b;
    }
}
