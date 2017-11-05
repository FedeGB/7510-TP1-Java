package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.Map;

public class Fact extends Element{

    String name;
    String evaluation;

    Fact(String input) {
        this.name = input.split("\\(")[0];
        this.evaluation = input.replaceAll("( *\\( *| *\\) *| *, *)", "");
    }

    public boolean evaluate(String input, Map<String,List<Element>> knowledge) {
        String inputToEvaluate = input.replaceAll("( *\\( *| *\\) *| *, *)", "");
        return this.evaluation.equals(inputToEvaluate);
    }

    public String getName() {
        return this.name;
    }
}
