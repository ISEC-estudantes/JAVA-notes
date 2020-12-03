package com.github.ISEC_estudantes.PD.exercicios.Aula6;

/*
Ex 16:

Desenvolva, em Java RMI, uma aplicação cliente/servidor que permita obter a hora actual no
sistema que alberga o serviço remoto. Os clientes recebem a localização do serviço através da
linha de comando e terminam depois do resultado ser apresentado na saída standard. O serviço
remoto e o registry onde este se encontra registado sob o nome timeserver correm na mesma
máquina. O resultado é fornecido através de uma instância da classe Hora, sendo esta
constituída pelos atributos static final long serialVersionUID = 1L, protected int horas, protected
int minutos e protected int segundos, bem como pelos métodos públicos Hora(int hora, int
minuto, int segundos), getters e toString. O serviço remoto implementa a interface remota
RemoteTimeInterface, sendo esta constituída apenas pelo método Hora getHora().
 */

public class RMI {
    public static void main(String[] args) {

    }
}
