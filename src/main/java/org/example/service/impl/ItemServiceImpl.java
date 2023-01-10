package org.example.service.impl;

import org.example.dao.ItemDAO;
import org.example.dao.impl.ItemDAOImpl;
import org.example.domain.Component;
import org.example.domain.Item;
import org.example.service.ItemService;

import java.io.IOException;
import java.util.List;

public class ItemServiceImpl implements ItemService {

    private ItemDAO itemDAO;

    public ItemServiceImpl() {
        itemDAO = new ItemDAOImpl();
    }

    @Override
    public List<Item> findByName(Component component) throws IOException {
        return itemDAO.findAll(component);
    }

    @Override
    public void save(Item item) throws IOException {
        List<Item> items = itemDAO.readData(item.getComponent().getFileName());
        items.add(item);
        itemDAO.save(items);
    }

    @Override
    public void deleteByModel(Item item) throws IOException {

        List<Item> items = itemDAO.readData(item.getComponent().getFileName());

        items.removeIf(it -> it.getModel().equals(item.getModel()));

        itemDAO.save(items);
    }

    @Override
    public double calculateCommonPrice(List<Item> items) {

        double commonPrice = 0;

        for (Item item : items) {
            commonPrice += itemDAO.findPriceByItem(item);
        }

       return commonPrice;

    }
}
