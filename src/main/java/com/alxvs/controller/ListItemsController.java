package com.alxvs.controller;

import com.alxvs.constants.Message;
import com.alxvs.repository.IRepository;
import com.alxvs.repository.Repository;
import com.alxvs.service.IItemService;
import com.alxvs.service.ILoggerService;
import com.alxvs.service.ItemService;
import com.alxvs.service.LoggerService;

public class ListItemsController {
    public static void main(String[] args) {
        IRepository repository = new Repository();
        ILoggerService loggerService = new LoggerService();
        IItemService itemService = new ItemService(repository, loggerService);

        loggerService.log(Message.LIST_ALL_ITEMS_MODULE);

        itemService.listItems();
    }
}
