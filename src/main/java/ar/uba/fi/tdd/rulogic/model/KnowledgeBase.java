package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Dictionary;
import java.util.regex.Pattern;

public class KnowledgeBase {

	Dictionary<String, Element> elements;

	public void parseDataBase(String dbFile) throws Exception {
		ElementFactory factory = new ElementFactory();
		int lineCounter = 1;
		try {
			FileReader file = new FileReader(dbFile);
			BufferedReader buff = new BufferedReader(file);
			String line;
			while ((line = buff.readLine()) != null) {
				Element element = factory.build(line);
				if(element == null) {
					throw new Exception("Line: " + lineCounter + ", contains: " + line);
				}
				elements.put(element.getName(), element);
				lineCounter++;
			}
		} catch (FileNotFoundException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} catch (Exception e) {
			throw e;
		}
	}

	public boolean answer(String query) {
		query.replaceAll("\\s+","");
		if(this.isValidQuery(query)) {
			String name = query.split("\\(")[0];
			Element element = elements.get(name);
			return element.evaluate(query);
		}
		return false;
	}


	private boolean isValidQuery(String query) {
		return Pattern.matches("^[^\\(]*\\([^)]*\\)$", query);
	}

}
