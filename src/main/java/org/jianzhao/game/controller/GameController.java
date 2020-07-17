package org.jianzhao.game.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {

    @GetMapping("/hi")
    public ResponseEntity<?> hi() {
        return ResponseEntity.ok("hello");
    }
}
