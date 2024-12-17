package com.lopeshenriquee.TabelaFipe.service;

public interface IDataConvert {
    <T> T getData(String json, Class<T> classe);
}
