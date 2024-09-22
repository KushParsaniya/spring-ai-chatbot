package dev.kush.springaichatbot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class ChatClientConfig {

    @Value("classpath:prompts/product-system-prompt.txt")
    private Resource productSystemPrompt;

    @Value("classpath:prompts/chat-memory-advisor.txt")
    private Resource chatMemoryAdvisorPrompt;

    private final ChatMemory chatMemory;

    @Bean
    ChatClient productChatClient(ChatClient.Builder builder) throws IOException {
        return builder
//                .defaultAdvisors(new PromptChatMemoryAdvisor(chatMemory, null, 20,
//                        chatMemoryAdvisorPrompt.getContentAsString(Charset.defaultCharset())))
//                .defaultAdvisors(rememberMeAdvisor)
//                .defaultSystem(productSystemPrompt)
                .defaultFunctions(
                        "getProductsByProductNameAndCompanyIdAndIsDeleted",
                        "getProductCountFromProductNameAndCompanyIdAndIsDeleted",
                        "getAllProductByCompanyIdAndIsDeleted",
                        "getAllProductVariantsByProductIdAndIsDeleted",
                        "getProductVariantCountByProductIdAndIsDeleted")
                .build();
    }
}
