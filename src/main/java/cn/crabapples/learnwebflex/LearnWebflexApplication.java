package cn.crabapples.learnwebflex;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LearnWebflexApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnWebflexApplication.class, args);
    }

    @Bean
    public CommandLineRunner runner(ChatClient.Builder builder) {
        return args -> {
            ChatClient chatClient = builder.build();
            String response = chatClient
                    .prompt("Tell me a joke")
                    .messages().call()
                    .content();
            System.out.println(response);
        };
    }


}
