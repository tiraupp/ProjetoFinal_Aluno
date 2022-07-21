package util;

public abstract class Contador {
    private static int VALOR = 0;

    public static int proximoId(){
        VALOR++;
        return VALOR;
    }
}
