package fr.igorbanque.retrieverservice.controllers;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = DatabaseFacadeControllers.BASE_URI)
@CrossOrigin
public class DatabaseFacadeControllers {

    static final String BASE_URI = "/";
}
