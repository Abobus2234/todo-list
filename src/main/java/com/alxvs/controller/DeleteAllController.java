package com.alxvs.controller;

import com.alxvs.constants.Message;
import com.alxvs.repository.IRepository;
import com.alxvs.repository.Repository;
import com.alxvs.service.IItemService;
import com.alxvs.service.ILoggerService;
import com.alxvs.service.ItemService;
import com.alxvs.service.LoggerService;

import java.util.Scanner;

public class DeleteAllController {
    public static void main(String[] args) {
        IRepository repository = new Repository();
        ILoggerService loggerService = new LoggerService();
        IItemService itemService = new ItemService(repository, loggerService);

        loggerService.log(Message.DELETE_ALL_ITEMS_MODULE);

        loggerService.log(Message.ARE_YOU_SURE);
        loggerService.log(Message.YES_NO_MENU);

        Scanner scanner = new Scanner(System.in);
        String rawId = scanner.nextLine();

        try {
            int parsedId = Integer.parseInt(rawId);
            if (parsedId == 1) {
                itemService.deleteAllItems();
            }
        } catch (NumberFormatException e) {
            loggerService.error(Message.INPUT_PARSE_ERROR);
        }

    }
}
