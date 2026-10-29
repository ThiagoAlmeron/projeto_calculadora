package calculadora;

/**
 * <p><strong>Projeto Calculadora — FinançApp</strong></p>
 *
 * <p>Esta classe fornece as operações matemáticas básicas (soma, subtração,
 * multiplicação e divisão) utilizadas pelo aplicativo FinançApp em
 * funcionalidades como orçamento mensal, divisão de despesas e projeção
 * de economia.</p>
 *
 * <p>Esse projeto é utilizado para demonstrar:</p>
 * <ul>
 *     <li>Testes unitários e funcionais (caixa preta e caixa branca);</li>
 *     <li>Tratamento de erros com exceções;</li>
 *     <li>Aplicação de refatoração em código Java;</li>
 *     <li>Documentação com JavaDoc.</li>
 * </ul>
 *
 * <p>Após a refatoração, cada operação foi extraída para um
 * <strong>método puro</strong> (sem efeitos colaterais), o que melhora a
 * legibilidade e facilita os testes unitários. O método {@link #calc(int, int, String)}
 * foi mantido para preservar o comportamento original da classe.</p>
 *
 * @author Thiago Almeron
 * @version 2.0
 */
public class Calculadora {

    /**
     * Guarda o resultado da última operação executada pelo método
     * {@link #calc(int, int, String)}. Substitui o antigo atributo {@code r}.
     */
    private int ultimoResultado = 0;

    /**
     * Cria uma nova calculadora com o último resultado igual a {@code 0}.
     */
    public Calculadora() {
    }

    /**
     * Soma dois números inteiros.
     *
     * @param a primeiro operando
     * @param b segundo operando
     * @return o resultado da soma de {@code a} e {@code b}
     */
    public int somar(int a, int b) {
        return a + b;
    }

    /**
     * Subtrai o segundo número do primeiro.
     *
     * @param a minuendo (valor do qual será subtraído)
     * @param b subtraendo (valor a ser subtraído)
     * @return o resultado de {@code a - b}
     */
    public int subtrair(int a, int b) {
        return a - b;
    }

    /**
     * Multiplica dois números inteiros.
     *
     * @param a primeiro fator
     * @param b segundo fator
     * @return o produto de {@code a} por {@code b}
     */
    public int multiplicar(int a, int b) {
        return a * b;
    }

    /**
     * Divide o primeiro número pelo segundo (divisão inteira).
     *
     * <p>Exemplo de uso no FinançApp: dividir a conta do restaurante
     * entre os amigos.</p>
     *
     * @param a dividendo
     * @param b divisor (não pode ser zero)
     * @return o quociente inteiro de {@code a / b}
     * @throws IllegalArgumentException se o divisor {@code b} for igual a zero
     */
    public int dividir(int a, int b) {
        // Cláusula de guarda: impede a divisão por zero antes de calcular
        if (b == 0) {
            throw new IllegalArgumentException("Erro: divisão por zero não é permitida.");
        }
        return a / b;
    }

    /**
     * <p>Executa a operação matemática solicitada com base no operador informado.</p>
     *
     * <p>Operadores aceitos:</p>
     * <ul>
     *     <li>"+" : Soma</li>
     *     <li>"-" : Subtração</li>
     *     <li>"*" : Multiplicação</li>
     *     <li>"/" : Divisão</li>
     * </ul>
     *
     * <p>Qualquer operador inválido (ou nulo) resulta em exceção.</p>
     *
     * @param a primeiro operando
     * @param b segundo operando
     * @param operador símbolo da operação desejada
     * @return o resultado da operação correspondente ao operador informado
     * @throws IllegalArgumentException se o operador for nulo, não for um
     *         símbolo válido, ou se houver tentativa de divisão por zero
     */
    public int calcular(int a, int b, String operador) {
        // Operador nulo também é tratado como inválido (evita NullPointerException)
        if (operador == null) {
            throw new IllegalArgumentException("Operador inválido: null");
        }

        // switch substitui a cadeia de if/else if da versão original
        return switch (operador) {
            case "+" -> somar(a, b);
            case "-" -> subtrair(a, b);
            case "*" -> multiplicar(a, b);
            case "/" -> dividir(a, b);
            default -> throw new IllegalArgumentException("Operador inválido: " + operador);
        };
    }

    /**
     * Método principal exigido pela especificação: {@code calc(int a, int b, String op)}.
     *
     * <p>Mantém o comportamento da versão original: nunca interrompe a
     * execução do programa. Em caso de erro (divisão por zero ou operação
     * inválida), exibe uma mensagem informativa no console e retorna {@code 0}.</p>
     *
     * @param a primeiro operando
     * @param b segundo operando
     * @param op símbolo da operação ("+", "-", "*" ou "/")
     * @return o resultado da operação, ou {@code 0} se a operação for inválida
     *         ou houver divisão por zero
     */
    public int calc(int a, int b, String op) {
        try {
            ultimoResultado = calcular(a, b, op);
        } catch (IllegalArgumentException erro) {
            // Erro tratado: mostra a mensagem e segue a execução normalmente
            System.out.println(erro.getMessage());
            ultimoResultado = 0;
        }

        System.out.println("Resultado = " + ultimoResultado);
        return ultimoResultado;
    }

    /**
     * Retorna o resultado da última operação executada por {@link #calc(int, int, String)}.
     *
     * @return o último resultado calculado (ou {@code 0} se nenhuma operação foi feita)
     */
    public int getUltimoResultado() {
        return ultimoResultado;
    }
}
