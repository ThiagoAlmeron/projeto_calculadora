package calculadora;

/**
 * Teste no modelo apresentado em aula (slide de refatoração), usando o
 * novo método {@link Calculadora#calcular(int, int, String)} e capturando
 * as exceções de divisão por zero e operação inválida.
 *
 * @author Thiago Almeron
 * @version 2.0
 */
public class TesteCalculadoraProfessor {

    /**
     * Construtor privado: a classe só é usada pelo método {@link #main(String[])}.
     */
    private TesteCalculadoraProfessor() {
    }

    /**
     * Executa os testes no formato do slide da aula.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println(calc.calcular(2, 3, "+"));
        System.out.println(calc.calcular(10, 4, "-"));
        System.out.println(calc.calcular(3, 5, "*"));
        System.out.println(calc.calcular(8, 2, "/"));

        try {
            System.out.println(calc.calcular(8, 0, "/")); // exceção
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        try {
            System.out.println(calc.calcular(5, 5, "x")); // exceção
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}
