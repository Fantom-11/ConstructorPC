package org.example.service;

import org.example.domain.Component;
import org.example.domain.Item;

import java.io.IOException;
import java.util.List;

public interface ItemService {

    List<Item> findByName(Component component) throws IOException;

    void save(Item item) throws IOException;

    void deleteByModel(Item item) throws IOException;

    double calculateCommonPrice(List<Item> items);

}
