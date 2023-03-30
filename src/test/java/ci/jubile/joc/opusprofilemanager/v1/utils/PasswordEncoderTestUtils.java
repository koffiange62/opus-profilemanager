package ci.jubile.joc.opusprofilemanager.v1.utils;

import ci.jubile.joc.opusprofilemanager.v1.util.PasswordEncoderUtil;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(SpringExtension.class)
class PasswordEncoderUtilsTest {

    @Test
    void bcryptPasswordEncode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = PasswordEncoderUtil.getEncoder().encode("*bouYakaTcha!*");
        assertTrue(encoder.matches("*bouYakaTcha!*", passwordEncoded));
    }
}