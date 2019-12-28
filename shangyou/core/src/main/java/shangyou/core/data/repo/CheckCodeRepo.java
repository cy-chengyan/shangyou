package shangyou.core.data.repo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import shangyou.core.data.redis.CheckCodeRedis;

@Repository
public class CheckCodeRepo {

    @Autowired
    private CheckCodeRedis checkCodeRedis;

    public boolean set(String mobileNumber, String checkCode) {
        return checkCodeRedis.set(mobileNumber, checkCode);
    }

    public String get(String mobileNumber) {
        return checkCodeRedis.get(mobileNumber);
    }

    public boolean getCheckCodeSentStatus(String mobileNumber) {
        return checkCodeRedis.getCheckCodeSentStatus(mobileNumber);
    }

    public boolean setCheckCodeSentStatus(String mobileNumber, boolean status) {
        return checkCodeRedis.setCheckCodeSentStatus(mobileNumber, status);
    }

}
