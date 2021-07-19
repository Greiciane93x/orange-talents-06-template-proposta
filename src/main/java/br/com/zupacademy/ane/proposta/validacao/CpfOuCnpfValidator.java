package br.com.zupacademy.ane.proposta.validacao;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.InputMismatchException;


/**
 * Entendendo o conceito de validação para o Cpf ou Cnpj com o link abaixo
 * https://clairtonluz.github.io/blog/2014/07/2014072299.html
 */
public class CpfOuCnpfValidator implements ConstraintValidator<CpfOuCnpj, String> {
    @Override
    public void initialize(CpfOuCnpj constraintAnnotation) {}

    @Override
    public boolean isValid(String valor, ConstraintValidatorContext context) {

        return valor == null || valor.isEmpty() || isCpf(valor) || isCnpj(valor);
    }

    /**
     * Faz a validação para o CPF
     */

    private boolean isCpf(String cpf) {
        // troca ponto e traço por vazio
        cpf = cpf.replace(".", "");
        cpf = cpf.replace("-", "");

        try {
            Long.parseLong(cpf);
        } catch (NumberFormatException e) {
            return false;
        }
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            // Multiplicando a última casa por 2  e a seguinte por 3
            // a seguinte por 4 e assim por diante
            d1 = d1 + (11 - nCount) * digitoCPF;

            // Para o segundo digito repita o procedimento incluindo o
            // primeiro digito calculado no passo anterior
            d2 = d2 + (12 - nCount) * digitoCPF;
        }
        ;

        // Resto da divisão por 11
        resto = (d1 % 11);

        // Se o resulto da divisão for 0 ou 1 o dígito é 0
        // Caso o contrário o dígito é 11 menos o resulto anterior
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        // Segundo resto da divsão por 11
        resto = (d2 % 11);

        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        // Digito verificador do CPF que está sendo validado
        String nDigVerificador = cpf.substring(cpf.length() - 2, cpf.length());

        // Concatena o primeiro resto com o segundo
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //Compra o dígito verificador do cpf com o primeiro resto + o
        // segundo resto

        return nDigVerificador.equals(nDigResult);

        }
    /**
     *  Abaixo realiza a validação por cnpj
     */

    private boolean isCnpj(String cnpj){
        cnpj = cnpj.replace(".", "");
        cnpj = cnpj.replace("-", "");
        cnpj = cnpj.replace("/", "");

        try{
            Long.parseLong(cnpj);
        }catch (NumberFormatException e){
            return false;
        }

        // Aqui retorna false se os CPNJs forem formados por uma seqfuencia de numeros
        if (cnpj.equals("00000000000000") || cnpj.equals("11111111111111")
                || cnpj.equals("22222222222222") || cnpj.equals("33333333333333")
                || cnpj.equals("44444444444444") || cnpj.equals("55555555555555")
                || cnpj.equals("66666666666666") || cnpj.equals("77777777777777")
                || cnpj.equals("88888888888888") || cnpj.equals("99999999999999") || (cnpj.length() != 14))
            return (false);

        char dig13, dig14;
        int sm, i, r, num, peso;
        try {
            // Calcula o primeiro dígito verificador
            sm = 0;
            peso = 2;
            for (i = 11; i >= 0; i--) {
                // Converte o i-ésimo caracter do CNPJ em número
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }

            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig13 = '0';
            else
                dig13 = (char) ((11 - r) + 48);

            // Calcula o segund dígito verificador
            sm = 0;
            peso = 2;
            for (i = 12; i >= 0; i--) {
                num = (int) (cnpj.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso + 1;
                if (peso == 10)
                    peso = 2;
            }
            r = sm % 11;
            if ((r == 0) || (r == 1))
                dig14 = '0';
            else
                dig14 = (char) ((11 - r) + 48);
            // Verifica se os dígitos calculados conferem com os dígitos
            // informados.
            if ((dig13 == cnpj.charAt(12)) && (dig14 == cnpj.charAt(13)))
                return (true);
            else
                return (false);
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}