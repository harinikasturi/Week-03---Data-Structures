class PetrolPump {
    int petrol;
    int distance;

    PetrolPump(int p, int d) {
        petrol = p;
        distance = d;
    }
}

class CircularTour {
    public static int findStartPoint(PetrolPump[] pumps) {
        int start = 0;
        int deficit = 0;
        int surplus = 0;

        for (int i = 0; i < pumps.length; i++) {
            surplus += pumps[i].petrol - pumps[i].distance;
            if (surplus < 0) {
                deficit += surplus;
                surplus = 0;
                start = i + 1;
            }
        }
        return (surplus + deficit) >= 0 ? start : -1;
    }
}