package com.bacsystem.microservices.components.utils;


/**
 * <b>Utility</b>
 * <p>
 * This class specifies the requirements for the {@link com.bacsystem.microservices.components.utils.Utility} component,
 * developed in accordance with the development standards established by christian.
 * Collaboration is encouraged for the enhancement and expansion of the bacsystem-tutorials.
 * </p>
 * <p>
 * <b>Usage:</b>
 * description here!
 * </p>
 *
 * @author christian
 * @author dbacilio88@outllok.es
 * @since 25/11/2024
 */

import com.bacsystem.microservices.components.enums.ResponseCode;
import com.bacsystem.microservices.components.exceptions.MicroservicesException;
import lombok.experimental.UtilityClass;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.util.Optional;
import java.util.function.Supplier;

@UtilityClass
public class Utility {

    /**
     * Método genérico que encuentra una entidad de tipo {@code T} de manera reactiva.
     * Este método acepta un {@link Supplier} que devuelve un {@link Optional} de tipo {@code T}.
     * Si el {@code Optional} no contiene un valor, el método lanzará una excepción
     * {@link MicroservicesException} con un mensaje personalizado.
     *
     * @param <T> El tipo de la entidad que se busca.
     * @param supplier Un {@link Supplier} que devuelve un {@link Optional<T>} de la entidad.
     * @param description Una descripción que se usará para generar el mensaje de error en caso de que la entidad no se encuentre.
     * @return Un {@link Mono<T>} que emite la entidad encontrada o un error si no se encuentra.
     */
    public <T>Mono<T> findEntity(Supplier<Optional<T>> supplier, final String description) {
        // Paso 1: Crea un Mono a partir del Supplier que devuelve un Optional<T>
        // Se utiliza Mono.fromCallable para envolver el código que obtiene la entidad de forma bloqueante.
        return Mono.fromCallable(supplier::get)
        // Paso 2: Convierte el Optional<T> en un Mono<T> reactivo.
        // Si el Optional contiene un valor, emite ese valor en el flujo. Si está vacío, emite un Mono vacío.
            .flatMap(Mono::justOrEmpty)
        // Paso 3: Si el Mono está vacío (no se encontró el valor), lanza un error.
        // El error es una excepción personalizada que indica que la entidad no se encontró.
                .switchIfEmpty(Mono.error(()-> new MicroservicesException(String.format("%s not found",description), ResponseCode.NOT_FOUND)))
        // Paso 4: Ejecuta la tarea bloqueante en un hilo de trabajo adecuado para evitar bloquear el hilo principal.
        // Se utiliza un Scheduler de tipo boundedElastic para manejar operaciones bloqueantes.
            .subscribeOn(Schedulers.boundedElastic());
}
}
