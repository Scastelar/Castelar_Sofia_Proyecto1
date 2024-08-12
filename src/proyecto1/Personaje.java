
package proyecto1;


public enum Personaje {
   //HEROES 
   Tierra("Tierra","Heroes",0),
   NovaBlast("NovaBlast","Heroes",0),
   BlackWidow("BlackWidow", "Heroes", 1),
   Gambit("Gambit", "Heroes", 2),
   SpiderGirl("SpiderGirl", "Heroes", 2),
   IceMan("IceMan", "Heroes", 2),  
   Storm("Storm", "Heroes", 2),
   Phoenix("Phoenix", "Heroes", 2),
   DrStrange("DrStrange", "Heroes", 2),
   Elektra("Elektra", "Heroes", 2),
   NightCrawler("NightCrawler", "Heroes", 2),
   EmmaFrost("EmmaFrost", "Heroes", 3),
   SheHulk("SheHulk", "Heroes", 3),
   GiantMan("GiantMan", "Heroes", 3),
   Colossus("Colossus", "Heroes", 3),
   Beast("Beast", "Heroes", 3),
   Blade("Blade", "Heroes", 4),
   Thing("Thing", "Heroes", 4),
   Punisher("Punisher", "Heroes", 4),
   InvisibleWoman("InvisibleWoman", "Heroes", 5),
   GhostRider("GhostRider", "Heroes", 4),
   Cyclops("Cyclops", "Heroes", 5),
   HumanTorch("HumanTorch", "Heroes", 5),
   Thor("Thor", "Heroes", 5),
   SilverSurfer("SilverSurfer", "Heroes", 6),
   Daredevil("Daredevil", "Heroes", 6),
   Hulk("Hulk", "Heroes", 6),
   IronMan("IronMan", "Heroes", 6),
   SpiderMan("SpiderMan", "Heroes", 7),
   Wolverine("Wolverine", "Heroes", 7),
   Namor("Namor", "Heroes", 7),
   NickFury("NickFury", "Heroes", 8),
   CapitanAmerica("CapitanAmerica", "Heroes", 9),
   ProfessorX("ProfessorX", "Heroes", 9),
   MrFantastic("MrFantastic", "Heroes", 10),
   
   //VILLANOS
   TierraObtenida("TierraObtenida", "Villanos", 0),
   PumpkinBomb("PumpkinBomb", "Villanos", 0),
   BLACKWIDOW("BlackWidow", "Villanos", 1),
   MrSinister("MrSinister", "Villanos", 2),
   Sentinel1("Sentinel1", "Villanos",  2),
   Sentinel2("Sentinel2", "Villanos",  2),  
   Ultron("Ultron", "Villanos", 2),
   Sandman("Sandman", "Villanos",2),
   Leader("Leader", "Villanos", 2),
   Viper("Viper", "Villanos",  2),
   Electro("Electro", "Villanos", 2),
   Juggernaut("Juggernaut", "Villanos", 3),
   Rhino("Rhino", "Villanos", 3),
   Carnage("Carnage", "Villanos",  3),
   Moleman("Moleman", "Villanos", 3),
   Lizard("Lizard", "Villanos", 3),
   Abomination("Abomination", "Villanos",  4),
   BlackCat("BlackCat", "Villanos", 4),
   Sabretooth("Sabretooth", "Villanos", 4),
   Thanos("Thanos", "Villanos", 4),
   Deadpool("Deadpool", "Villanos", 5),
   Mysterio("Mysterio", "Villanos", 5),
   DrOctopus("DrOctopus", "Villanos", 5),
   Mystique("Mystique", "Villanos",  5),
   Onslaught("Onslaught", "Villanos", 6),
   OmegaRed("OmegaRed", "Villanos",  6),
   RedSkull("RedSkull", "Villanos", 6),
   Bullseye("Bullseye", "Villanos", 6),
   Venom("Venom", "Villanos", 7),
   Apocalypse("Apocalypse", "Villanos", 7),
   GreenGoblin("GreenGoblin", "Villanos", 7),
   Magneto("Magneto", "Villanos", 8),
   Kingpin("Kingpin", "Villanos", 8),
   Galactus("Galactus", "Villanos", 9),
   DrDoom("DrDoom", "Villanos",  10);
           
    private final String nombre;
    private final String bando;
    private final int rango;
    private int fila;
    private int columna;
    private boolean seleccionado;
    
    Personaje(String nombre, String bando, int rango){
        this.nombre=nombre;
        this.bando=bando;
        this.rango=rango;
    }
    
    public String getName(){
        return nombre;
    }
    public String getBando(){
        return bando;
    }
    public int getRango(){
        return rango;
    }
 
}
    
    
    
