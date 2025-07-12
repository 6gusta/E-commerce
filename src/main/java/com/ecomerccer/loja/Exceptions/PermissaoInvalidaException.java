package com.ecomerccer.loja.Exceptions;

public class PermissaoInvalidaException extends RuntimeException {
    public PermissaoInvalidaException(String message) {
        super(message);
    }

    public class SenhaIncorretaException extends RuntimeException {
        public SenhaIncorretaException(String msg) {
            super(msg);
        }
    }


    public class UsuarioNaoEncontradoException extends RuntimeException {
        public UsuarioNaoEncontradoException(String msg) {
            super(msg);
        }
    }


}
