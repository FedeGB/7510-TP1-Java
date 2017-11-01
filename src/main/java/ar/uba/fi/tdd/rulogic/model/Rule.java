package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Rule extends Element{

    String name;
    ArrayList<String> composition;

    Rule(String input) {
        this.name = this.extractRuleName(input);
        this.composition = extractRuleComposition(input);
    }

    public boolean evaluate(String input) {
        return false;
    }

    public String getName() {
        return this.name;
    }

    private String extractRuleName(String input) {
        String[] separated = input.split(":-");
        String ruleSide = separated[0];
        String ruleName = ruleSide.split("\\(")[0];
        return ruleName;
    }

    private ArrayList<String> extractRuleComposition(String input) {
        String[] separated = input.split(":-");
        String ruleSide = separated[0];
        String factSide = separated[1];
        return generateRuleList(ruleSide, factSide);
    }

    private ArrayList<String> generateRuleList(String ruleSide, String factSide) {
        ArrayList<String> ruleList = new ArrayList<String>();
        String[] variables = ruleSide.split("\\(")[1].replaceAll("\\)", "").split(",");
        String[] allFacts = factSide.split("\\) *,");
        List<String> processedFacts = Arrays
                .stream(allFacts)
                .map(element -> putTogether(element))
                .collect(Collectors.toList());
        ruleList.add(String.valueOf(variables.length));
        ruleList.addAll(Arrays.asList(variables));
        ruleList.addAll(processedFacts);
        return ruleList;
    }

    private <R> String putTogether(String element) {
        return element.replaceAll("( *\\( *| *\\) *| *, *)", "");
    }
}
