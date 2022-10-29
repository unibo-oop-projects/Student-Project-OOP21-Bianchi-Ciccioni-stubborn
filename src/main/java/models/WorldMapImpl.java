package models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class WorldMapImpl implements WorldMap{

    private int board_width;
    private int board_height;
    private int num_enemies;
    private int num_collectables;
    private Map<Point2D,Optional<Entity>> board;
    private Point2D playerPosition;
    private SpawnStrategy spawnStrategy;
    private CollisionStrategy collisionStrategy;
    
    public WorldMapImpl(int width, int height, int enemies, int collectables, SpawnStrategy strategy) {
        this.board_width = width;
        this.board_height = height;
        this.num_enemies = enemies;
        this.num_collectables = collectables;
        this.spawnStrategy = strategy;
        this.collisionStrategy = new CollisionImpl();
        this.playerPosition = new Point2D(board_width/2, board_height/2);
        this.board = IntStream.range(0, board_width).boxed()
                    .flatMap(x -> IntStream.range(0, board_height).boxed()
                    .map(y -> new Point2D(x,y))).collect(Collectors.toMap(x -> x, x -> Optional.empty()));
        this.board.put(this.playerPosition, Optional.of(new PlayerImpl(this.playerPosition, 3)));
        this.spawnEntity();
    }
    
    /*
     * pattern strategy utilized in order to spawn each entity in the worldmap at the start of the game.
     * we decided to use a RandomSpawnStrategy to create random possible positions where our entities
     * will be instantiated. It internally uses a 2-way combinator pattern: at first, it generates
     * a Set of positions for the enemies, then for the collectables; after that, it combines
     * this two sets and checks if there are duplicates, regenerating new positions if so.
     */
    private void spawnEntity() {
        if(this.spawnStrategy.checkNumPoints(this.board_width * this.board_height, this.num_enemies + this.num_collectables)) {
          //Set<Pair<Integer,Integer>> spawnPoints = this.spawnStrategy.getSpawnPoints(BOARD_WIDTH, BOARD_HEIGHT, NUM_ENEMIES + NUM_COLLECTABLES);
            Set<Point2D> enSpawnPoints = this.spawnStrategy.getSpawnPoints(this.board_width, this.board_height, this.num_enemies);
            Set<Point2D> collectSpawnPoints = this.spawnStrategy.getSpawnPoints(this.board_width, this.board_height, this.num_collectables);
            Set<Point2D> everyPoint = this.spawnStrategy.getDoubleSpawnPoints(this.board_width, this.board_height, enSpawnPoints, collectSpawnPoints);
            Iterator<Point2D> pointIterator = everyPoint.iterator();
            for(int i = 0; i < this.num_enemies; i ++) {
                Point2D enemyPos = pointIterator.next();
                this.board.put(enemyPos, Optional.of(new EnemyImpl(enemyPos, 1, this.getEnemyAi())));
            }
            for(int i = this.num_enemies; i < (this.num_enemies + this.num_collectables); i ++) {
                this.board.put(pointIterator.next(), Optional.of(new CollectableImpl()));
            }
        }
    }
    
    /*
     * this is the logical method used to move the player character: after receiving a specific
     * movement enum, we check if there is a possible collision with an enemy or the worldmap boundaries;
     * if not, then we move the player to a nearby cell of our map based on the input movement.
     */
    @Override
    public void movePlayer(MOVEMENT movement) {
        Point2D newPos = Point2D.sum(this.playerPosition, movement.movement);
        if(!this.collisionStrategy.checkCollisions(this.getBoard(), newPos, this.board_width, this.board_height)){
            Entity player = this.board.replace(this.playerPosition, Optional.empty()).get();
            this.playerPosition = newPos;
            player.setPosition(this.playerPosition);
            this.board.put(this.playerPosition, Optional.of(player));
        }
    }
    
    @Override
    public Map<Point2D,Optional<Entity>> getBoard() {
        return this.board;
    }
    
    @Override
    public Point2D getPlayerPos() {
        return this.playerPosition;
    }
    
    @Override
    public List<Pair<Point2D,Class<? extends Entity>>> getEntitiesPos() {
        List<Pair<Point2D,Class<? extends Entity>>> entitiesPos = new ArrayList<>();
        for(Entry<Point2D, Optional<Entity>> i : this.board.entrySet()) {
            if(i.getValue().isPresent() && !i.getKey().equals(this.getPlayerPos())) {
                entitiesPos.add(new Pair<>(i.getKey(), i.getValue().get().getClass()));
            }
        }
        return entitiesPos;
    }
    
    /*
     * this is used to set a possible random Ai to an enemy
     */
    private AiEnemy getEnemyAi() {
        Random r = new Random();
        int randomSelect = r.nextInt(2);
        return randomSelect == 0 ? new RandomAiEnemy() : new FocusAiEnemy();
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
