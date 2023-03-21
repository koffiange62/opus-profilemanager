package ci.jubile.joc.opusprofilemanager.unittest.v1.utils;

import ci.jubile.joc.opusprofilemanager.model.Domain;
import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.resource.DomainResource;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;

public class FormationTestUtils {

    private final static DomainResource DOMAIN_RESOURCE = DomainResource.builder().code("R&D").libelle("Recherche et Développement").build();
    private final static Domain DOMAIN = new Domain("R&D", "Recherche et Développement");

    public static FormationResource formationResourceBuilder(){
        return FormationResource.builder()
                .domain(DOMAIN_RESOURCE)
                .diploma("Ingenieur Mecanique")
                .school("Harvard").year(2019).build();
    }

    public static Formation FormationBuilder() {

        return new Formation("Ingenieur Mecanique", "Harvard", 2019, DOMAIN);
    }
}
