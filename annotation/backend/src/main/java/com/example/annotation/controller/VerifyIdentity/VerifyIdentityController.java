package com.example.annotation.controller.VerifyIdentity;


import com.example.annotation.controller.VerifyIdentity.dto.VerifyIdentityDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/Verifie")
@CrossOrigin(origins = "http://localhost:8081")
public class VerifyIdentityController {

//    @Autowired
//    VerifyIdentityService verifyIdentityService;

    private static HashMap<String, String> identityMap = new HashMap<>();

    @PostMapping("/verify-identity")
    public ResponseEntity<String> verifyIdentity(@RequestBody VerifyIdentityDto dto) {
        // 处理接收到的数据
        //System.out.println("Received Data: " + dto.getName() + ", " + dto.getIdNumber());
        String name = dto.getName();
        String fpsId = dto.getFpsId();

        if (identityMap.containsKey(name) && identityMap.get(name).equals(fpsId)) {
            return ResponseEntity.status(409).body("Duplicate identity detected");
        }

        identityMap.put(name, fpsId);
        return ResponseEntity.ok("Identity verified successfully");
    }
}
