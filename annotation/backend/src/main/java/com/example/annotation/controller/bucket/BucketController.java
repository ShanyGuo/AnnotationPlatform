package com.example.annotation.controller.bucket;

import com.amazonaws.services.s3.model.Bucket;
import com.example.annotation.controller.bucket.vo.FileVo;
import com.example.annotation.service.BucketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bucket")
@CrossOrigin(origins = "http://localhost:8081")
public class BucketController {
    private static int accessCount = 1;
    private static final int MAX_ACCESS_COUNT = 50;

    @Autowired
    BucketService bucketService;

    @GetMapping
    public void getBucketList() {
        List<Bucket> bucketList = bucketService.getBucketList();
        System.out.println("bucketList:"+bucketList);
    }

    @GetMapping("/downloadObj")
    public ResponseEntity<FileVo> downloadObject() throws Exception {
        String bucketName = "mandarin-source-files";
        String objName = "mandarin_annotator_assignments/mandarin_" + accessCount + ".json";

        if (accessCount >= MAX_ACCESS_COUNT) {
            FileVo end =  new FileVo("end", "Access limit reached. This endpoint can only be accessed 10 times.");
            return ResponseEntity.status(403).body(end);
        }

        accessCount++;
        FileVo fileVO = bucketService.getObjectFromBucket(bucketName, objName);
        return ResponseEntity.ok(fileVO);
    }

    @PostMapping("/uploadObj")
    public void uploadObject(@RequestParam("bucketName") String bucketName, @RequestParam("objName") String objName) throws Exception {
        bucketService.putObjectIntoBucket(bucketName, objName,"opt/test/v1/uploadfile.txt");
    }

    @PostMapping("/createBucket")
    public String createBucket(@RequestParam("bucketName") String bucketName) {
        bucketService.createBucket(bucketName);
        return "done";
    }
}