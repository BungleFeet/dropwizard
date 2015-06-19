package net.lazygun.experiment.helloworld.health;

import com.codahale.metrics.health.HealthCheck;

/**
 * TODO: Write Javadocs for this class.
 * Created: 18/06/2015 23:28
 *
 * @author Ewan
 */
public class TemplateHealthCheck extends HealthCheck {
    private final String template;

    public TemplateHealthCheck(String template) {
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
