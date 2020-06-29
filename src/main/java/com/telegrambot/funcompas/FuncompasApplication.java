package com.telegrambot.funcompas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootApplication
public class FuncompasApplication {

    public static void main(String[] args) {

        SpringApplication.run(FuncompasApplication.class, args);
    }

}
