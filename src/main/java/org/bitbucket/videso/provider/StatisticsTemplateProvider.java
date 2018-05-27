package org.bitbucket.videso.provider;

import com.google.inject.Provider;
import com.google.inject.Singleton;
import lombok.Setter;
import org.bitbucket.videso.model.StatisticsTemplate;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Singleton
public class StatisticsTemplateProvider implements Provider<StatisticsTemplate> {

    private static final String TEMPLATE_FILE = "/stats_template.txt";

    @Setter
    private String templateFile = TEMPLATE_FILE;

    @Override
    public StatisticsTemplate get() {
        return loadFromFile();
    }

    private StatisticsTemplate loadFromFile() {
        StringBuilder templateBuilder = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(templateFile)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                templateBuilder.append(line).append("\n");
            }
        } catch (Exception e) {
            throw new RuntimeException(templateFile + " not found");
        }

        return new StatisticsTemplate(templateBuilder.toString());
    }
}
