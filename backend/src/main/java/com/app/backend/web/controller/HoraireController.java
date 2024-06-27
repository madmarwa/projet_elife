package com.app.backend.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import com.app.backend.business.services.HoraireService;
import com.app.backend.business.services.UserService;
import com.app.backend.dao.entities.Horaire;
import com.app.backend.dao.entities.User;
import com.app.backend.web.dto.HoraireDTO;

@RestController
@RequestMapping("/api/horaire")
public class HoraireController {
    
    private final UserService userService;
    private final HoraireService horaireService;

    public HoraireController(UserService userService,HoraireService horaireService) {
        this.horaireService = horaireService;
        this.userService=userService;
    }

    @GetMapping()
    public ResponseEntity<?> getAllHoraire() {

        List<HoraireDTO> horaires = this.horaireService.getAllHoraires()
                .stream()
                .map(HoraireDTO::toHoraireDTO)
                
                .collect(Collectors.toList());     
        return new ResponseEntity<>(horaires, HttpStatus.OK);


    }
    
    @GetMapping("/doctor/{id}")
    public ResponseEntity<?> getAllHoraireByDoctor(@PathVariable("id") String id) {
        User medecin=userService.findById(id).get();
        List<HoraireDTO> horaires = this.horaireService.findByMedecin(medecin)
                .stream()
                .map(HoraireDTO::toHoraireDTO)
                
                .collect(Collectors.toList());     
        return new ResponseEntity<>(horaires, HttpStatus.OK);

    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getHoraireById(@PathVariable String id) {
        HoraireDTO horaire = HoraireDTO.toHoraireDTO(this.horaireService.findById(id));
        return new ResponseEntity<>(horaire, HttpStatus.OK);
    }

    @PostMapping()
    @PreAuthorize("hasAuthority('WRITE_PRIVILEGE') and hasRole('ADMIN','DOCTOR')")
    public ResponseEntity<?> addHoraire(@RequestBody HoraireDTO horaireDTO)  {
        Horaire horaire = HoraireDTO.fromHoraireDTO(horaireDTO);
        return new ResponseEntity<>(this.horaireService.save(horaire), HttpStatus.CREATED);
    }

    
    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('UPDATE_PRIVILEGE') and hasRole('ADMIN','DOCTOR')")
    public ResponseEntity<?> updateHoraire(@PathVariable String id, @RequestBody HoraireDTO horaireDTO){
        Horaire horaire = HoraireDTO.fromHoraireDTO(horaireDTO);
        return new ResponseEntity<>(this.horaireService.updateHoraire(id, horaire), HttpStatus.OK);

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('DELETE_PRIVILEGE') and hasRole('ADMIN','DOCTOR')")
    public ResponseEntity<?> deleteHoraire(@PathVariable String id) {
        this.horaireService.deleteById(id);
        return new ResponseEntity<>( HttpStatus.NO_CONTENT);
    }

}
