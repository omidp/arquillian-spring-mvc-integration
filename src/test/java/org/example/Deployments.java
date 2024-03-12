package org.example;

import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import java.io.File;

public class Deployments {

	public static File[] getWebDependencies() {
		return resolveArtifact("org.springframework:spring-webmvc",
			"org.springframework:spring-tx",
			"org.springframework:spring-orm",
			"org.springframework:spring-context",
			"org.springframework:spring-webmvc",
			"org.springframework:spring-web");
	}

	public static File[] resolveArtifact(String... artifacts) {
		return Maven.resolver().loadPomFromFile("pom.xml").resolve(artifacts).withTransitivity().asFile();

	}

}
