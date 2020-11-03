package es.jpargoteo.fibonacci.service;

import es.jpargoteo.fibonacci.exception.ApiException;

import java.util.List;

public interface ISequenceService {

    List<Long> getSequence(Long number) throws ApiException;
}
