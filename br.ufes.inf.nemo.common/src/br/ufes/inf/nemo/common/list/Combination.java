package br.ufes.inf.nemo.common.list;

import java.util.ArrayList;
import java.util.HashSet;

public class Combination {
	
    private int r ;
    
    @SuppressWarnings("rawtypes")
	private ArrayList input ;
    
    private int MAX ;
    
    private int N ;

    /** se r e' zero entao iremos fazer todas
     *  as combinacoes (com qualquer quantidade
     *  de elementos).
     */
    public <T> Combination(ArrayList<T> input, int r) {
        this.r = r ;
        this.input = input ;
        this.MAX = ~(1 << input.size()) ;
        this.N = 1;
    }

    public <T> Combination(HashSet<T> input, int r) {
		this(new ArrayList<T>(input), r);
	}

	/** Retorna true quando ha pelo menos
     *  uma combinacao disponivel.
     */
    public boolean hasNext() {
        if ( r != 0 ) {
            while ( ((this.N & this.MAX) != 0) && (countbits() != r) ) N+=1 ;
        }

        return (this.N & this.MAX) != 0;
    }

    /** Retorna a quantidade de bits ativos (= 1)
     * de N.
     */
    private int countbits() {
        int i;
        int c;

        i = 1;
        c = 0;
        while ( (this.MAX & i) != 0 ) {
            if ( (this.N & i) != 0) {
                c++;
            }
            i = i << 1 ;
        }

        return c ;
    }

    /** Util para obter o tamanho da saida. Esse
     * tamanho e' o numero de posicoes do vetor
     * retornado por next.
     */
    public int getOutputLength() {
        if (r != 0) {
            return r;
        }

        return this.countbits();
    }

    /** Retorna uma combinacao.
     *
     * ATENCAO: Sempre use next() quando se
     * tem certeza que ha uma combinacao
     * disponivel. Ou seja, sempre use next()
     * quando hasNext() retornar true.
     */
    @SuppressWarnings("unchecked")
	public <T> ArrayList<T> next() {
        int input_index, i;

        ArrayList<T> output = new ArrayList<T>();

        input_index = 0;
        i = 1;

        while ((this.MAX & i) != 0) {
            if ((this.N & i) != 0) {
                output.add((T) input.get(input_index));
            }
            input_index += 1;
            i = i << 1;
        }

        N += 1;

        return output;
    }
    
    public static void main(String[] args) {
    
    }
}
