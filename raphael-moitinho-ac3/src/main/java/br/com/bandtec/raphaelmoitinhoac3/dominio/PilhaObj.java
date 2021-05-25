package br.com.bandtec.raphaelmoitinhoac3.dominio;
public class PilhaObj <T> {

    private Integer topo;
    private T[] pilha;

    public PilhaObj(Integer tamanho) {
        this.topo = -1;
        this.pilha = (T[]) new Object[tamanho];
    }

    public boolean isEmpty(){
        return topo == -1;
    }

    public boolean isFull(){
        return pilha.length == topo + 1;
    }

    public void push(T info){
        if (!isFull()){
            pilha[++topo] = info;
        }else {
            System.out.println("Pilha cheia!");
        }
    }

    public T pop(){
        if (!isEmpty()){
            return pilha[topo--];
        }
        return null;
    }

    public T peek(){
        if (!isEmpty()){
            return pilha[topo];
        }
        return null;
    }

    public void exibe(){
        if (isEmpty()){
            System.out.println("Pilha vazia");
        }else {
            for (Integer i = 0; i < topo+1; i++){
                System.out.println(pilha[i]);
            }
        }

    }

    public PilhaObj<T> multiPop (Integer indice){
        if (indice > topo + 1){
            return null;
        }

        PilhaObj<T> auxiliar = new PilhaObj<T>(indice);
        for (int i = 0; i < indice; i++){
            auxiliar.push(pop());
        }

        //auxiliar.pop();
        return auxiliar;
    }

    public void multPush (PilhaObj<T> auxiliar){
        while (!auxiliar.isEmpty()){
            push(auxiliar.pop());
        }
    }
}

