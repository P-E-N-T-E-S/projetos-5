package com.capibyte.acervo.dominio.core.acervo.livro;
import org.apache.commons.validator.routines.ISBNValidator;


public class Isbn10 extends Isbn{
    public Isbn10(String codigo) {
        super(codigo);
    }

    @Override
    boolean testarCodigo(String codigo) {
        return ISBNValidator.getInstance().isValidISBN10(codigo);
    }
}
