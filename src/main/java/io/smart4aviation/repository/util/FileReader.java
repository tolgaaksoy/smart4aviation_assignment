package io.smart4aviation.repository.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.smart4aviation.model.entity.CargoEntity;
import io.smart4aviation.model.entity.FlightEntity;
import io.smart4aviation.model.enums.WeightUnit;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * Utility class for reading data from JSON files.
 */
public class FileReader {

    private static final String CARGO_ENTITY_FILE_PATH = "src/main/resources/cargoEntity.json";
    private static final String FLIGHT_ENTITY_FILE_PATH = "src/main/resources/flightEntity.json";

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addDeserializer(WeightUnit.class, new WeightUnitDeserializer());
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(module);
    }

    /**
     * Constructs a FileReader object. Private constructor to prevent instantiation.
     */
    private FileReader() {
        //throw new AssertionError("This class should not be instantiated");
    }

    private static class FileReaderHolder {
        private static final FileReader INSTANCE = new FileReader();
    }

    /**
     * Returns the instance of the FileReader.
     *
     * @return FileReader instance
     */
    public static FileReader getInstance() {
        return FileReaderHolder.INSTANCE;
    }

    /**
     * Reads the FlightEntity data from the JSON file.
     *
     * @return List of FlightEntity objects
     */
    public List<FlightEntity> readFlightEntityFromFile() {
        try {
            File file = new File(FLIGHT_ENTITY_FILE_PATH);
            TypeReference<List<FlightEntity>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Reads the CargoEntity data from the JSON file.
     *
     * @return List of CargoEntity objects
     */
    public List<CargoEntity> readCargoEntityFromFile() {
        try {
            File file = new File(CARGO_ENTITY_FILE_PATH);
            TypeReference<List<CargoEntity>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(file, typeReference);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    /**
     * Custom deserializer for the WeightUnit enum.
     */
    private static class WeightUnitDeserializer extends JsonDeserializer<WeightUnit> {
        @Override
        public WeightUnit deserialize(JsonParser parser, DeserializationContext context) throws IOException {
            ObjectCodec codec = parser.getCodec();
            JsonNode node = codec.readTree(parser);
            String value = node.asText().toUpperCase();
            return WeightUnit.valueOf(value);
        }
    }
}