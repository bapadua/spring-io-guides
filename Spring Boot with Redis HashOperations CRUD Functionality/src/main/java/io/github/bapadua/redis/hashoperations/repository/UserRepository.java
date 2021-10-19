package io.github.bapadua.redis.hashoperations.repository;

import io.github.bapadua.redis.hashoperations.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Slf4j
@Repository
public class UserRepository {

    private final HashOperations<String, Long, User> hashOperations;

    public UserRepository(RedisTemplate<String, User> redisTemplate) {
        hashOperations = redisTemplate.opsForHash();
    }

    public void create(User user) {
        hashOperations.put("USER", user.getUserId(), user);
        log.info("User with user id: {} saved", user.getUserId());
    }

    public User get(final Long userId) {
        return (User) hashOperations.get("USER", userId);
    }

    public Map<Long, User> getAll() {
        return hashOperations.entries("USER");
    }

    public Map<Long, User> getAllTest() {
        return hashOperations.entries("*");
    }

    public void update(User user) {
        hashOperations.put("USER", user.getUserId(), user);
        log.info("User with userid: {} updated", user.getUserId());
    }

    public void delete(String userId) {
        hashOperations.delete("USER", userId);
        log.info("User id: {} deleted", userId);
    }
}
