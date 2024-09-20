package dev.kush.springaichatbot.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class ActiveSessionVo {

    @Id
    private String id;

    int isActive;

    long companyId;

    public ActiveSessionVo(String id, int isActive, long companyId) {
        this.id = id;
        this.isActive = isActive;
        this.companyId = companyId;
    }
}
