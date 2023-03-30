package ci.jubile.joc.opusprofilemanager.v1.utils;

import ci.jubile.joc.opusprofilemanager.model.Profile;
import ci.jubile.joc.opusprofilemanager.v1.resource.ProfileResource;

import java.time.LocalDateTime;

public class ProfileTestUtils {

    public static final String PROFILE_ID = "GGGJGGF";
    public static ProfileResource profileResourceBuilder(){
        return ProfileResource.builder()
                .id(PROFILE_ID)
                .address("1956 av Therese")
                .city("montreal")
                .country("Canada")
                .district("montreal")
                .email("koffiange@siracide.com")
                .firstName("ange")
                .lastName("koffi")
                .password("test123")
                .phoneNumber("5142356235")
                .province("Quebec")
                .build();
    }

    public static Profile profileBuilder(){
        Profile profile = new Profile(PROFILE_ID, "koffi", "ange", "koffiange62@gmail.com",
                "5144341118",  "Canada", "Quebec",
                "Montreal", "Montreal", "Av Walklay", "2325");
        profile.setCreatedAt(LocalDateTime.now());
        return profile;
    }
}
