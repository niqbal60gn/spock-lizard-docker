package com.example.plugin.echo_maven_plugin;

import java.io.File;

import org.apache.maven.plugin.testing.AbstractMojoTestCase;

public class MyMojoTest extends AbstractMojoTestCase {
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
 
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }
 
    public void testMessage() throws Exception {
        File pom = getTestFile("src/test/resources/unit/echo-mojo/pom.xml");
        assertNotNull(pom);
        assertTrue(pom.exists());
        MyMojo myMojo = (MyMojo) lookupMojo("echo", pom);
        assertNotNull(myMojo);
        myMojo.execute();
    }

}
