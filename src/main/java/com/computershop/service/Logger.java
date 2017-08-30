package com.computershop.service;

import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

@Service
public class Logger {
    private static final String path = "D:\\log.txt";
    private static final String CONSOLE = "console";
    private static final String FILE = "file";
    private static Logger logger;
    private String level = FILE;
    private  String className;

    public static Logger getLogger(Class className) {
        logger = new Logger();
        logger.setClassName(className.getName());
        return logger;
    }

    public void info(String text) {
        String log = LocalDateTime.now() + " INFO " + className + " - " + text;
        if (level.equals(CONSOLE)) {
            System.out.println(log);
        } else
            toFile(log);
    }

    public void error(String text) {
        String log = LocalDateTime.now() + " ERROR " + className + " - " + text;
        if (level.equals(CONSOLE)) {
            System.out.println(log);
        } else
            toFile(log);
    }

    public void warn(String text) {
        String log = LocalDateTime.now() + " WARNING " + className + " - " + text;
        if (level.equals(CONSOLE)) {
            System.out.println(log);
        } else
            toFile(log);
    }

    private void toFile(String text) {
        File fileName = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.append(text);
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void setClassName(String className) {
        this.className = className;
    }

    public void setLevel(String level) {
        if (level.equals(CONSOLE)) {
            logger.level = CONSOLE;
        } else {
            logger.level = FILE;
        }
    }
}