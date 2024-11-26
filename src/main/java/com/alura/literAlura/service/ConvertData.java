package com.alura.literAlura.service;

import com.alura.literAlura.model.DataAuthor;
import com.alura.literAlura.model.DataBook;
import com.alura.literAlura.model.GeneralData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.stream.Collectors;

public class ConvertData implements IConvertData{
    private ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> clase) {
        try{
            return objectMapper.readValue(json,clase);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> T getDataBook(GeneralData json, Class<T> clase){
            JsonNode node = objectMapper.valueToTree(json.results().getFirst());
            return  getData(node.toString(),clase);
    }

    public <T> T getDataAuthor(DataBook json, Class<T> clase) {
        JsonNode node = objectMapper.valueToTree(json.authors().getFirst());
        return  getData(node.toString(),clase);
    }
}
