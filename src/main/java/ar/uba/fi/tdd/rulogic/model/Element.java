package ar.uba.fi.tdd.rulogic.model;

import java.util.List;
import java.util.Map;

abstract public class Element {

    public abstract boolean evaluate(String input, Map<String,List<Element>> knowledge);

    public abstract String getName();

}