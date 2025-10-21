package dev.danielrl.atomictrip.service;

import dev.danielrl.atomictrip.dto.ServiceResponse;

public interface GenericClientService {

    ServiceResponse sendMessage(String message, int port);
}
