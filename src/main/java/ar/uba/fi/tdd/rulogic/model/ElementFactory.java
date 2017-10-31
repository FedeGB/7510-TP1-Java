package ar.uba.fi.tdd.rulogic.model;

import java.util.regex.Pattern;

public class ElementFactory {

    public Element build(String input) {
        if(isFact(input)) {
            Fact fact = new Fact();
            return fact;
        } else if(isRule(input)) {
            Rule rule = new Rule();
            return rule;
        }
        return null;
    }

    private boolean isFact(String input) {
        boolean b = Pattern.matches("^[^\\(]*\\([^)]*\\)$", input);
        return b;
    }

    private boolean isRule(String input) {
        boolean b = Pattern.matches("^[^\\(]*\\([^)]*\\) :- ([^\\(]*\\([^)]*\\), *)*([^\\(]*\\([^)]*\\))$", input);
        return b;
    }
}
