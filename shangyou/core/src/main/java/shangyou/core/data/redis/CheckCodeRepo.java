package shangyou.core.data.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CheckCodeRepo {

    private final String CC_KEY_PREFIX = "CC_";
    private final int EXPIRED_SECONDS = 15 * 60;
    private final String SC_KEY_PREFIX = "SC_";

    @Autowired
    private StringRedisTemplate redisTemplate;

    public boolean set(String mobileNumber, String checkCode) {
        String key = CC_KEY_PREFIX + mobileNumber;
        redisTemplate.opsForValue().set(key, checkCode);
        redisTemplate.expire(key, EXPIRED_SECONDS, TimeUnit.SECONDS);
        return true;
    }

    public String get(String mobileNumber) {
        String key = CC_KEY_PREFIX + mobileNumber;
        return redisTemplate.opsForValue().get(key);
    }

    public boolean getCheckCodeSentStatus(String mobileNumber) {
        String key = SC_KEY_PREFIX + mobileNumber;
        return redisTemplate.opsForValue().get(key) != null;
    }

    public boolean setCheckCodeSentStatus(String mobileNumber, boolean status) {
        String key = SC_KEY_PREFIX + mobileNumber;
        if (status) {
            redisTemplate.opsForValue().set(key, "");
            redisTemplate.expire(key, 60, TimeUnit.SECONDS);
        } else {
            redisTemplate.delete(key);
        }
        return true;
    }

}
