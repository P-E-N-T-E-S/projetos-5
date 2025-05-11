package com.capibyte.acervo.dominio.core.acervo.obra;

public class DoiUtils {
    
    /**
     * Mascara um DOI adicionando pontos e barras
     * Exemplo: 1012345678 -> 10.1234/5678
     */
    public static String mascarar(String doi) {
        if (doi == null || doi.length() < 10) {
            return doi;
        }

        StringBuilder masked = new StringBuilder();
        // Adiciona os primeiros dois dígitos
        masked.append(doi.substring(0, 2));
        masked.append(".");
        // Adiciona os próximos 4 dígitos
        masked.append(doi.substring(2, 6));
        masked.append("/");
        // Adiciona o resto
        masked.append(doi.substring(6));

        return masked.toString();
    }

    /**
     * Desmascara um DOI removendo pontos e barras
     * Exemplo: 10.1234/5678 -> 1012345678
     */
    public static String desmascarar(String doi) {
        if (doi == null) {
            return null;
        }
        return doi.replace(".", "").replace("/", "");
    }

    /**
     * Valida se um DOI está em formato válido
     */
    public static boolean isValid(String doi) {
        if (doi == null || doi.trim().isEmpty()) {
            return false;
        }
        
        // Remove pontos e barras para validar
        String doiLimpo = desmascarar(doi);
        
        // Deve ter pelo menos 10 dígitos
        return doiLimpo.length() >= 10 && doiLimpo.matches("\\d+");
    }
} 