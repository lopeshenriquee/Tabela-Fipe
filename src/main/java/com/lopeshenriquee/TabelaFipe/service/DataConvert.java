package com.lopeshenriquee.TabelaFipe.service;

import com.fasterxml.jackson.databind.ObjectMapper;

public class DataConvert implements IDataConvert{
    public ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T getData(String json, Class<T> classe) {
        return null;
    }
}
