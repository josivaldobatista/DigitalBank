package com.jfb.digital_banking_data.utils;

public class MaskUtil {

    public static  String maskCpfCnpj(String cpfCnpj) {
        if (cpfCnpj == null) {
            return null;
        } else if (cpfCnpj.length() == 11) {
            // CPF - Mascarar os primeiros 7 dígitos e mostrar os 4 últimos
            return "*******" + cpfCnpj.substring(7);
        } else if (cpfCnpj.length() == 14) {
            // CNPJ - Mascarar os primeiros 10 dígitos e mostrar os 4 últimos
            return "**********" + cpfCnpj.substring(10);
        } else {
            return cpfCnpj; // Retorna o valor original se não for um CPF ou CNPJ válido
        }
    }

}

