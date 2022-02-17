package com.tour.backend;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class HeroModelAssembler implements RepresentationModelAssembler<Hero, EntityModel<Hero>> {
    
    @Override
    public EntityModel<Hero> toModel(Hero hero){
        EntityModel<Hero> heroModel = EntityModel.of(hero,
        linkTo(methodOn(HeroController.class).one(hero.getId())).withSelfRel(),
        linkTo(methodOn(HeroController.class).all()).withRel("all"));

        return heroModel;
    }
}
