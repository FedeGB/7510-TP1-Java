package ar.uba.fi.tdd.rulogic.model;

public class Fact extends Element{

    String name;
    String evaluation;

    Fact(String input) {
        this.name = input.split("\\(")[0];
        this.evaluation = input.replaceAll("( *\\( *| *\\) *| *, *)", "");
    }

    public boolean evaluate(String input) {
        String inputToEvaluate = input.replaceAll("( *\\( *| *\\) *| *, *)", "");
        return this.evaluation.equals(inputToEvaluate);
    }

    public String getName() {
        return this.name;
    }
}
