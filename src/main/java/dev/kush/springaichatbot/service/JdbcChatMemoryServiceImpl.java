package dev.kush.springaichatbot.service;

import dev.kush.springaichatbot.models.ActiveSessionVo;
import dev.kush.springaichatbot.models.ChatHistory;
import dev.kush.springaichatbot.repos.ActiveSessionVoRepository;
import dev.kush.springaichatbot.repos.ChatHistoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class JdbcChatMemoryServiceImpl implements JdbcChatMemoryService {

    private final ActiveSessionVoRepository activeSessionVoRepository;

    private final ChatHistoryRepository chatHistoryRepository;

    @Override
    public void add(String conversationId, List<Message> messages) {

        if (conversationId == null || conversationId.isBlank()) {
            return;
        }

        if (activeSessionVoRepository.existsById(conversationId)) {
            return;
        }

        var savedSession = activeSessionVoRepository.save(new ActiveSessionVo(conversationId, 1, 64));
        List<ChatHistory> chatHistories = messages.stream()
                .map(
                        message -> new ChatHistory(message.getContent(), savedSession))
                .toList();
        chatHistoryRepository.saveAll(chatHistories);
    }

    @Override
    public List<Message> get(String conversationId, int lastN) {
        return chatHistoryRepository.findByActiveSessionIdAndLimit(conversationId, lastN)
                .stream().map(UserMessage::new)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void clear(String conversationId) {
        if (conversationId == null || conversationId.isBlank()) {
            return;
        }
        activeSessionVoRepository.updateIsActiveBySessionId(conversationId);
    }
}
