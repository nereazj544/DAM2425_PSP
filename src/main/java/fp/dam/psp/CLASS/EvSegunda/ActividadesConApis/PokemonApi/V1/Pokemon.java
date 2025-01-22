package fp.dam.psp.CLASS.EvSegunda.ActividadesConApis.PokemonApi.V1;

import java.util.List;

public class Pokemon {

    // ! NOTA SUPER IMPORTANTE HAY QUE PONER LOS NOMBRES TAL CUAL SALEN EN EL JSON,
    // SINO NO CARRULARRA

    public String name;
    public List<AbilityWrapper> abilities;
    public List<StatsWrapper> stats;
    public List<TypesWrapper> types;

    public static class AbilityWrapper {
        public Ability ability;
    }

    public static class TypesWrapper {
        public Types type;
    }

    public static class StatsWrapper {
        public Stats stat;
        public int base_stat;
    }

    public static class Types {
        public String name;
    }

    public static class Stats {
        public String name;
    }

    public static class Ability {
        public String name;
    }

    /*
     * 
     * String nombre;
     * List<Habilidades> habilidades;
     * 
     * public static class Habilidades {
     * public Habilidades habilidades;
     * }
     * 
     * public static class Habi {
     * public String nombre;
     * 
     * }
     */
}
