package com.alxvs.service;

public class LoggerService implements ILoggerService {
    @Override
    public void log(String value) {
        System.out.println(value);
    }

    @Override
    public void error(String value) {
        String redColorANSI = "\u001B[31m";
        String resetColorANSI = "\u001B[0m";

        System.out.println(redColorANSI + value + resetColorANSI);
    }
}
