package focandlol.util;

import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GenerateKey {
    public String generateUserKey(){
        return UUID.randomUUID().toString().replace("-", "");
    }
}
