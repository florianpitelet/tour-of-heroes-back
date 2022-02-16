package com.tour.backend;

public class Hero404 extends RuntimeException {
    Hero404(Long id){
        super("Could not find hero" + id);
    }
}
