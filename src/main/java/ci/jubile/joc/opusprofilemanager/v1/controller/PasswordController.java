package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.v1.exception.PasswordHandlerException;
import ci.jubile.joc.opusprofilemanager.v1.resource.PasswordResource;
import ci.jubile.joc.opusprofilemanager.v1.service.PasswordServiceImpl;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("v1/password")
@Validated
public class PasswordController {
    private final PasswordServiceImpl passwordService;

    public PasswordController(PasswordServiceImpl passwordService) {
        this.passwordService = passwordService;
    }

    @PutMapping("edit")
    public void editPassword(@RequestBody @Valid PasswordResource passwordResource) throws PasswordHandlerException {
        passwordService.updatePassword(passwordResource);
    }
}
