package ar.uba.fi.tdd.rulogic;

import ar.uba.fi.tdd.rulogic.model.KnowledgeBase;

import java.net.URL;
import java.util.Scanner;

/**
 * Console application.
 *
 */
public class App
{
	public static void main(String[] args) {
		KnowledgeBase knowledgeBase = new KnowledgeBase();
		URL url = knowledgeBase.getClass().getClassLoader().getResource("rules.db");
		try {
			knowledgeBase.parseDataBase(String.valueOf(url.toURI()).replaceAll("file:", ""));
		} catch (Exception e) {
			System.out.println("DB could not be initialized!");
		}
		System.out.println("I shall answer all your questions!");
		Scanner reader = new Scanner(System.in).useDelimiter("\n");
		boolean flag = true;
		while(flag) {
			System.out.println("Enter a query (q to quit): ");
			String queryInput = reader.next();
			String cleanQuery = queryInput.replaceAll("\\s+","");
			cleanQuery = cleanQuery.replaceAll("\\.","");
			if(cleanQuery.equals("q")) {
				flag = false;
			} else {
				String result = (knowledgeBase.answer(cleanQuery) ? "TRUE" : "FALSE");
				System.out.println("Result is: " + result);
			}
		}
		reader.close();
		System.out.println("App finished.");
	}
}
