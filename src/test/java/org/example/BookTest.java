package org.example;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit5.ArquillianExtension;
import org.jboss.arquillian.test.api.ArquillianResource;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@ExtendWith(ArquillianExtension.class)
public class BookTest {

	@ArquillianResource URL url;

	@Deployment
	public static WebArchive createDeployment() {
		WebArchive archive = ShrinkWrap.create(WebArchive.class)
			.addPackage(AppConfig.class.getPackage())
			.addAsLibraries(Deployments.getWebDependencies())
			;
		return archive;
	}

	@Test
	public void testHelloServlet() throws MalformedURLException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI printUrl = new URL(url, "hello").toURI();
		System.out.println(printUrl);
		ResponseEntity<String> forEntity = restTemplate.getForEntity(printUrl, String.class);
		Assertions.assertEquals("<h1>Hello world!</h1>", forEntity.getBody());
	}

	@Test
	public void testPrint() throws MalformedURLException, URISyntaxException {
		RestTemplate restTemplate = new RestTemplate();
		URI printUrl = new URL(url, "print").toURI();
		System.out.println(printUrl);
		ResponseEntity<String> forEntity = restTemplate.getForEntity(printUrl, String.class);
		Assertions.assertEquals("print", forEntity.getBody());
	}

}
