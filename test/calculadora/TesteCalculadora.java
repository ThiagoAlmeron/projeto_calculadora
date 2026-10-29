package calculadora;

/**
 * <p>Classe de testes unitários da {@link Calculadora}.</p>
 *
 * <p>Cada teste exibe no console as <strong>entradas utilizadas</strong>,
 * o <strong>resultado esperado</strong> e o <strong>resultado obtido</strong>,
 * indicando se o teste passou ou falhou. Ao final é exibida a taxa de
 * sucesso: (testes que passaram / total executado) &times; 100.</p>
 *
 * <p>Os testes são executados novamente após a refatoração (teste de
 * regressão), garantindo que o comportamento da calculadora não mudou.</p>
 *
 * @author Thiago Almeron
 * @version 2.0
 */
public class TesteCalculadora {

    /** Quantidade de testes executados. */
    private static int totalTestes = 0;

    /** Quantidade de testes que passaram. */
    private static int testesAprovados = 0;

    /**
     * Construtor privado: a classe só é usada pelo método {@link #main(String[])}.
     */
    private TesteCalculadora() {
    }

    /**
     * Executa todos os testes da calculadora.
     *
     * @param args argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        Calculadora calc = new Calculadora();

        System.out.println("=== TESTES UNITÁRIOS — CALCULADORA FINANÇAPP ===");
        System.out.println();

        // Operações básicas (método calcular)
        verificarResultado("Soma", "2 + 3", 5, calc.calcular(2, 3, "+"));
        verificarResultado("Subtração", "10 - 4", 6, calc.calcular(10, 4, "-"));
        verificarResultado("Multiplicação", "3 * 5", 15, calc.calcular(3, 5, "*"));
        verificarResultado("Divisão", "8 / 2", 4, calc.calcular(8, 2, "/"));

        // Tratamento de erros: devem lançar IllegalArgumentException
        verificarExcecao("Divisão por zero", calc, 8, 0, "/");
        verificarExcecao("Operação inválida", calc, 5, 5, "x");

        // Regressão: o método calc() original continua sem interromper o programa
        System.out.println("--- Regressão: método calc() (comportamento original) ---");
        verificarResultado("calc() divisão por zero", "8 / 0", 0, calc.calc(8, 0, "/"));
        verificarResultado("calc() operação inválida", "5 x 5", 0, calc.calc(5, 5, "x"));

        exibirResumo();
    }

    /**
     * Compara o resultado esperado com o obtido e exibe o relatório do teste.
     *
     * @param nome nome do teste
     * @param entrada descrição das entradas utilizadas (ex.: "2 + 3")
     * @param esperado resultado esperado
     * @param obtido resultado retornado pela calculadora
     */
    private static void verificarResultado(String nome, String entrada, int esperado, int obtido) {
        registrar(nome, entrada, String.valueOf(esperado), String.valueOf(obtido), esperado == obtido);
    }

    /**
     * Verifica se a operação lança {@link IllegalArgumentException}, como esperado
     * para divisão por zero e operadores inválidos.
     *
     * @param nome nome do teste
     * @param calc instância da calculadora testada
     * @param a primeiro operando
     * @param b segundo operando
     * @param operador operador utilizado no teste
     */
    private static void verificarExcecao(String nome, Calculadora calc, int a, int b, String operador) {
        String entrada = a + " " + operador + " " + b;
        String esperado = "IllegalArgumentException";
        try {
            int resultado = calc.calcular(a, b, operador);
            registrar(nome, entrada, esperado, "retornou " + resultado + " (sem exceção)", false);
        } catch (IllegalArgumentException erro) {
            registrar(nome, entrada, esperado, "IllegalArgumentException: " + erro.getMessage(), true);
        }
    }

    /**
     * Imprime o relatório de um teste e atualiza os contadores.
     *
     * @param nome nome do teste
     * @param entrada entradas utilizadas
     * @param esperado resultado esperado
     * @param obtido resultado obtido
     * @param passou {@code true} se o resultado obtido é igual ao esperado
     */
    private static void registrar(String nome, String entrada, String esperado, String obtido, boolean passou) {
        totalTestes++;
        if (passou) {
            testesAprovados++;
        }
        System.out.println("Teste " + totalTestes + ": " + nome);
        System.out.println("  Entrada:  " + entrada);
        System.out.println("  Esperado: " + esperado);
        System.out.println("  Obtido:   " + obtido);
        System.out.println("  Status:   " + (passou ? "PASSOU" : "FALHOU"));
        System.out.println();
    }

    /**
     * Exibe o total de testes, quantos passaram e a taxa de sucesso.
     */
    private static void exibirResumo() {
        double taxaSucesso = (testesAprovados * 100.0) / totalTestes;
        System.out.println("=== RESUMO ===");
        System.out.printf("Testes executados: %d | Aprovados: %d | Taxa de sucesso: %.0f%%%n",
                totalTestes, testesAprovados, taxaSucesso);
    }
}
