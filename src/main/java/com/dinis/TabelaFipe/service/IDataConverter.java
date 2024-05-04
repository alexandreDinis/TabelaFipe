package com.dinis.TabelaFipe.service;

import java.util.List;

public interface IDataConverter {

    <T> T fetchData(String json, Class <T> dataClass);

    <T> List<T> getList(String json, Class<T> tClass);
}
