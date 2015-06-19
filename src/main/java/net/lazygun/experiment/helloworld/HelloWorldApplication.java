package net.lazygun.experiment.helloworld;

import io.dropwizard.Application;
import io.dropwizard.java8.Java8Bundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import net.lazygun.experiment.helloworld.health.TemplateHealthCheck;
import net.lazygun.experiment.helloworld.resources.HelloWorldResource;

/**
 * TODO: Write Javadocs for this class.
 * Created: 18/06/2015 22:06
 *
 * @author Ewan
 */
public class HelloWorldApplication extends Application<HelloWorldConfiguration> {

    public static void main(String[] args) throws Exception {
        new HelloWorldApplication().run(args);
    }

    @Override
    public String getName() {
        return "hello-world";
    }

    @Override
    public void initialize(Bootstrap<HelloWorldConfiguration> bootstrap) {
        bootstrap.addBundle(new Java8Bundle());
        // TODO: Implement
    }

    @Override
    public void run(HelloWorldConfiguration helloWorldConfiguration, Environment environment) throws Exception {
        final HelloWorldResource resource = new HelloWorldResource(
                helloWorldConfiguration.getTemplate(),
                helloWorldConfiguration.getDefaultName()
        );
        environment.jersey().register(resource);

        final TemplateHealthCheck healthCheck
                = new TemplateHealthCheck(helloWorldConfiguration.getTemplate());
        environment.healthChecks().register("template", healthCheck);
    }
}
