package com.bach.api.api.rests;

import com.bach.api.api.types.DTOActualizacionVideo;
import com.bach.api.api.types.DTORegistroVideo;
import com.bach.api.api.types.DTORespuestaVideo;
import com.bach.api.config.security.TokenService;
import com.bach.api.jpa.entities.Video;
import com.bach.api.jpa.enums.Role;
import com.bach.api.jpa.repositories.MentoriaRepository;
import com.bach.api.jpa.repositories.VideoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/videos")
public class VideoController {

    @Autowired
    private VideoRepository repository;

    @Autowired
    private MentoriaRepository mentoriaRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("video-upload/{idMentoria}")
    public ResponseEntity<DTORespuestaVideo> subirVideo(@PathVariable Long idMentoria, @Valid
                                                        @RequestBody DTORegistroVideo datos, @RequestHeader("Authorization") String token){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var mentoriaOptional = mentoriaRepository.findById(idMentoria);
        if (mentoriaOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var metoria = mentoriaOptional.get();
        var video = new Video(datos);
        repository.save(video);
        video.getMentorias().add(metoria);
        metoria.getVideos().add(video);
        mentoriaRepository.save(metoria);
        var datosRetorno = new DTORespuestaVideo(video);
        return ResponseEntity.ok(datosRetorno);
    }

    @PutMapping("/actualizar-video/{idVideo}")
    @Transactional
    public ResponseEntity<DTORespuestaVideo> actualizaVideo(@PathVariable Long idVideo,
                                                            @RequestBody DTOActualizacionVideo datos, @RequestHeader("Authorization") String token){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var videoOptional = repository.findById(idVideo);
        if (videoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var video = videoOptional.get();
        video.actualiza(datos);
        var datosRespuesta = new DTORespuestaVideo(video);
        return ResponseEntity.ok(datosRespuesta);
    }

    @GetMapping("/videos-por-mentoria/{idMentoria}")
    public ResponseEntity<Page<DTORespuestaVideo>> obtenVideosPorMentria(@PathVariable Long idMentoria, Pageable pageable){
     var videos = repository.findByMentoriasId(idMentoria, pageable).map(DTORespuestaVideo::new);
     if (videos.isEmpty()){
         return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
     }
     return ResponseEntity.ok(videos);
    }

    @GetMapping("/videos-por-nombre/{video}")
    public ResponseEntity<Page<DTORespuestaVideo>> obtenVideosPorMentria(@PathVariable String video, Pageable pageable){
        var videos = repository.findByTituloIgnoreCaseContaining(video, pageable).map(DTORespuestaVideo::new);
        if (videos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(videos);
    }

    @DeleteMapping("/{idVideo}")
    public ResponseEntity borraVideo(@PathVariable Long idVideo, @RequestHeader("Authorization") String token){
        var rolDeUsuario = Role.valueOf(tokenService.getClaimrol(token));
        if (rolDeUsuario != Role.ADMIN && rolDeUsuario != Role.MENTOR){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var videoOptional = repository.findById(idVideo);
        if (videoOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        var video = videoOptional.get();
        repository.delete(video);
        return  ResponseEntity.ok().build();
    }
}
