package com.fakeapi.fakeapi.service;

import com.jayway.jsonpath.JsonPath;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class UnitService {
    private final String units;
    private final String complexUnits;
    private final String users;
    {
        try {
            units = readJsonFile("responses/igel/unit.json");
            complexUnits = readJsonFile("responses/igel/unit_complex.json");
            users = readJsonFile("responses/igel/user.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUnitById(String id)  {
        String jsonPath = "$.[?(@.unitID == '" + id + "')]";
        String result = JsonPath.read(units, jsonPath).toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String getUnitByNetworkName(String networkName) {
        String jsonPath = "$.[?(@.networkName == '" + networkName + "')]";
        String result = JsonPath.read(units, jsonPath).toString();
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String getAllUnit() {
        return units;
    }

    public String getComplexUnitById(String id) {
        String jsonPath = "$.[?(@.unitID == '" + id + "')]";
        String result = JsonPath.read(complexUnits, jsonPath).toString();
        if (result.isEmpty()) {
            return "";
        }
        result = result.substring(1, result.length() - 1);
        return result;
    }

    public String getAllUnitComplex() {
        return complexUnits;
    }

    public String getUserById(String id) {
        String jsonPath = "$.[?(@.id == '" + id + "')]";
        String result = JsonPath.read(users, jsonPath).toString();
        if (result.isEmpty()) {
            return "";
        }
        result = result.substring(1, result.length() - 1);
        return  result;
    }

    public String getAllUsers() {
        return users;
    }

    private static String readJsonFile(String filePath) throws IOException {
        // Load the JSON file from the resource folder
        ClassPathResource resource = new ClassPathResource(filePath);

        // Read the content of the JSON file into a byte array
        byte[] fileBytes = FileCopyUtils.copyToByteArray(resource.getInputStream());

        // Convert the byte array to a string using the UTF-8 charset
        return new String(fileBytes, StandardCharsets.UTF_8);
    }
}
