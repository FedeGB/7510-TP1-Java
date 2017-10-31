package ar.uba.fi.tdd.rulogic.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class KnowledgeBase {

	public void parseDataBase(String dbFile) throws Exception {
		ElementFactory factory = new ElementFactory();
		int lineCounter = 1;
		try {
			FileReader file = new FileReader(dbFile);
			BufferedReader buff = new BufferedReader(file);
			String line;
			while ((line = buff.readLine()) != null) {
				line.replaceAll("\\s+","");
				Element element = factory.build(line);
				if(element == null) {
					throw new Exception("Line: " + lineCounter + ", contains: " + line);
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
		return true;
	}

}
