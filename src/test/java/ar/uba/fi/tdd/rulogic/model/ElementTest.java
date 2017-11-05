package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ElementTest {
    @InjectMocks
    private ElementFactory factory;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
        this.factory = new ElementFactory();
    }

    @Test
    public void factoryInvalidInputTest() {
        Element result = this.factory.build("Invaid,query)");
        Assert.assertTrue( result == null);
    }

    @Test
    public void factoryValidFactInputTest() {
        Element result = this.factory.build("valid(fact,inserted)");
        Assert.assertTrue(result instanceof Fact);
    }

    @Test
    public void factoryValidRuleInputTest() {
        Element result = this.factory.build("valid(X,Y) :- fact1(Y) , fact2(Y,X)");
        Assert.assertTrue(result instanceof Rule);
    }

    @Test
    public void factNameTest() {
        Element result = this.factory.build("valid(fact,inserted)");
        Assert.assertTrue(result.getName().equals("valid"));
    }

    @Test
    public void factEvaluationTest() {
        Element result = this.factory.build("valid(fact,inserted)");
        Assert.assertFalse(result.evaluate("valid(fact,no)", null));
        Assert.assertTrue(result.evaluate("valid(fact,inserted)", null));
    }

    @Test
    public void ruleNameTest() {
        Element result = this.factory.build("valid(X,Y) :- fact1(Y) , fact2(Y,X)");
        Assert.assertTrue(result.getName().equals("valid"));
    }

    @Test
    public void ruleEvaluationTest() {
        Element result = this.factory.build("valid(X,Y) :- fact1(Y) , fact2(Y,X)");
        Map<String,List<Element>> elements = new HashMap<>();
        Element fact1 = this.factory.build("fact1(value2)");
        List<Element> fact1List = new ArrayList<>();
        fact1List.add(fact1);
        elements.put(fact1.getName(), fact1List);
        Element fact2 = this.factory.build("fact2(value2,value1)");
        List<Element> fact2List = new ArrayList<>();
        fact2List.add(fact2);
        elements.put(fact2.getName(), fact2List);
        Assert.assertTrue(result.evaluate("valid(value1,value2)", elements));

    }
}
