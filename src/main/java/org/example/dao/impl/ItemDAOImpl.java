package org.example.dao.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import org.example.dao.ItemDAO;
import org.example.domain.Component;
import org.example.domain.Item;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<Item> findAll(Component component) {
        File fileName = new File(component.getFileName());

        List<Item> items = new ArrayList<>();
        try {
            items = OBJECT_MAPPER.readValue(fileName, new TypeReference<>() {
            });
            return items;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        return items;

    }

    @Override
    public void save(List<Item> items) {
        String fileName = items.get(0).getComponent().getFileName();

        try {
            OBJECT_MAPPER.writeValue(new File(fileName), items);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public List<Item> readData(String fileName) throws IOException {

        return OBJECT_MAPPER.readValue(new File(fileName), new TypeReference<>() {
        });
    }

    @Override
    public float findPriceByItem(Item item) {
        List<Item> items = findAll(item.getComponent());

        return items
                .stream()
                .filter(it -> it.getModel().equals(item.getModel()))
                .findFirst()
                .get()
                .getPrice();

    }
}
