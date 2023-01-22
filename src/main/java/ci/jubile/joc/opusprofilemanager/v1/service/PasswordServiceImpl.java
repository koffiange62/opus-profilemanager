package ci.jubile.joc.opusprofilemanager.v1.service;

import ci.jubile.joc.opusprofilemanager.domain.Password;
import ci.jubile.joc.opusprofilemanager.util.PasswordEncoderUtil;
import ci.jubile.joc.opusprofilemanager.v1.exception.PasswordHandlerException;
import ci.jubile.joc.opusprofilemanager.v1.repository.PasswordRepository;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ResourceBundle;

@Component
@Slf4j
public class PasswordServiceImpl implements PasswordService{
    @Value("${spring.password.history.retention.size}")
    private Integer passwordHistoryRetentionSize;
    ResourceBundle resourceBundle = ResourceBundle.getBundle("messages");

    private final PasswordRepository passwordRepository;

    public PasswordServiceImpl(PasswordRepository passwordRepository) {
        this.passwordRepository = passwordRepository;
    }

    @Override
    public void create(PasswordResource passwordResource) {
        Password password = new Password();
        password.setProfileId(passwordResource.getProfileId());
        password.setCurrentPassword(PasswordEncoderUtil.getEncoder().encode(passwordResource.getNewPassword()));
        password.setCreatedAt(LocalDateTime.now());
        passwordRepository.insert(password);
    }

    @Override
    public Password findByProfileId(String profileId) throws PasswordHandlerException {
        return passwordRepository.findByProfileId(profileId)
                .orElseThrow(() -> new PasswordHandlerException(resourceBundle.getString("password.not.found")));
    }

    @Override
    public void updatePassword(PasswordResource passwordResource) throws PasswordHandlerException{
        Password password = this.findByProfileId(passwordResource.getProfileId());
        log.info("==> password retrieved");
        if (password != null){
            boolean oldAndCurrentPasswordsAreTheSame = PasswordEncoderUtil.getEncoder()
                    .matches(passwordResource.getCurrentPassword(), password.getCurrentPassword());
            log.info("==> Old and Current Passwords Are Same : "+oldAndCurrentPasswordsAreTheSame);
            if (oldAndCurrentPasswordsAreTheSame){
                if (PasswordEncoderUtil.getEncoder().matches(passwordResource.getNewPassword(),
                        password.getCurrentPassword()))
                    throw new PasswordHandlerException(resourceBundle.getString("password.not.change"));
                log.info("==> Updating password history...");
                this.updatePasswordHistory(password);
                log.info("==> Updating current password ...");
                password.setCurrentPassword(PasswordEncoderUtil.getEncoder().encode(passwordResource.getNewPassword()));
                password.setUpdatedAt(LocalDateTime.now());
                passwordRepository.save(password);
                log.info("==> End");
            } else {
                throw new PasswordHandlerException(resourceBundle.getString("password.not.correct"));
            }
        }
    }

    private void updatePasswordHistory(Password password){
        if (password.getPasswordHistory().size() == passwordHistoryRetentionSize){
            password.getPasswordHistory().poll();
        }
        password.getPasswordHistory().add(password.getCurrentPassword());
    }
}
