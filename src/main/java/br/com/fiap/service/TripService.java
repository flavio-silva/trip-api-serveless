package br.com.fiap.service;

import br.com.fiap.dao.TripRepository;
import br.com.fiap.model.Trip;

public class TripService {

    private TripRepository repository = new TripRepository();

    public Trip save(Trip trip) {

        String url = S3PhotoBucketService.createBucket(trip.toString());
        trip.setUrlPhotos(url);
        return repository.save(trip);
    }
}
