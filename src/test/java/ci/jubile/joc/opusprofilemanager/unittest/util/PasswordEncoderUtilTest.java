package ci.jubile.joc.opusprofilemanager.unittest.util;

import ci.jubile.joc.opusprofilemanager.util.PasswordEncoderUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class PasswordEncoderUtilTest {

    @Test
    void bcryptPasswordEncode(){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String passwordEncoded = PasswordEncoderUtil.getEncoder().encode("*bouYakaTcha!*");
        assertTrue(encoder.matches("*bouYakaTcha!*", passwordEncoded));
    }
}