package shangyou.core.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CheckCodeRepo {

    private final String KEY_PREFIX = "CC_";
    private final int EXPIRED_SECONDS = 15 * 60;

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean set(String mobileNumber, String checkCode) {
        String key = KEY_PREFIX + mobileNumber;
        redisTemplate.opsForValue().set(key, checkCode);
        redisTemplate.expire(key, EXPIRED_SECONDS, TimeUnit.SECONDS);
        return true;
    }

    public String get(String mobileNumber) {
        String key = KEY_PREFIX + mobileNumber;
        return redisTemplate.opsForValue().get(key);
    }

}
