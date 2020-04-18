package br.com.fiap.service;

import br.com.fiap.model.Trip;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3PhotoBucketService {

    public static String createBucket(Trip trip) {
        AmazonS3 s3Client = AmazonS3ClientBuilder.defaultClient();
        StringBuilder builder = new StringBuilder();
        builder.append(trip.getCountry());
        builder.append("-");
        builder.append(trip.getCity());
        builder.append("-");
        builder.append(trip.getDateTrip());
        builder.append("-");

        int min = 100000;
        int max = 999999;
        int random_int = (int)(Math.random() * (max - min + 1) + min);

        builder.append(random_int);
        s3Client.createBucket(builder.toString());

        return s3Client.getUrl(builder.toString(), "").toString();
    }

}
