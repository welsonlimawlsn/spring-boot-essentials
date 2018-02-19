package br.com.welson.springboot.error;

public class CustomErrorType {

    private String messageError;

    public CustomErrorType(String messageError) {
        this.messageError = messageError;
    }

    public String getMessageError() {
        return messageError;
    }
}
