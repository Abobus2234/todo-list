package com.alxvs.service;

import com.alxvs.constants.Message;
import com.alxvs.models.Item;
import com.alxvs.repository.IRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ItemService implements IItemService {
    private final String ITEMS_FILE_PATH = "src/main/resources/items.json";
    private final String ITEM_ID_FILE_PATH = "src/main/resources/itemId.txt";

    private final IRepository repository;
    private final ObjectMapper objectMapper;
    private final ILoggerService loggerService;

    public ItemService(IRepository repository, ILoggerService loggerService) {
        this.repository = repository;
        this.loggerService = loggerService;
        objectMapper = new ObjectMapper();
    }

    @Override
    public void addItem(String description) {
        try {
            final String idRaw = this.repository.getLocalValue(ITEM_ID_FILE_PATH);
            int parsedLastId = Integer.parseInt(idRaw);
            int newId = parsedLastId + 1;
            this.repository.setLocalValue(ITEM_ID_FILE_PATH, Integer.toString(newId));

            Item newItem = new Item(newId, description, false);

            final String itemsRaw = this.repository.getLocalValue(ITEMS_FILE_PATH);

            final List<Item> itemsList;

            if (itemsRaw.isEmpty()) {
                itemsList = new ArrayList<>();
            } else {
                itemsList = this.objectMapper.readValue(itemsRaw, new TypeReference<List<Item>>() {});
            }

            itemsList.add(newItem);

            final String newItemsList = this.objectMapper.writeValueAsString(itemsList);
            this.repository.setLocalValue(ITEMS_FILE_PATH, newItemsList);
            this.loggerService.error(Message.ITEM_ADDED_MESSAGE);
        } catch (JsonProcessingException e) {
            this.loggerService.error(Message.JSON_PARSE_ERROR);
        } catch (IOException e) {
            this.loggerService.error(Message.FILE_READ_WRITE_ERROR);
        }
    }

    @Override
    public void deleteItem(int id) {
        try {
            final String itemsRaw = this.repository.getLocalValue(ITEMS_FILE_PATH);
            final List<Item> itemsList = this.objectMapper.readValue(itemsRaw, new TypeReference<List<Item>>() {});

            itemsList.removeIf(item -> item.getId() == id);

            final String newItemsList = this.objectMapper.writeValueAsString(itemsList);
            this.repository.setLocalValue(ITEMS_FILE_PATH, newItemsList);
            this.loggerService.error(Message.ITEM_DELETED_MESSAGE);
        } catch (JsonProcessingException e) {
            this.loggerService.error(Message.JSON_PARSE_ERROR);
        } catch (IOException e) {
            this.loggerService.error(Message.FILE_READ_WRITE_ERROR);
        }
    }

    @Override
    public void listItems() {
        try {
            final String itemsRaw = this.repository.getLocalValue(ITEMS_FILE_PATH);
            List<Item> items = this.objectMapper.readValue(itemsRaw, new TypeReference<List<Item>>() {});

            for (final Item item : items) {
                System.out.println(item);
            }
        } catch (JsonProcessingException e) {
            this.loggerService.error(Message.JSON_PARSE_ERROR);
        } catch (IOException e) {
            this.loggerService.error(Message.FILE_READ_WRITE_ERROR);
        }
    }

    @Override
    public void deleteAllItems() {
        try {
            this.repository.setLocalValue(ITEMS_FILE_PATH, "");
            this.loggerService.error(Message.ITEMS_DELETED_MESSAGE);
        } catch (IOException e) {
            this.loggerService.error(Message.FILE_READ_WRITE_ERROR);
        }
    }

    @Override
    public void markItem(int id) {
        try {
            final String itemsRaw = this.repository.getLocalValue(ITEMS_FILE_PATH);
            final List<Item> itemsList = this.objectMapper.readValue(itemsRaw, new TypeReference<List<Item>>() {});

            for (final Item item : itemsList) {
                if(item.getId() == id) {
                    item.setMarked(!item.isMarked());
                }
            }

            final String newItemsList = this.objectMapper.writeValueAsString(itemsList);
            this.repository.setLocalValue(ITEMS_FILE_PATH, newItemsList);
            this.loggerService.error(Message.ITEM_MARKED);
        } catch (JsonProcessingException e) {
            this.loggerService.error(Message.JSON_PARSE_ERROR);
        } catch (IOException e) {
            this.loggerService.error(Message.FILE_READ_WRITE_ERROR);
        }
    }
}
