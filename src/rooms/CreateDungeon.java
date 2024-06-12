package rooms;

import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import gui.Draw;
import rooms.Home.*;
import rooms.Village.Goal;
import rooms.Village.HouseEntrance;
import rooms.Village.HouseExit;
import rooms.Village.HouseMiddel;
import rooms.Village.HouseMiddel1;
import rooms.Village.HouseMiddel2;
import rooms.Village.MarketEntrance;
import rooms.Village.MarketExit;
import rooms.Village.MarketMiddel;
import rooms.Village.MarketMiddel1;
import rooms.Village.MarketMiddel2;
import rooms.Village.Normal;
import rooms.Village.Spawn;

public class CreateDungeon {
    public ArrayList<Integer> DungeonRooms;
    public int DungeonLength;
    public int currentRoom = 0;
    private static RoomTemplate[][] rooms = {
            {new Dungeon("Dungeon"), new Entrace("Eingang"), new Center("Mitte"), new Exit("Ausgang"), new Field("Field")},
            {new Normal("Path"), new HouseEntrance("Haus eingang"), new HouseMiddel("Haus Mitte 1"), new HouseMiddel1("Haus Mitte 2"), new HouseMiddel2("Haus Mitte 3"), new HouseExit("Haus Exit"),
                    new MarketEntrance("Markt Eingang"), new MarketMiddel("Markt Mitte 1"), new MarketMiddel1("Markt Mitte 2"), new MarketMiddel2("Markt Mitte 3"), new MarketExit("Markt Exit"),
                    new Spawn("Spawn"), new Goal("Goal")},
            {}
    };
    private int[][] Features = {{1, 6}};
    private static int[] homeVillage = {0, 1, 2, 3, 4};
    public boolean homeVillageBuild = false;
    public static int dungeonType = 1;
    private static int[] maxRooms = {10, 20, 50, 100};

    public void createDungeon() {
        if (!homeVillageBuild) {
            DungeonLength = (int) (Math.random() * (maxRooms[dungeonType-1] / 2)) + (maxRooms[dungeonType-1] / 2);
            DungeonRooms = new ArrayList<Integer>();
            System.out.println("Dungeon erstelle mit:" + DungeonLength + " mit folgenden Räumen: ");
            for (int i = 0; i < DungeonLength; i++) {
                DungeonRooms.add(-1);
            }
            spreadFeatures();
            finishFeatures();
            
            DungeonRooms.addFirst(11);
            DungeonRooms.addLast(12);
            
            DungeonLength+=2;
            
            for (int i = 0; i < DungeonLength; i++) {
                System.out.print(DungeonRooms.get(i) + ", ");
            }
            
        }
    }

    private void spreadFeatures() {
        int indexLastFeature = -100;
        int random;

        for (int i = 0; i < DungeonLength; i++) {
            if (i + 3 >= DungeonLength) {
                break;
            }
            if (indexLastFeature + 3 > i) {
            } else {
                random = (int) (Math.random() * 100);
                if (random > 50) {
                    DungeonRooms.set(i, Features[dungeonType - 1][(int) (Math.random() * Features[dungeonType - 1].length)]);
                    indexLastFeature = i;
                }
            }
        }

    }

    private void finishFeatures() {
        for (int i = 0; i < DungeonLength; i++) {
            if (DungeonRooms.get(i) != -1 && checkFeature(DungeonRooms.get(i))) {
                int maxFeatureLenght = 0;
                for (int j = i + 1; j < DungeonLength; j++) {
                    if (DungeonRooms.get(j) == -1) {
                        maxFeatureLenght++;
                    } else {
                        break;
                    }
                }

                int FeatureLength = (int) (Math.random() * (maxFeatureLenght) + 1);
                int FeatureEnd = DungeonRooms.get(i) + 4;
                DungeonRooms.set(FeatureLength + i, FeatureEnd);

                int[] possibleRooms = {DungeonRooms.get(i) + 1, DungeonRooms.get(i) + 2, DungeonRooms.get(i) + 3};
                for (int j = i + 1; j < FeatureLength + i; j++) {
                    DungeonRooms.set(j, possibleRooms[(int) (Math.random() * 3)]);
                }

            }
        }
        for (int i = 0; i < DungeonLength; i++) {
            if (DungeonRooms.get(i) == -1) {
                DungeonRooms.set(i, 0);
            }
        }
    }

    private boolean checkFeature(int Feature) {
        for (int i = 0; i < Features[dungeonType - 1].length; i++) {
            if (Features[dungeonType - 1][i] == Feature) {
                return true;
            }

        }
        return false;

    }

    public int getRoom() {
        if (homeVillageBuild) {
            return homeVillage[currentRoom];
        }
        return DungeonRooms.get(currentRoom);
    }

    public int getLastRoom() {
        if (homeVillageBuild) {
            return homeVillage[currentRoom - 1];
        }
        return DungeonRooms.get(currentRoom - 1);
    }

    public int getNextRoom() {
        if (homeVillageBuild) {
            return homeVillage[currentRoom + 1];
        }
        return DungeonRooms.get(currentRoom + 1);
    }

    public int getDungeonLenght() {
        if (homeVillageBuild) {
            return homeVillage.length;
        }
        return DungeonLength;
    }

    public void drawRoom(Graphics2D g2d) {
        Draw.clearObjects();
        if (!homeVillageBuild) {
            rooms[dungeonType][DungeonRooms.get(currentRoom)].changeColors();
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString(rooms[dungeonType][DungeonRooms.get(currentRoom)].name, 200, 300);
            rooms[dungeonType][DungeonRooms.get(currentRoom)].createObjects();
            rooms[dungeonType][DungeonRooms.get(currentRoom)].DrawImage(g2d);
        } else {
            rooms[dungeonType][currentRoom].changeColors();
            g2d.setFont(new Font("Arial", Font.BOLD, 20));
            g2d.drawString(rooms[dungeonType][currentRoom].name, 200, 300);
            rooms[dungeonType][currentRoom].createObjects();
            rooms[dungeonType][currentRoom].DrawImage(g2d);
        }
    }
}
