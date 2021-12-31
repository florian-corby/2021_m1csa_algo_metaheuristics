package TSP;

public class City {
    private int id;
    private int[] coordinates;

    // ================= CONSTRUCTORS ================= //
    public City(int id, int x, int y){
        this.id = id;
        this.coordinates = new int[]{x, y};
    }

    public City(City toCopy){
        id = toCopy.getId();
        coordinates = new int[2];
        coordinates[0] = toCopy.getCoordinates()[0];
        coordinates[1] = toCopy.getCoordinates()[1];
    }

    // ================= METHODS ================= //
    public int getId() { return id; }
    public int[] getCoordinates() { return new int[]{coordinates[0], coordinates[1]}; }
}
