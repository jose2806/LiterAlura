package com.alura.literAlura.service;

public interface IConvertData {
    <T> T getData(String json, Class<T> clase);
}
