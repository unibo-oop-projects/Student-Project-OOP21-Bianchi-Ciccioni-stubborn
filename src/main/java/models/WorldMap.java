package models;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class WorldMap{

    private static final int BOARD_WIDTH = 50;
    private static final int BOARD_HEIGHT = 50;
    private static final int NUM_ENEMIES = 5;
    private static final int NUM_COLLECTABLES = 15;
    private static Map<Pair<Integer,Integer>,Optional<Entity>> BOARD;
    private Pair<Integer,Integer> playerPosition;
    private SpawnStrategy spawnStrategy;
    
    public WorldMap(){
        this.spawnStrategy = new RandomSpawnStrategy();
        this.playerPosition = new Pair<>(BOARD_WIDTH/2, BOARD_HEIGHT/2);
        //this.board.putDAPPERTUTT(new Optional...)
        /*
        List<Pair<Integer,Integer>> grid = IntStream.rangeClosed(0, BOARD_WIDTH).boxed()
                 .flatMap(x -> IntStream.rangeClosed(0, BOARD_HEIGHT).boxed()
                         .map(y -> new Pair<>(x,y))).collect(Collectors.toList());*/
        BOARD = IntStream.rangeClosed(0, BOARD_WIDTH).boxed()
                .flatMap(x -> IntStream.rangeClosed(0, BOARD_HEIGHT).boxed()
                        .map(y -> new Pair<>(x,y))).collect(Collectors.toMap(x -> x, x -> Optional.empty()));
        BOARD.put(this.playerPosition, Optional.of(new PlayerImpl()));
        this.spawnEntity();
    }
    
    /*
     * METODI DA CREARE: SPAWN PERSONAGGIO, MOVIMENTO PERSONAGGIO, SPAWN COLLECTABLE,
     *  SPAWN NEMICI.
     */
    
    /*
     * pattern strategy utilizzato per lo spawn dei nemici e dei collectable
     */
    private void spawnEntity() {
        /*
        if(entity.getClass() == Player) 
            BOARD.put(this.playerPosition, Optional.of(entity));
        } else {
            Random r = new Random();
            Pair<Integer,Integer> entityPos = new Pair<>(r.nextInt(BOARD_WIDTH), r.nextInt(BOARD_HEIGHT));
            BOARD.put(entityPos, Optional.of(entity));
        }*/
        if(this.spawnStrategy.checkNumPoints(BOARD_WIDTH * BOARD_HEIGHT, NUM_ENEMIES + NUM_COLLECTABLES)) {
            Set<Pair<Integer,Integer>> enSpawnPoints = this.spawnStrategy.getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, NUM_ENEMIES);
            Set<Pair<Integer,Integer>> collectSpawnPoints = this.spawnStrategy.getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, NUM_COLLECTABLES);
            //Set<Pair<Integer,Integer>> spawnPoints = this.spawnStrategy.getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, NUM_ENEMIES + NUM_COLLECTABLES);
            Set<Pair<Integer,Integer>> everyPoint = this.spawnStrategy.getDoubleSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, enSpawnPoints, collectSpawnPoints);
            Iterator<Pair<Integer,Integer>> pointIterator = everyPoint.iterator();
            for(int i = 0; i < NUM_ENEMIES; i ++) {
                BOARD.put(pointIterator.next(), Optional.of(new EnemyImpl()));
            }
            for(int i = NUM_ENEMIES; i < (NUM_ENEMIES + NUM_COLLECTABLES); i ++) {
                BOARD.put(pointIterator.next(), Optional.of(new CollectableImpl()));
            }
        }
        //spawnPoints.forEach(point -> BOARD.put(point, Optional.of(entity)));
        
    }
    
    public void movePlayer(MOVEMENT movement) {
        //if(checkMovement(movement)){
            Entity player = BOARD.replace(this.playerPosition, Optional.empty()).get();
            this.playerPosition = new Pair<>(this.playerPosition.getX() + movement.x, this.playerPosition.getY() + movement.y);
            player.setPosition(this.playerPosition);
            BOARD.put(this.playerPosition, Optional.of(player));
        //}
    }
    
    public Map<Pair<Integer,Integer>,Optional<Entity>> getBoard(){
        return this.BOARD;
    }
    
    /*
     * Scrivere i test dei models!!
     * Cominciare a scrivere note per la relazione del progetto (riguardo DESIGN/ARCHITETTURA e
     * IMPLEMENTAZIONE fatta da me)
     * ROBA DA MIGLIORARE:fare sì che la playerPosition nuova sia gestita da Enum e non da movePlayer stesso
     * bisogna quindi sistemare il BOARD.replace utilizzato perchè nonostante funziona, potrebbe
     * trovarsi in una situazione di Exception.
     * Considerare un sistema di Spawn nel caso di morte dei nemici.
     * Sistemare set delle posizioni dei nemici/collectables utilizzando Combiner
     * Per ogni Collectables dagli un Enum con un attributo per il punteggio (per distinguere cosa succede)
     * Migliorare in caso questo design di Enum dei Collectables
     */
    
    
    /*
     * un player, un enemy, un collectible sono entità, caricate in maniera diversa.
     * interagibili tra loro. Entità può essere un Character o un Collectible (Charater è
     * Player o Enemy). Gerarchia di partenza da sfruttare. Utile anche per le View. Fanno cose diverse
     * a livello logico, ma si visualizzano allo stesso modo (Controller passa alla View degli Entity
     * da visualizzare). Visualizzi Entity sulla mappa con Player con posizione specifica iniziale, spawn
     * iniziale di nemico random, spawn dei collectible random.
     * COLLISIONI: deve creare interfaccia per la collisione dove non richiedo quasi niente nè devo
     * fare troppi metodi a riguardo. Può farlo in diversi modi (tipo con Lambda fornendo la Mappa
     * come le coordinate o i limiti della mappa).
     * Come gestire la mappa (consigli): struttura dati da usare migliore è Map che ha come chiave
     * un Pair di coordinate e come valore un Optional Entity.
     * Molto utile per capire come fare con le collisioni e individuare le Entity presenti (e quali spazi sono
     * occupati). Se così un Character si vuole muovere guardo dove si vuole muovere (controllo
     * se sta uscendo dalla mappa, è uno spazio vuoto, sta andando contro un nemico...)
     */
    
}
