package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动入口
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class Application {
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("               ----DDServer Running----\n" +
				"                  ________  ________                    \n" +
				"                  \\______ \\ \\______ \\                   \n" +
				"                   |    |  \\ |    |  \\                  \n" +
				"                   |    `   \\|    `   \\                 \n" +
				" _________________/_______  /_______  /_________________\n" +
				"/_____/_____/_____/       \\/        \\/_____/_____/_____/");
	}

}
