package dev.TeamRedDragon.SmartHomeSimulator.Utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.TeamRedDragon.SmartHomeSimulator.Home.Home;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.io.InputStream;

@Service
public class JsonFileService {

    private final ResourceLoader resourceLoader;
    private final ObjectMapper objectMapper;

    public JsonFileService(ResourceLoader resourceLoader, ObjectMapper objectMapper) {
        this.resourceLoader = resourceLoader;
        this.objectMapper = objectMapper;
    }

    public Home readJsonFile() {
        try (InputStream inputStream = resourceLoader.getResource("classpath:data/simpleHome.json").getInputStream()) {
            return objectMapper.readValue(inputStream, Home.class);
        } catch (IOException e) {
            throw new RuntimeException("Error reading JSON file", e);
        }
    }
}
