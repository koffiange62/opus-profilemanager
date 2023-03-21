package ci.jubile.joc.opusprofilemanager.unittest.v1.utils;

import ci.jubile.joc.opusprofilemanager.model.Domain;
import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.resource.DomainResource;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;

import java.util.List;

public class FormationTestUtils {

    private final static DomainResource DOMAIN_RESOURCE = DomainResource.builder().code("R&D").libelle("Recherche et Développement").build();
    private final static Domain DOMAIN = new Domain("R&D", "Recherche et Développement");
    public final static String FORMATION_ID = "QWERTY";

    public static FormationResource formationResourceBuilder(){
        return FormationResource.builder()
                .id(FORMATION_ID)
                .domain(DOMAIN_RESOURCE)
                .diploma("Ingenieur Mecanique")
                .school("Harvard").year(2019).build();
    }

    public static Formation formationBuilder() {
        return new Formation("Ingenieur Mecanique", "Harvard", 2019, DOMAIN);
    }

    public static List<Formation> formationListBuilder(){
        return List.of(formationBuilder());
    }

    public static List<FormationResource> formationResourceListBuilder(){
        return List.of(formationResourceBuilder());
    }
}
