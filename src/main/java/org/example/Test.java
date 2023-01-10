package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.example.domain.Component;
import org.example.domain.Item;
import org.example.domain.Manufacturer;
import org.example.service.ItemService;
import org.example.service.impl.ItemServiceImpl;

import java.io.IOException;
import java.util.List;

public class Test {

    private static final ItemService ITEM_SERVICE = new ItemServiceImpl();

    public static void main(String[] args) throws IOException {
        Item item = new Item(Component.valueOf("RAM"), "AM-89",
                Manufacturer.valueOf("SAMSUNG"), 12.45f);

        Item item1 = new Item(Component.valueOf("MOTHER_BOARD"), "MPO-67",
                Manufacturer.valueOf("SAMSUNG"), 35.45f);

//        ITEM_SERVICE.save(item);

        System.out.println(ITEM_SERVICE.calculateCommonPrice(List.of(item, item1)));
    }
}
