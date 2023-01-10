package ci.jubile.joc.opusprofilemanager.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    @Value("${spring.security.encoder.strength}")
    private static final Integer encoderStrength = 16;

    public static String bcryptPasswordEncode(String password){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(encoderStrength);
        return encoder.encode(password);
    }

}
