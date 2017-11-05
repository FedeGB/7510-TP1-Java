package ar.uba.fi.tdd.rulogic.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Rule extends Element{

    String name;
    ArrayList<String> composition;

    Rule(String input) {
        this.name = this.extractRuleName(input);
        this.composition = extractRuleComposition(input);
    }

    public boolean evaluate(String input, Map<String,List<Element>> knowledge) {
        String parsedQuery = input.replaceAll(" +", "");
        List<String> queryRuleValues = obtainValuesFromBrackets(parsedQuery);
        if(queryRuleValues.size() == Integer.parseInt(this.composition.get(0))) {
            int varsAmount = Integer.parseInt(this.composition.get(0));
            List<String> replacedFacts = new ArrayList<>();
            for(int itFacts = 1 + varsAmount; itFacts < this.composition.size(); itFacts++) {
                String replacedRuleFact = this.composition.get(itFacts);
                for(int itVars = 1; itVars <= varsAmount; itVars++) {
                    String varActual = this.composition.get(itVars);
                    String valueActual = queryRuleValues.get(itVars-1);
                    replacedRuleFact = replacedRuleFact.replaceAll(varActual, valueActual);
                }
                replacedFacts.add(replacedRuleFact);
            }
            boolean queryResult = true;
            for(String fact : replacedFacts) {
                String name = fact.split("-")[0];
                boolean inFactResult = false;
                if(!knowledge.containsKey(name)) {
                    queryResult = false;
                    break;
                } else {
                    List<Element> elementFacts = knowledge.get(name);
                    for(Element listed : elementFacts) {
                        inFactResult = inFactResult || listed.evaluate(fact.replaceAll("-",""), knowledge);
                    }
                }
                queryResult = queryResult && inFactResult;
            }
            return queryResult;
        } else {
            return false;
        }
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
        String separation = element.replaceAll(" *\\( *", "-");
        return separation.replaceAll("( *\\) *| *, *)", "");
    }

    private List<String> obtainValuesFromBrackets (String query) {
        String cleanQuery = query.replaceAll(" +", "");
        String varsSide = cleanQuery.split("\\(")[1];
        String parsedSide = varsSide.replace("\\)", "");
        String[] ruleVars = parsedSide.split(",");
        return Arrays.asList(ruleVars);
    }
}
