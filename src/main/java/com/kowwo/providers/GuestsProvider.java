package com.kowwo.providers;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class GuestsProvider {

    public static final String GUESTS_FILE_PATH = "src/main/resources/guests.json";

    public static int[] getGuests() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            File file = new File(GUESTS_FILE_PATH);
            return objectMapper.readValue(file, int[].class);
        } catch (IOException e) {
            throw new RuntimeException("An error occurred during reading guest file.");
        }
    }
}
