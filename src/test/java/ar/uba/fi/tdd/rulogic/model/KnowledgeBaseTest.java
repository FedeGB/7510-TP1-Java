package ar.uba.fi.tdd.rulogic.model;

import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import java.net.URL;

public class KnowledgeBaseTest {

	@InjectMocks
	private KnowledgeBase knowledgeBase;

	@Before
	public void setUp() throws Exception {
		initMocks(this);
		URL url = this.getClass().getClassLoader().getResource("rules.db");
		this.knowledgeBase.parseDataBase(String.valueOf(url.toURI()).replaceAll("file:", ""));
	}

	@Test
	public void parseDBTest() {
		Assert.assertTrue(this.knowledgeBase.elements.containsKey("padre"));
		Assert.assertTrue(this.knowledgeBase.elements.get("padre").size() == 5);
		Assert.assertTrue(this.knowledgeBase.elements.get("tio").size() == 1);

	}

	@Test
	public void answerFactTest() {
		Assert.assertTrue(this.knowledgeBase.answer("varon (javier)."));
		Assert.assertTrue(this.knowledgeBase.answer("padre(hector, maria)."));
	}

}
