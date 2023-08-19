package com.example.trivia.api.v1.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Base Rest Controller.
 * Base class for all REST controllers, used to define common properties and methods at once.
 * ALL controllers MUST extend this class. If they don't, a test will fail.
 */
@Slf4j
@RequestMapping("/api/"+APIDoc.API_VERSION)
public class BaseRestController {

}
