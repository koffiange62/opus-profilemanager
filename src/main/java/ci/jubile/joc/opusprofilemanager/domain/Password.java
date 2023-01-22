package ci.jubile.joc.opusprofilemanager.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;

@Getter @Setter
public class Password extends model{
    String currentPassword;
    LinkedList<String> passwordHistory = new LinkedList<>();
    String profileId;

    @Override
    public String toString() {
        return null;
    }
}
