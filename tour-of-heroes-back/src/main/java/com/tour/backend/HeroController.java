package com.tour.backend;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.mediatype.problem.Problem;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HeroController {

    private final HeroRepo repository;

    private final HeroModelAssembler assembler;

    HeroController(HeroRepo repository, HeroModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    // RECUPERATION D UN ELEMENT
    @GetMapping("/hero/{id}")
    EntityModel<Hero> one(@PathVariable Long id) {

        Hero hero = repository.findById(id)
                .orElseThrow(() -> new Hero404(id));
        return assembler.toModel(hero);

    }

    // RECUPERATION DE TOUS LES ELEMENTS
    @GetMapping("/all")
    CollectionModel<EntityModel<Hero>> all() {
        List<EntityModel<Hero>> heroList = repository.findAll().stream()
                .map(assembler::toModel)
                .collect(Collectors.toList());
        return CollectionModel.of(heroList,
                linkTo(methodOn(HeroController.class).all()).withSelfRel());
    }

    // CEATION D UN NOUVEL ELEMENT
    @PostMapping("/all")
    Hero newHero(@RequestBody Hero newHero) {
        return repository.save(newHero);
    }

    // UPDATE D UN ELEMENT
    @PutMapping("/hero/{id}")
    ResponseEntity<?> replaceHero(@RequestBody Hero newHero, @PathVariable Long id) {
        Hero updateHero = repository.findById(id)
                .map(hero -> {
                    hero.setName(newHero.getName());
                    hero.setPower(newHero.getPower());
                    return repository.save(hero);
                })
                .orElseGet(() -> {
                    newHero.setId(id);
                    return repository.save(newHero);
                });

        EntityModel<Hero> entityModel = assembler.toModel(updateHero);

        return ResponseEntity
                .created(entityModel.getRequiredLink(IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    // SUPPRESSION D UN HERO
    @DeleteMapping("/hero/{id}")
    ResponseEntity<?> deleteHero(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

}
