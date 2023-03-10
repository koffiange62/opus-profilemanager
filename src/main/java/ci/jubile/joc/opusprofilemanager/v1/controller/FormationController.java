package ci.jubile.joc.opusprofilemanager.v1.controller;

import ci.jubile.joc.opusprofilemanager.model.Formation;
import ci.jubile.joc.opusprofilemanager.v1.mapper.FormationMapper;
import ci.jubile.joc.opusprofilemanager.v1.resource.FormationResource;
import ci.jubile.joc.opusprofilemanager.v1.service.FormationServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "v1/formations")
public class FormationController {
    private final FormationServiceImpl formationService;
    private final FormationMapper formationMapper;

    public FormationController(FormationServiceImpl formationService, FormationMapper formationMapper) {
        this.formationService = formationService;
        this.formationMapper = formationMapper;
    }

    @GetMapping("/formation/profile/{profileId}")
    ResponseEntity<List<FormationResource>> listFormation(@PathVariable(name = "profileId") String profileId) {
        List<Formation> formationList = formationService.findAll(profileId);
        List<FormationResource> formationResourceList = formationMapper.formationListToResourceList(formationList);
        return ResponseEntity.ok(formationResourceList);
    }

    @PostMapping("/formation/profile/{profileId}")
    ResponseEntity<FormationResource> addFormation(@PathVariable(name = "profileId") String profileId,
                                                   @RequestBody FormationResource formationResource){
        Formation formation = formationMapper.formationResourceToFormation(formationResource);
        formation = formationService.add(profileId, formation);
        FormationResource response =  formationMapper.formationToFormationResource(formation);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/formation/profile/{profileId}")
    ResponseEntity<FormationResource> updateFormation(@PathVariable(name = "profileId") String profileId,
                                                      @RequestBody FormationResource formationResource){
        Formation formation = formationMapper.formationResourceToFormation(formationResource);
        formation = formationService.update(profileId, formation);
        FormationResource response = formationMapper.formationToFormationResource(formation);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/formation")
    ResponseEntity<?> deleteFormation(@RequestParam(name = "profileId") String profileId,
                                      @RequestParam(name = "formationId") String formationId){
        formationService.delete(profileId, formationId);
        return ResponseEntity.noContent().build();
    }
}
