
import java.io.Serializable;
import java.util.Objects;

public class Hora implements Serializable {
    protected final int horas, minutos, segundos;
    static final long serialVersionUID = 1L;

    public Hora(int hora, int minuto, int segundos) {
        this.horas = hora;
        this.minutos = minuto;
        this.segundos = segundos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hora hora = (Hora) o;
        return horas == hora.horas &&
                minutos == hora.minutos &&
                segundos == hora.segundos;
    }

    @Override
    public int hashCode() {
        return Objects.hash(horas, minutos, segundos);
    }

    @Override
    public String toString() {
        return "Hora{" +
                "horas=" + horas +
                ", minutos=" + minutos +
                ", segundos=" + segundos +
                '}';
    }


    public int getHoras() {
        return horas;
    }

    public int getMinutos() {
        return minutos;
    }

    public int getSegundos() {
        return segundos;
    }
}
