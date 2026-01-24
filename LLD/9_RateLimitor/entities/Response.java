package entities;

public record Response(boolean success, int httpcode, String message){}