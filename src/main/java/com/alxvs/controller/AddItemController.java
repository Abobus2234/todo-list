package com.alxvs.controller;

import com.alxvs.constants.Message;
import com.alxvs.repository.IRepository;
import com.alxvs.repository.Repository;
import com.alxvs.service.IItemService;
import com.alxvs.service.ILoggerService;
import com.alxvs.service.ItemService;
import com.alxvs.service.LoggerService;

import java.util.Scanner;

public class AddItemController {
    public static void main(String[] args) {
        IRepository repository = new Repository();
        ILoggerService loggerService = new LoggerService();
        IItemService itemService = new ItemService(repository, loggerService);

        loggerService.log(Message.ADD_ITEM_MODULE);

        Scanner scanner = new Scanner(System.in);
        String rawDescription = scanner.nextLine();

        itemService.addItem(rawDescription);
    }
}
