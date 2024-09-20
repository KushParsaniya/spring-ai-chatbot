package dev.kush.springaichatbot.repos;

import dev.kush.springaichatbot.models.ChatHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ChatHistoryRepository extends JpaRepository<ChatHistory, Long> {

    @Query(nativeQuery = true, value = "select content from chat_history where chat_history.session_id =:sessionId and exists(" +
            "select 1 from active_session_vo where id =:sessionId and is_active=1) order by id desc limit :limit")

//    @Query(nativeQuery = true, value = "select chat_history.content from chat_history join active_session_vo" +
//            " on chat_history.session_id = active_session_vo.id where active_session_vo.id =:sessionId and active_session_vo.is_active=1 order by chat_history.id desc limit :limit")
    List<String> findByActiveSessionIdAndLimit(String sessionId, int limit);


}