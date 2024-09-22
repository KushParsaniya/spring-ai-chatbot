package dev.kush.springaichatbot.advisors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.AdvisedRequest;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.RequestResponseAdvisor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;

@Component
@Slf4j
public class RememberMeAdvisor implements RequestResponseAdvisor {

    private final ChatClient chatClient;

    private final ExecutorService executorService;

    @Value("classpath:prompts/extract-info-system-prompt.txt")
    private Resource extractInfoSystemPrompt;

    public RememberMeAdvisor(@Lazy ChatClient chatClient, @Qualifier("memoryExtractorExecutorService") ExecutorService executorService) {
        this.chatClient = chatClient;
        this.executorService = executorService;
    }

    @Override
    public AdvisedRequest adviseRequest(AdvisedRequest request, Map<String, Object> adviseContext) {

        Callable<Boolean> callable = () -> extractMemoryIfPossible(request);
        var result = executorService.submit(callable);
        executorService.submit(() -> {
            try {
                if (result.get()) {
                    log.info("Memory extraction was successful.");
                } else {
                    log.info("Memory extraction was not successful.");
                }
            } catch (InterruptedException | ExecutionException e) {
                log.error("Error occurred while extracting memory.", e);
            }
        });

        return RequestResponseAdvisor.super.adviseRequest(request, adviseContext);
    }

    private boolean extractMemoryIfPossible(AdvisedRequest request) {
        var memoryExtractedResult = chatClient
                .prompt()
                .system(extractInfoSystemPrompt)
                .messages(memoryBasicExtraction(request))
                .call()
                .entity(MemoryExtractionResult.class);

        if (worthKeepingMemory(memoryExtractedResult)) {
            // TODO: Add this to vector-store
            System.out.println("Memory extracted: " + memoryExtractedResult.content());
            return true;
        }
        return false;
    }

    private boolean worthKeepingMemory(MemoryExtractionResult memoryExtractionResult) {
        return memoryExtractionResult != null && !memoryExtractionResult.content().isBlank() && memoryExtractionResult.useful();
    }

    private List<Message> memoryBasicExtraction(AdvisedRequest request) {
        return request.messages();
    }

    @Override
    public String getName() {
        return "RememberMeAdvisor";
    }

    record MemoryExtractionResult(String content, boolean useful) {
    }
}
