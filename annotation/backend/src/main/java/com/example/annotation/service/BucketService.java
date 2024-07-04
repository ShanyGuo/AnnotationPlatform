package com.example.annotation.service;

import com.amazonaws.services.s3.model.Bucket;
import com.example.annotation.controller.bucket.vo.FileVo;

import java.io.IOException;
import java.util.List;

public interface BucketService {

    // get list of buckets for given user
    List<Bucket> getBucketList();

    // check if given bucket name valid
    boolean validateBucket(String bucketName);

    // download given objectName from the bucket
    FileVo getObjectFromBucket(String bucketName, String objectName) throws IOException;

    // upload given file as objectName to S3 bucket
    void putObjectIntoBucket(String bucketName, String objectName, String filePathToUpload);

    // create Bucket with provided name (throws exception if bucket already present)
    void createBucket(String bucket);
}
