package com.test.model;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.json.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;

public class FileModel {

    private static final Logger LOG = LoggerFactory.getLogger(FileModel.class);

    private File getFile(){
        String fileName = "fileForTesting.txt";
        return new File(getClass().getClassLoader().getResource(fileName).getFile());
    }

    private int numberOfCharsInLine(String line){
        return line.length();
    }

    private JsonObject getMetaDataObject() throws IOException {
        return Json.createObjectBuilder()
        .add("fileName", getFile().getName())
        .add("fileSize", (double)getFile().length()/1024)
        .add("fileCreationDate", Files.readAttributes(getFile().toPath(), BasicFileAttributes.class).creationTime().toString())
        .build();
    }

    private JsonArray getTextWithoutLengthParameter(int limit, String word){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        BufferedReader reader;
        String line;
        int countOfChars = 0;
        try {
                reader = new BufferedReader(new FileReader(getFile()));
                while ((line = reader.readLine()) != null && countOfChars <= limit) {
                    if (line.equalsIgnoreCase(word) || StringUtils.containsIgnoreCase(line, word)) {
                        jsonArray.add(line);
                        countOfChars += numberOfCharsInLine(line);
                    }
                }
        } catch (IOException e) {
            LOG.error("Exception in getTextWithoutLengthParameter("+limit+", "+word+")" + e.getMessage());
        }
        return jsonArray.build();
    }

    private JsonArray getTextWithLengthParameter(int limit, String word, int length){
        JsonArrayBuilder jsonArray = Json.createArrayBuilder();
        BufferedReader reader;
        String line;
        int countOfChars = 0;
        try {
                reader = new BufferedReader(new FileReader(getFile()));
                while ((line = reader.readLine()) != null && countOfChars <= limit) {
                    if (line.equalsIgnoreCase(word) || StringUtils.containsIgnoreCase(line, word)) {
                        if (line.length() <= length) {
                            jsonArray.add(line);
                            countOfChars += numberOfCharsInLine(line);
                        }
                    }
                }
        } catch (IOException e) {
            LOG.error("Exception in getTextWithLengthParameter("+limit+", "+word+", "+length+")" + e.getMessage());
        }
        return jsonArray.build();
    }

    public JsonObject getFilteredObject(int limit, String word, int length, boolean includeMetaData) throws IOException {
        JsonArray jsonArray = null;
        if (length>=0) {
            jsonArray = getTextWithLengthParameter(limit, word, length);
        }
        else {
            jsonArray = getTextWithoutLengthParameter(limit, word);
        }
        JsonObjectBuilder jsonObject = Json.createObjectBuilder();
        jsonObject.add("text", jsonArray);
        if (includeMetaData){
            jsonObject.add("metaData", getMetaDataObject());
        }
        LOG.info("getFilteredObject("+limit+", "+word+", "+length+", "+includeMetaData+")");
        return jsonObject.build();
    }
}
