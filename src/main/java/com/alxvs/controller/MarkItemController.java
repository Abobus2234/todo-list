package com.alxvs.controller;

import com.alxvs.constants.Message;
import com.alxvs.repository.IRepository;
import com.alxvs.repository.Repository;
import com.alxvs.service.IItemService;
import com.alxvs.service.ILoggerService;
import com.alxvs.service.ItemService;
import com.alxvs.service.LoggerService;

import java.util.Scanner;

public class MarkItemController {
    public static void main(String[] args) {
        IRepository repository = new Repository();
        ILoggerService loggerService = new LoggerService();
        IItemService itemService = new ItemService(repository, loggerService);

        loggerService.log(Message.MARK_ITEM_MODULE);

        Scanner scanner = new Scanner(System.in);
        String rawId = scanner.nextLine();

        try {
            int parsedId = Integer.parseInt(rawId);
            itemService.markItem(parsedId);
        } catch (NumberFormatException e) {
            loggerService.error(Message.INPUT_PARSE_ERROR);
        }
    }
}
