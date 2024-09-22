package dev.kush.springaichatbot.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.client.advisor.PromptChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.UUID;

@RestController
@RequestMapping("/product")
public class ProductChatController {

    private final ChatClient chatClient;

    private final ChatMemory chatMemory;

    private final RequestResponseAdvisor rememberMeAdvisor;

    @Value("classpath:prompts/product-user-prompt.txt")
    private Resource productUserPrompt;

    @Value("classpath:prompts/chat-memory-advisor.txt")
    private Resource chatMemoryAdvisorPrompt;


    public ProductChatController(@Qualifier("productChatClient") ChatClient chatClient, ChatMemory chatMemory, RequestResponseAdvisor rememberMeAdvisor) {
        this.chatClient = chatClient;
        this.chatMemory = chatMemory;
        this.rememberMeAdvisor = rememberMeAdvisor;
    }

    @GetMapping("/init/{companyId}")
    public String initChat(HttpServletResponse response, @RequestParam String productName, @PathVariable long companyId) throws IOException {
        final String conversationId = UUID.randomUUID().toString();
        response.setHeader("sessionId", conversationId);
        return chatClient
                .prompt()
                .user(String.format(productUserPrompt.getContentAsString(Charset.defaultCharset()),
                        companyId, productName))
                .advisors(new PromptChatMemoryAdvisor(chatMemory, conversationId, 20,
                        chatMemoryAdvisorPrompt.getContentAsString(Charset.defaultCharset())))
                .call()
                .content();
    }

    @GetMapping("/chat")
    public String chat(@RequestParam String message) {
        return chatClient
                .prompt()
                .advisors(rememberMeAdvisor)
                .user(message)
                .call().content();
    }


    @GetMapping("/continue/{companyId}")
    public String continueChat(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam String productName,
            @PathVariable long companyId) throws IOException {

        String sessionId = request.getHeader("sessionId");
        if (sessionId == null || sessionId.isBlank()) {
            response.setStatus(404);
            return "sessionId not found";
        }
        return chatClient
                .prompt()
                .user(String.format(productUserPrompt.getContentAsString(Charset.defaultCharset()),
                        companyId, productName))
                .advisors(new PromptChatMemoryAdvisor(chatMemory, sessionId, 20,
                        chatMemoryAdvisorPrompt.getContentAsString(Charset.defaultCharset())))
                .call()
                .content();
    }

    @DeleteMapping("/clear")
    public void clearChat(HttpServletRequest request) {
        chatMemory.clear(request.getHeader("sessionId"));
    }
}
