package cn.crabapples.learnwebflex.system.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.model.Generation;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/ai")
public class ChatController {
    @Autowired
    private OpenAiChatModel chatModel;
    @Autowired
    private ChatClient.Builder builder;

    @GetMapping("/question")
    public Flux<String> question(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        ChatClient chatClient = builder.build();
        return chatClient.prompt()
                .messages(new UserMessage(message))
                .stream()
                .chatResponse()
                .log()
                .map(e -> {
                    Generation results = e.getResult();
                    AssistantMessage output = results.getOutput();
                    return output.getText();
                });
    }

    @GetMapping("/question/stream")
    public Flux<ServerSentEvent<Object>> questionStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        ChatClient chatClient = builder.build();
        return chatClient.prompt()
                .messages(new UserMessage(message))
                .stream()
                .chatResponse().map(e -> {
                    Generation results = e.getResult();
                    AssistantMessage output = results.getOutput();
                    return ServerSentEvent.builder().data(output.getText()).build();
                });
    }

    @GetMapping("/ai/generateStream")
    public Flux<ChatResponse> generateStream(@RequestParam(value = "message", defaultValue = "Tell me a joke") String message) {
        Prompt prompt = new Prompt(new UserMessage(message));
        return this.chatModel.stream(prompt);
    }
}
