package br.com.fiap.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

class S3PhotoBucketService {

    static String createBucket(String name) {

        name = name.toLowerCase().replace(" ", "-");

        String endpoint = System.getenv("ENDPOINT_OVERRIDE");

        if (endpoint != null && !endpoint.isEmpty()) {
            return encodeValue(name) + ".s3.fake-region.amazonaws.com";
        }

        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
        s3Client.createBucket(name);

        return s3Client.getUrl(name, "").toString();
    }

    static private String encodeValue(String value) {
        try {
            return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return "";
    }

}
