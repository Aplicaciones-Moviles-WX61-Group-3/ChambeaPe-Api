package com.digitaldark.ChambeaPe_Api.shared;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
public class DateTimeEntity {
    public Timestamp currentTime(){
        // Obtiene la hora actual en milisegundos
        long currentTimeMillis = System.currentTimeMillis();

        // Crea un objeto Timestamp con la hora actual
        Timestamp timestamp = new Timestamp(currentTimeMillis);

        return timestamp;
    }
}
