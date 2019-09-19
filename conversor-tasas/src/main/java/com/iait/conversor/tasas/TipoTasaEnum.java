package com.iait.conversor.tasas;

import java.util.HashMap;
import java.util.Map;

import org.springframework.util.Assert;

public enum TipoTasaEnum {

    EFECTIVA(1),
    NOMINAL_ADELANTADA(2),
    NOMINAL_VENCIDA(3),
    ;

    private Integer id;

    private TipoTasaEnum(Integer id) {
        this.id = id;
    }

    private static final Map<Integer, TipoTasaEnum> map;

    static {
        map = new HashMap<>();
        for (TipoTasaEnum tipoTasa : TipoTasaEnum.values()) {
            map.put(tipoTasa.id, tipoTasa);
        }
    }

    public static TipoTasaEnum of(Integer id) {
        if (id == null) {
            return null;
        }
        TipoTasaEnum tipo = map.get(id);
        Assert.notNull(tipo, String.format("Tipo de tasa incorrecto: %s", id));
        return tipo;
    }
}
