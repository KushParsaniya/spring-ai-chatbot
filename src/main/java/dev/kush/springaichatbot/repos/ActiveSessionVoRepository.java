package dev.kush.springaichatbot.repos;

import dev.kush.springaichatbot.models.ActiveSessionVo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ActiveSessionVoRepository extends JpaRepository<ActiveSessionVo, String> {

    @Modifying
    @Query("update ActiveSessionVo a set a.isActive=0 where a.id = :sessionId")
    int updateIsActiveBySessionId(String sessionId);
}