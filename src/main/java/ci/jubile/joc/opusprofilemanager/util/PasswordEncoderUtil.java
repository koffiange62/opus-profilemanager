package ci.jubile.joc.opusprofilemanager.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordEncoderUtil {

    @Value("${spring.security.encoder.strength}")
    private static final Integer encoderStrength = 16;
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(encoderStrength);

    public static BCryptPasswordEncoder getEncoder(){
        return encoder;
    }

}
