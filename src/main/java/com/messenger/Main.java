package com.messenger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main
{
    public static void main(String[] args)
    {
        //DB init
        /* TODO: Здесь нужно проинициализировать бд (создать таблички, ЕСЛИ ИХ НЕТ!)*/

        SpringApplication.run(Main.class);
    }
}
