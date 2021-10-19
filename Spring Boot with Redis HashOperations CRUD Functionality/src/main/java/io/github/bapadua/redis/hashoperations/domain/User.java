package io.github.bapadua.redis.hashoperations.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private Long userId;
    private String name;

    public User(Integer userId) {
        this.userId = (long) userId;
    }
}
