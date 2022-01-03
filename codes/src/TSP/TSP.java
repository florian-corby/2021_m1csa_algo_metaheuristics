package TSP;

import DataStructures.Vector;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;


public class TSP {
    private Vector vec;
    private City[] cities;

    // ================= CONSTRUCTORS ================= //
    public TSP(String fileName){
        File f = new File(fileName);
        try {
            Scanner sc = new Scanner(f);
            int nbCities = sc.nextInt();
            vec = getRandVec(nbCities);
            cities = new City[nbCities];

            for(int i = 0; i < nbCities; i++)
                cities[i] = new City(sc.nextInt(), sc.nextInt(), sc.nextInt());
        }
        catch (FileNotFoundException e) { e.printStackTrace(); }
    }


    // ================= STATIC METHODS ================= //
    public static Vector getRandVec(int size){
        int[] res = new int[size]; Random rand = new Random(); int tmp, randIdx;
        for(int i = 0; i < size; i++) res[i] = i+1;

        for(int i = 0; i < size; i++){
            randIdx = rand.nextInt(size);
            tmp = res[i];
            res[i] = res[randIdx];
            res[randIdx] = tmp;
        }

        return new Vector(res);
    }

    public static double euclidianDistance(int[] v1, int[] v2){
        return Math.sqrt((v1[0] - v2[0]) * (v1[0] - v2[0]) + (v1[1] - v2[1]) * (v1[1] - v2[1]));
    }

    public static double getDistance(City[] cities, int[] vec){
        double res = euclidianDistance(new int[]{0, 0},
                cities[vec[0]-1].getCoordinates());

        for(int i = 0; i < cities.length - 1; i++){
            res += euclidianDistance(cities[vec[i]-1].getCoordinates(),
                    cities[vec[i+1]-1].getCoordinates());
        }

        res += euclidianDistance(cities[vec[cities.length-1]-1].getCoordinates(),
                new int[]{0,0});

        return res;
    }

    public static void printDist(City[] cities, Vector vec){
        System.out.println("Distance: " + getDistance(cities, vec.getPrimitiveVector()) + " km");
    }


    // ================= METHODS ================= //
    public void printDist(){printDist(cities, vec);}
    public City getCity(int idx){ return new City(cities[idx]); }
    public City[] getCities(){
        City[] res = new City[cities.length];
        for(int i = 0; i < cities.length; i++) res[i] = getCity(i);
        return res;
    }
    public Vector getVec() { return new DataStructures.Vector(vec.getPrimitiveVector()); }
    public void setVec(int[] toCopy){ vec.setPrimitiveVector(toCopy); }
}
