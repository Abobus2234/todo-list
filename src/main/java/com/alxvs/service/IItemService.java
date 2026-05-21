package com.alxvs.service;

public interface IItemService {
    void addItem(String description);
    void deleteItem(int id);
    void listItems();
    void deleteAllItems();
    void markItem(int id);
}
