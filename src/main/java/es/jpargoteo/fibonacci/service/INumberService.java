package es.jpargoteo.fibonacci.service;

import es.jpargoteo.fibonacci.exception.ApiException;

public interface INumberService {

    Long getNumber(Long number) throws ApiException;
}
