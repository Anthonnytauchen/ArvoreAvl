public class Elemento <Tipo> {
    private Tipo valor;
    private Elemento<Tipo> left;
    private Elemento<Tipo> right;
    private int altura;



    public Elemento(Tipo valor) {
        this.valor = valor;
        this.left = null;
        this.right = null;
        this.altura = 1;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }


    public Tipo getValor() {

        return valor;
    }

    public void setValor(Tipo valor) {

        this.valor = valor;
    }

    public Elemento<Tipo> getLeft() {
        return left;
    }

    public void setLeft(Elemento<Tipo> left) {
        this.left = left;
    }

    public Elemento<Tipo> getRight() {
        return right;
    }

    public void setRight(Elemento<Tipo> right) {
        this.right = right;
    }


}
