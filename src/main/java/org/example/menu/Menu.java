package org.example.menu;

import org.example.domain.Component;
import org.example.domain.Item;
import org.example.domain.Manufacturer;
import org.example.domain.User;
import org.example.service.ItemService;
import org.example.service.UserService;
import org.example.service.impl.ItemServiceImpl;
import org.example.service.impl.UserServiceImpl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final UserService USER_SERVICE = new UserServiceImpl();
    private static final ItemService ITEM_SERVICE = new ItemServiceImpl();

    public static void main(String[] args) {
        createFiles();
        while (true) {
            showRightsMenu();
            int key = SCANNER.nextInt();
            try {
                switch (key) {
                    case 1: {
                        String login = getUserInput("Enter login");

                        String password = getUserInput("Enter password");

                        User user = new User(login, password);

                        boolean isExist = USER_SERVICE.checkExistingUserByLoginAndPassword(user);

                        if (!isExist) {
                            System.out.println("Please registration");
                        } else {
                            boolean isAdmin = USER_SERVICE.checkIsAdmin(login);

                            String role = isAdmin ? "Admin" : "User";

                            System.out.println("Hello " + login + " your role is " + role);

                            if (isAdmin) {
                                boolean isContinue = true;
                                while (isContinue) {
                                    showAdminMenu();
                                    int keyAdmin = SCANNER.nextInt();

                                    switch (keyAdmin) {
                                        case 1:
                                            String componentName = getUserInput("Enter component name");
                                            Component compName =
                                                    Component.valueOf(componentName.toUpperCase());
                                            String model = getUserInput("Enter component model");
                                            String manufacturerComponent =
                                                    getUserInput("Enter manufacturer component").toUpperCase();
                                            Manufacturer manufacturer =
                                                    Manufacturer.valueOf(manufacturerComponent);
                                            String priceUser = getUserInput("Enter component price");
                                            float price = Float.parseFloat(priceUser);

                                            Item item = new Item(compName, model, manufacturer, price);

                                            ITEM_SERVICE.save(item);
                                            break;

                                        case 2:
                                            String compModel = getUserInput("Enter component model");
                                            String componentName1 = getUserInput("Enter component name");
                                            Component compName1 =
                                                    Component.valueOf(componentName1.toUpperCase());
                                            Item item1 = new Item(compName1, compModel);
                                            ITEM_SERVICE.deleteByModel(item1);

                                            break;
                                        case 3:
                                            isContinue = false;
                                            break;

                                    }

                                }
                            } else {

                                String modelProcessor = getUserInput("Enter model processor");
                                Item processor = new Item(Component.PROCESSOR, modelProcessor);

                                String modelMotherBoard = getUserInput("Enter model mother board");
                                Item motherBoard = new Item(Component.MOTHER_BOARD, modelMotherBoard);

                                String modelRam = getUserInput("Enter model RAM");
                                Item ram = new Item(Component.RAM, modelRam);

                                String modelHdd = getUserInput("Enter model HDD");
                                Item hdd = new Item(Component.HDD, modelHdd);

                                List<Item> items = List.of(processor, motherBoard, hdd, ram);

                                double price = ITEM_SERVICE.calculateCommonPrice(items);

                                BigDecimal commonPrice =
                                        BigDecimal.valueOf(price).setScale(2, RoundingMode.CEILING);

                                System.out.println("Common price = " + commonPrice);
                            }
                        }

                        break;
                    }
                    case 2: {
                        String login = getUserInput("Enter login");

                        String password = getUserInput("Enter password");

                        User user = new User(login, password, false);

                        USER_SERVICE.registration(user);
                        break;
                    }
                    case 3: {
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("Exception" + e);
            }
        }
    }

    private static String getUserInput(String message) {
        System.out.println(message);
        return SCANNER.next();
    }

    private static void showRightsMenu() {
        System.out.println("1) Login.\n" +
                "2) Registration.\n" +
                "3) Exit.");
    }

    private static void showAdminMenu() {
        System.out.println("1) Add item.\n" +
                "2) Delete item.\n" +
                "3) Exit.");
    }

    private static void createFiles() {
        Arrays
                .stream(Component.values())
                .forEach(component -> {

                    String fileName = component.getFileName();
                    Path path = Paths.get(fileName);
                    if (!Files.exists(path)) {
                        try {
                            Files.write(path, "[]".getBytes(), StandardOpenOption.CREATE);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
    }

}

