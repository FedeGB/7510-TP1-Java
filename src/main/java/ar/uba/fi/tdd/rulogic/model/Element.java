package ar.uba.fi.tdd.rulogic.model;

import java.lang.reflect.Array;

abstract public class Element {

    public abstract void Element(String name, Array composition);

    public abstract boolean evaluate(String input);

}
