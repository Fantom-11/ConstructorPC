package org.example.dao;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.domain.Component;
import org.example.domain.Item;

import java.io.IOException;
import java.util.List;

public interface ItemDAO {

    ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    List<Item> findAll(Component component) throws IOException;

    void save(List<Item> items);

    public List<Item> readData(String fileName) throws IOException;

    float findPriceByItem(Item item);

}
