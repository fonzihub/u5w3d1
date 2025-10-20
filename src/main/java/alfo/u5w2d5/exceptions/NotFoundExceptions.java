package alfo.u5w2d5.exceptions;

import java.util.UUID;

public class NotFoundExceptions extends RuntimeException {
    public NotFoundExceptions(UUID uuid) {
        super("questo: " + uuid + " non è stato trovato");
    }
    public  NotFoundExceptions(String message) {
        super(message);
    }
}
