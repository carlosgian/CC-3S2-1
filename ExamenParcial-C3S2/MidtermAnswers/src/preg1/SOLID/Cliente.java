package preg1.SOLID;

import java.util.List;

public class Cliente {
    public static void main(String[] args) {
        System.out.println("Version con LSP");

        //Instanciando tres miembros
        PremiunMember premiun = new PremiunMember("Abejita azul");
        VipMember vip = new VipMember("Kaperucita Feliz");
        FreeMember free = new FreeMember("Inspectora Motita");

        //Se organizan dos torneos
        premiun.organizeTournament();
        vip.organizeTournament();

        //Luego proceden a unirts
        premiun.joinTournament();
        vip.joinTournament();
        free.joinTournament();
    }
}
