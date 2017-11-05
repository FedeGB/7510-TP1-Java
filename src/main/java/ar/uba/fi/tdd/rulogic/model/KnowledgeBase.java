package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class KnowledgeBase {

	public Map<String, List<Element> > elements = new HashMap<>();

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
				if(elements.containsKey(element.getName())) {
					List<Element> list = elements.get(element.getName());
					list.add(element);
					elements.put(element.getName(), list);
				} else {
					List<Element> list = new ArrayList<>();
					list.add(element);
					elements.put(element.getName(), list);
				}
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
		String cleanQuery = query.replaceAll("\\s+","");
		cleanQuery = cleanQuery.replaceAll("\\.","");
		if(this.isValidQuery(cleanQuery)) {
			String name = cleanQuery.split("\\(")[0];
			List<Element> elementList = elements.get(name);
			boolean evaluation = false;
			for(Element element : elementList) {
				evaluation = evaluation || element.evaluate(cleanQuery, this.elements);
				if(evaluation) break;
			}
			return evaluation;
		}
		return false;
	}


	private boolean isValidQuery(String query) {
		return Pattern.matches("^[^\\(]*\\([^)]*\\)$", query);
	}

}
