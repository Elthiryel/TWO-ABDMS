package pl.edu.agh.two.abdms.gui.controller;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterables;
import org.apache.commons.lang3.StringUtils;
import pl.edu.agh.two.abdms.gui.exceptions.ValidationException;

import java.util.LinkedList;
import java.util.List;

public class ConfigurationsController {
    private final static ConfigurationsController instance = new ConfigurationsController();

    private List<Configuration> configurations = new LinkedList<Configuration>();

    private ConfigurationsController() {
    }

    public static ConfigurationsController getInstance() {
        return instance;
    }

    public synchronized void addConfiguration(Configuration configuration) throws ValidationException {
        validate(configuration);

        configurations.add(configuration);
    }

    private void validate(final Configuration configuration) throws ValidationException {
        if (configuration.getFile() == null) {
            throw new ValidationException("No file selected.");
        }
        if (StringUtils.isBlank(configuration.getName())) {
            throw new ValidationException("Type configuration name.");
        }
        boolean nameNotUnique = Iterables.any(configurations, new Predicate<Configuration>() {
            @Override
            public boolean apply(Configuration c) {
                return c.getName().equals(configuration.getName());
            }
        });
        if (nameNotUnique) {
            throw new ValidationException("Configuration name is not unique.");
        }
    }

    public List<Configuration> getConfigurations() {
        return configurations;
    }
}
